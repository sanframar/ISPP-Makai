
package controllers.actor;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import paypal.PaypalEnvironment;
import services.ActorService;
import services.BannerService;
import services.NotificationService;

import com.paypal.api.payments.Payment;

import controllers.AbstractController;
import domain.Actor;
import domain.Banner;
import forms.BannerForm;

@Controller
@RequestMapping("/banner/actor")
public class BannerActorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private BannerService		bannerService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private NotificationService	notificationService;


	// Constructors -----------------------------------------------------------

	public BannerActorController() {
		super();
	}

	// Listing -----------------------------------------------------------------		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByAdvertising() {
		ModelAndView result;
		Collection<BannerForm> bannerForms;
		Collection<Banner> banners;
		Actor actor;
		Integer numberNoti;

		actor = this.actorService.findByPrincipal();
		if (this.actorService.checkAuthority(actor, "ADMIN"))
			banners = this.bannerService.findAll();
		else
			banners = this.bannerService.findByActorId(actor.getId());
		numberNoti = this.notificationService.findNotificationWithoutRead();

		bannerForms = new HashSet<BannerForm>();

		for (final Banner b : banners)
			bannerForms.add(this.bannerService.bannerToFormObject(b));

		result = new ModelAndView("banner/list");
		result.addObject("requestURI", "banner/actor/list.do");
		result.addObject("numberNoti", numberNoti);
		result.addObject("bannerForms", bannerForms);

		return result;
	}

	// Display -----------------------------------------------------------------		
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int bannerId) {
		ModelAndView result;
		final Integer numberNoti;
		Banner banner;

		banner = this.bannerService.findOne(bannerId);
		numberNoti = this.notificationService.findNotificationWithoutRead();

		result = new ModelAndView("banner/display");
		result.addObject("requestURI", "banner/actor/display.do");
		result.addObject("numberNoti", numberNoti);
		result.addObject("banner", banner);

		return result;
	}

	// Delete --------------------------------------------------------------------		

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int bannerId) {
		ModelAndView result;
		Banner banner;
		try {
			banner = this.bannerService.findOne(bannerId);

			this.bannerService.delete(banner);

			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			System.out.println(oops.toString());
			result = new ModelAndView("error");
		}
		return result;
	}

	// Creation ---------------------------------------------------------------		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		Banner banner;
		BannerForm bannerForm;

		banner = this.bannerService.create();
		bannerForm = this.bannerService.bannerToFormObject(banner);
		bannerForm.setPrice(0.0);

		result = this.createEditModelAndView(bannerForm);

		return result;
	}

	// Edition ----------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int bannerId) {
		ModelAndView result;
		Banner banner;
		BannerForm bannerForm;

		banner = this.bannerService.findOne(bannerId);
		bannerForm = this.bannerService.bannerToFormObject(banner);

		result = this.createEditModelAndView(bannerForm);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final BannerForm bannerForm, final BindingResult binding, final HttpServletRequest req, final HttpServletResponse resp) throws IOException {

		ModelAndView result;
		Banner banner;
		byte[] savedFile;

		banner = this.bannerService.reconstruct(bannerForm, binding);

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(bannerForm);

		} else
			try {

				if (bannerForm.getBannerImage().getSize() > 0) {

					savedFile = bannerForm.getBannerImage().getBytes();
					banner.setPicture(savedFile);

				}
				banner.setPaid(false);
				banner.setActive(false);
				banner = this.bannerService.save(banner);

				final double price = banner.getPrice();

				final PaypalEnvironment paypalEnvironment = new PaypalEnvironment();
				final Payment payment = paypalEnvironment.createPayment(req, resp, price, banner.getId(), "bannerId", "/banner/actor");
				result = new ModelAndView("redirect:" + req.getAttribute("redirectURL"));

				//				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(bannerForm, "banner.commit.error");

			}
		return result;

	}

	@RequestMapping(value = "/payment/done", method = RequestMethod.GET)
	public ModelAndView paymentFirstStep(@RequestParam(required = false) final int bannerId) {
		ModelAndView result;
		result = new ModelAndView();
		try {
			final Banner banner = this.bannerService.findOne(bannerId);
			banner.setPaid(true);
			this.bannerService.simpleSave(banner);
			result = new ModelAndView("banner/actor/payment/successful");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/");
			result.addObject("errorMessage", "banner.pay.error");
		}
		return result;
	}

	@RequestMapping(value = "/payment/successful", method = RequestMethod.GET)
	public ModelAndView paymentSecondStep() {
		ModelAndView result;
		result = new ModelAndView();
		try {
			result = new ModelAndView("redirect:/banner/actor/payment/done.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/");
			result.addObject("errorMessage", "offer.pay.error");
		}
		return result;
	}

	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ModelAndView payBanner(@RequestParam final int bannerId, final HttpServletRequest req, final HttpServletResponse resp) {
		ModelAndView result;
		try {
			final Actor actor = this.actorService.findByPrincipal();
			final Banner banner = this.bannerService.findOne(bannerId);
			Assert.isTrue(banner.getActor().equals(actor));

			final double price = banner.getPrice();

			final PaypalEnvironment paypalEnvironment = new PaypalEnvironment();
			final Payment payment = paypalEnvironment.createPayment(req, resp, price, banner.getId(), "bannerId", "/banner/actor");
			result = new ModelAndView("redirect:" + req.getAttribute("redirectURL"));
		} catch (final Throwable oops) {
			result = new ModelAndView("error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final BannerForm bannerForm) {
		ModelAndView result;

		result = this.createEditModelAndView(bannerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final BannerForm bannerForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("banner/create");
		result.addObject("bannerForm", bannerForm);
		result.addObject("requestURI", "banner/actor/create.do");
		result.addObject("errorMessage", message);

		return result;
	}
}