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

<form:form action="${RequestURI}" modelAttribute="requestForm" enctype="multipart/form-data">
		
	<fieldset>
		<acme:textbox code="request.description" path="description" mandatory="true" />
		<br />
		<acme:input code="request.tags" path="tags" />
		<br />
		<%--
		<acme:select code="request.category" path="category" items="${categories}" itemLabel="getName" />
		<br />
		--%>
		<acme:select code="request.animal" path="animal" items="${animals}" itemLabel="name" />
		<br />

		<br/>
		
		<form:label path="category">
			<spring:message code="request.category"/>
		</form:label>
		<jstl:forEach var="category" items="${categoriesList}">
			<form:checkbox path ="category" value="${category}"/><jstl:out value="${category}"/>
		</jstl:forEach>
		<form:errors path="category" cssClass="error"/>

	</fieldset>
	<br/>
	
	<jstl:if test="${errorMessage != null}">
		<spring:message code="${errorMessage}" var="error" />
		<font size="4" color="red"><jstl:out value="${error}"></jstl:out></font>
	</jstl:if>
	<br/>
	
		<acme:submit code="request.create" name="save" />
		<acme:cancel code="request.cancel" url="" />
	<br/>
	
</form:form>