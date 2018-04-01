
package services;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.TravelRepository;
import security.Authority;
import domain.Actor;
import domain.Customer;
import domain.Notification;
import domain.NotificationType;
import domain.Transporter;
import domain.Travel;
import domain.Vehicle;
import forms.TravelForm;

@Service
@Transactional
public class TravelService {

	// Managed repository —---------------------------------------------------
	@Autowired
	private TravelRepository	travelRepository;

	// Supporting services —------------------------------------------------—
	@Autowired
	private TransporterService	transporterService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private AnimalService		animalService;

	@Autowired
	private ProfessionalService	professionalService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private NotificationService	notificationService;

	@Autowired
	private Validator			validator;


	// Constructors------------------------------------------------------------
	public TravelService() {
		super();
	}

	// Simple CRUD methods —------------------------------------------------—
	public Travel findOne(final int travelId) {
		Travel result;

		result = this.travelRepository.findOne(travelId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Travel> findAll() {
		Collection<Travel> result;

		result = this.travelRepository.findAll();

		return result;
	}

	public TravelForm createForm() {
		final TravelForm result = new TravelForm();

		return result;
	}

	public Travel create() {
		Travel result;
		Transporter principal;
		final Vehicle vehicle = null;

		principal = this.transporterService.findByPrincipal();
		Assert.notNull(principal);

		result = new Travel();
		result.setTransporterOwner(principal);
		result.setVehicle(vehicle);

		return result;
	}

	public Travel save(final Travel travel) {
		Assert.notNull(travel);
		Travel result;
		Transporter principal;
		Calendar today;

		principal = this.transporterService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(travel.getTransporterOwner().getId() == principal.getId());

		today = Calendar.getInstance();
		Assert.isTrue(today.getTime().before(travel.getStartMoment()));
		Assert.isTrue(travel.getStartMoment().before(travel.getEndMoment()));

		Assert.isTrue((travel.getAnimalSeats() != null || travel.getAnimalSeats() > 0) || (travel.getHumanSeats() != null || travel.getHumanSeats() > 0));

		result = this.travelRepository.save(travel);

		return result;
	}

	public void delete(final Travel travel) {
		Actor principal;
		Calendar today;

		Assert.notNull(travel);
		Assert.isTrue(travel.getId() != 0);

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN") || this.actorService.checkAuthority(principal, "CUSTOMER") || this.actorService.checkAuthority(principal, "PROFESSIONAL"));
		if (this.actorService.checkAuthority(principal, "CUSTOMER") || this.actorService.checkAuthority(principal, "PROFESSIONAL"))
			Assert.isTrue(travel.getTransporterOwner().getId() == principal.getId());

		today = Calendar.getInstance();
		Assert.isTrue(today.getTime().before(travel.getStartMoment()));

		this.travelRepository.delete(travel);
	}

	// Other business methods —---------------------------------------------—

	public void registerTravel(final Travel travel) {
		Actor actor;
		Customer customer;
		Collection<Travel> travels;
		Notification notification = null;

		actor = this.actorService.findByPrincipal();

		Assert.isTrue(this.actorService.checkAuthority(actor, Authority.CUSTOMER));

		customer = this.customerService.findByPrincipal();
		travels = customer.getTravelPassengers();

		Assert.isTrue(!travels.contains(travel));
		Assert.isTrue(travel.getHumanSeats() > 0);
		travel.setHumanSeats(travel.getHumanSeats() - 1);
		this.travelRepository.save(travel);
		travels.add(travel);
		customer.setTravelPassengers(travels);
		this.customerService.save(customer);

		notification = this.notificationService.create(travel.getTransporterOwner());

		notification.setReason("Nueva inscripcion a su viaje");
		notification.setDescription("Un usuario se ha apuntado a un viaje creado por usted");
		notification.setType(NotificationType.TRAVEL);
		this.notificationService.save(notification);

	}
	public Travel reconstruct(final TravelForm travelForm, final BindingResult binding) throws IOException {
		Assert.notNull(travelForm);
		Travel result;

		if (travelForm.getId() == 0)
			result = this.create();
		else
			result = this.findOne(travelForm.getId());

		result.setDestination(travelForm.getDestination());
		result.setOrigin(travelForm.getOrigin());
		result.setStartMoment(travelForm.getStartMoment());
		result.setEndMoment(travelForm.getEndMoment());
		result.setHumanSeats(travelForm.getHumanSeats());
		result.setAnimalSeats(travelForm.getAnimalSeats());
		result.setVehicle(travelForm.getVehicle());

		this.validator.validate(result, binding);

		return result;
	}

	public TravelForm toFormObject(final Travel travel) {
		Assert.notNull(travel);
		final TravelForm result = new TravelForm();

		result.setDestination(travel.getDestination());
		result.setOrigin(travel.getOrigin());
		result.setStartMoment(travel.getStartMoment());
		result.setEndMoment(travel.getEndMoment());
		result.setAnimalSeats(travel.getAnimalSeats());
		result.setHumanSeats(travel.getHumanSeats());
		result.setVehicle(travel.getVehicle());
		result.setId(travel.getId());

		return result;
	}

	public Collection<Travel> findTravelByTransporterId(final int transporterId) {
		return this.travelRepository.findTravelByTransporterId(transporterId);
	}

	public Collection<Travel> findTravelByVehicleId(final Vehicle vehicle) {
		return this.travelRepository.findTravelByVehicleId(vehicle.getId());
	}

}
