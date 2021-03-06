<%--
 * index.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="row text-center mt-3">
	<div class="col-lg-8">
	<div class="card py-3 shadow" style="background-color: rgba(248, 249, 250, 0.58);">
		<div class="row align-items-center">
			<div class="offset-lg-1 col-lg-3">
				<div class="center-div">
					<img src="images/logo.png" alt="Makai" style="height: 220px;" />
				</div>
			</div>
			<div class="offset-lg-1 col-lg-7">
				<h3><spring:message code="welcome.greeting.prefix" /></h3>	
			</div>
		</div>
	</div>
	</div>
	
	<security:authorize access="hasRole('CUSTOMER')">
		<div class="col-lg-4 mt-lg-0 mt-3">
			<div class="card bg-primary pt-3 shadow">
				<div class="center-div py-2">
					<img alt="Go to travels" src="images/travel-pet.png"  height="128" width="128">
				</div>
				<div class="card-body"> <acme:link  href="travel/menu.do" code="welcome.go.to.travel" type=" btn-outline-light" /></div>
			</div>
		</div>
	</security:authorize>
	
	<security:authorize access="hasRole('ANIMALSHELTER')">
		<div class="col-lg-4 mt-lg-0 mt-3">
			<div class="card bg-warning pt-3 shadow">
				<div class="center-div py-2">
					<img alt="Go to animals" src="images/pet-xl.png"  height="128" width="128">
				</div>
				<div class="card-body"> <acme:link  href="animal/menu.do" code="welcome.go.to.animals" type=" btn-outline-light" /></div>
			</div>
		</div>
	</security:authorize>
	
		<security:authorize access="hasRole('ADMIN')">
		<div class="col-lg-4 mt-lg-0 mt-3">
			<div class="card bg-info pt-3 shadow">
			
				<div class="center-div py-2">
					<img alt="Statistics" src="images/statistics.png"  height="128" width="128">
				</div>
				<div class="card-body"> <acme:link  href="banner/admin/dashboard.do" code="welcome.go.to.statistics" type=" btn-outline-light" /></div>
			
			</div>
		</div>
	</security:authorize>
	
	
	<security:authorize access="hasRole('TRAINER')">
		<div class="col-lg-4 mt-lg-0 mt-3">
			<div class="card bg-secondary pt-3 shadow">
			
				<div class="center-div py-2">
					<img alt="My offerts" src="images/handshake-xl.png"  height="128" width="128">
				</div>
				<div class="card-body"> <acme:link  href="offer/trainer/list.do" code="master.page.offer" type=" btn-outline-light" /></div>
			
			</div>
		</div>
	</security:authorize>
	
	<security:authorize access="isAnonymous()">
		<div class="col-lg-4 mt-lg-0 mt-3">
		<div class="card bg-info pt-3 shadow">
			<div class="center-div py-2">
				<img alt="image" src="images/image2.png"  height="128" width="128">
			</div>
			<div class="card-footer mt-3">
				<button type="button" data-toggle="modal" data-target="#advertisingModal" class="btn btn-lg btn-outline-light" >
				 	<spring:message code="master.page.banner.advertise" />
				</button>
			</div>
		
		</div>
		</div>
	</security:authorize>
	
</div>
<br>
<div class="card-deck text-light text-center pb-2">

	<security:authorize access="hasRole('CUSTOMER')">
			<div class="card bg-info shadow">
				<div class="center-div py-2">
					<img alt="Go to animals" src="images/request-xl.png"  height="128" width="128">
				</div>
				<div class="card-body"> <acme:link  href="request/customer/menu.do" code="welcome.go.to.request" type=" btn-outline-light" /></div>
			</div>
			<div class="card bg-warning shadow">
				<div class="center-div py-2">
					<img alt="Go to animals" src="images/dog.png"  height="128" width="128" style="margin-right: -45px;">
					<img alt="Go to animals" src="images/cat.png"  height="128" width="128">
				</div>
				<div class="card-body"> <acme:link  href="animal/menu.do" code="welcome.go.to.animals" type=" btn-outline-light" /></div>
			</div>
		
	</security:authorize>

	<security:authorize access="isAnonymous()">
			<div class="card bg-secondary shadow">
				<div class="center-div py-2">
				<img alt="dog-training" src="images/trainer.png" height="128" width="128">
				</div>
				<h4 class="card-title"><spring:message code="welcome.trainer.title"/></h4>
				<h5 class="card-body card-text mx-2">
					<spring:message code="welcome.trainers.card"/>
				</h5>
				<div class="card-footer">
				<acme:link href="trainer/register.do" type=" btn-outline-light" code="welcome.trainer.register"/>
				</div>
			</div>
			<div class="card bg-primary shadow">
				<div class="center-div py-2">
				<img alt="travel-pet" src="images/travel-pet.png" height="128" width="128">
				</div>
				<h5 class="card-title"><spring:message code="welcome.travel.title"/></h5>
				<h5 class="card-body card-text"><spring:message code="welcome.travels.card"/></h5>
				<div class="card-footer">
				<acme:link href="customer/register.do" type=" btn-outline-light" code="welcome.customer.register"/>
				</div>
			</div>
			<div class="card bg-warning shadow">
				<div class="center-div py-2">
					<img alt="dog-training" src="images/animalshelter.png" height="128" width="200">
				</div>
				<h4 class="card-title"><spring:message code="welcome.animalshelter.title"/></h4>
				<h5 class="card-body card-text"><spring:message code="welcome.animalshelters.card"/></h5>
		
				<div class="card-footer">
				<acme:link href="animalShelter/register.do" type=" btn-outline-light" code="welcome.animalShelter.register"/>
				</div>
			</div>
	</security:authorize>
</div>
	<security:authorize access="hasRole('TRAINER')">
	
		<div class="row pb-2 text-light text-center">
			<div class="col-lg-8">
				<div class="card bg-info shadow">
					<div class="center-div py-2">
						<img alt="Go to Request" src="images/request-xl.png"  height="128" width="128">
					</div>
					<div class="card-body"> <acme:link  href="request/trainer/list.do" code="welcome.go.to.request" type=" btn-outline-light" /></div>
				</div>
			</div>
			<div class="col-lg-4 pt-4 pt-lg-0">
				<div class="card bg-secondary shadow">
				<div class="center-div py-2">
				
					<img alt="My past offerts" src="images/handshake-xl.png"  height="128" width="128">
					<img alt="My past offerts" src="images/calendar-xl.png"  height="80" width="80" style="margin: 50px 0px 0px -55px;">
				</div>
				<div class="card-body"> <acme:link  href="offer/trainer/listPassed.do" code="master.page.offer.passed" type=" btn-outline-light" /></div>
			</div>
			</div>
		</div>
		
	</security:authorize>


<!-- The Modal Advertasing -->
<div class="modal fade" id="advertisingModal">
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content">
		
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title"><spring:message code="master.page.banner.advertise"/></h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body">
				<spring:message code="master.page.banner.haveAccount"/>
			</div>
			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="location='security/login.do'"><spring:message code="master.page.yes"/></button>
				<button type="button" class="btn btn-dark" data-dismiss="modal" onclick="location='advertising/register.do'"><spring:message code="master.page.no"/></button>
			</div>
		  
		</div>
	</div>
  </div>


