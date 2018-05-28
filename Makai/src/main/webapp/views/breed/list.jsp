<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%@ page import="org.springframework.context.i18n.LocaleContextHolder" %>
<jstl:set var="lang" value="<%=LocaleContextHolder.getLocale()%>"/>

<div class="center-div">
	<acme:link href="breed/admin/create.do" type="success" code="breed.create" image="add1"/>
</div>

<jstl:set var="i" value="${0}"></jstl:set>
<div class="table-responsive">
<display:table name="breeds" id="row" pagesize="10" requestURI="${requestURI}" class="displaytag">

	<acme:emptyMsg/>
	
	<jstl:if test="${lang == 'es'}">
		<acme:column code="breed.name" property="nameSpa" sortable="true"/>
	</jstl:if>
	<jstl:if test="${lang == 'en'}">
		<acme:column code="breed.name" property="nameEng" sortable="true"/>
	</jstl:if>
	
	<display:column>
		<div class="btn-group">	
			<acme:link image="edit" href="breed/admin/edit.do?breedId=${row.id}" type="warning"/>
			<jstl:if test="${canDelete[i] == false}">
				<acme:link image="trash" href="breed/admin/delete.do?breedId=${row.id}" type="danger"/>
			</jstl:if>
		</div>

	</display:column>
	<jstl:set var="i" value="${i+1}"></jstl:set>
</display:table>
</div>