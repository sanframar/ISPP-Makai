
package controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import domain.Banner;
import forms.BannerForm;

@Controller
@RequestMapping("/banner")
public class BannerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private BannerService	bannerService;


	// Constructors -----------------------------------------------------------

	public BannerController() {
		super();
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

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final BannerForm bannerForm, final BindingResult binding) throws IOException {

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

				this.bannerService.save(banner);
				result = new ModelAndView("redirect:../");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(bannerForm, "banner.commit.error");

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
		result.addObject("requestURI", "banner/create.do");
		result.addObject("errorMessage", message);

		return result;
	}
}