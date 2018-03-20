<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<jstl:if test="${successMessage != null}">
	<div class="alert alert-success"><spring:message code="${successMessage}" /></div>
</jstl:if>

<form:form action="${RequestURI}" modelAttribute="travelForm" enctype="multipart/form-data">
		
	<fieldset>
		<br />
		<p><b>
			<spring:message code="travel.origin" />
		</b></p>
		<br />
		<acme:textbox code="travel.country" path="countryOrigin" mandatory="true" />
		<br />
		<acme:textbox code="travel.state" path="stateOrigin" mandatory="false" />
		<br />	
		<acme:textbox code="travel.province" path="provinceOrigin" mandatory="false" />
		<br />
		<acme:textbox code="travel.city" path="cityOrigin" mandatory="true" />
		<br />
		<acme:textbox code="travel.zipcode" path="zip_codeOrigin" mandatory="true" />
		<br />
		<p><b>
			<spring:message code="travel.destination" />
		</b></p>
		<br />
		<acme:textbox code="travel.country" path="countryDestination" mandatory="true" />
		<br />
		<acme:textbox code="travel.state" path="stateDestination" mandatory="false" />
		<br />	
		<acme:textbox code="travel.province" path="provinceDestination" mandatory="false" />
		<br />
		<acme:textbox code="travel.city" path="cityDestination" mandatory="true" />
		<br />
		<acme:textbox code="travel.zipcode" path="zip_codeDestination" mandatory="true" />
		<br />
		<p><b>
			<spring:message code="travel.details" />
		</b></p>
		<br />
		<acme:textbox code="travel.startMoment" path="startMoment" mandatory="true" />
		<br />

		<acme:textbox code="travel.endMoment" path="endMoment" mandatory="true" />
		<br />

		<acme:textbox code="travel.animalSeats" path="animalSeats" mandatory="false" />
		<br />
		
		<acme:textbox code="travel.humanSeats" path="HumanSeats" mandatory="false" />
		<br />
		
		<acme:select code="travel.vehicle" path="vehicle" items="${vehicles}" itemLabel="license" />
		<br />

	</fieldset>
	<br/>
	
	<security:authorize access="isAnonymous()">
	</security:authorize>
	
	<jstl:if test="${errorMessage != null}">
		<spring:message code="${errorMessage}" var="error" />
		<font size="4" color="red"><jstl:out value="${error}"></jstl:out></font>
	</jstl:if>
	<br/>
	
		<acme:submit code="travel.create" name="save" />
		<acme:cancel code="travel.cancel" url="" />
	<br/>
	
</form:form>