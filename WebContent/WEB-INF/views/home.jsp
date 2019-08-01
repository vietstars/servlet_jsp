<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-4 offset-sm-4 mt-5">
		<h1 class="text-center">Home page!</h1>
		<h5>User: ${sessionScope.loggedId}</h5>
		<c:set var="Male" scope = "session" value = "M"/>
		<h5>Gender: ${Male.equals(sessionScope.loggedGender)?"Male":"Female"}</h5>
	</div>
</div>