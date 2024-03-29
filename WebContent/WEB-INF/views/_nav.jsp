<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
	  	<a class="navbar-brand" href="/pwater">Pwater</a>
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
	  	</button>
	  	<div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="navbar-nav">
		      <li class="nav-item active">
		        <a class="nav-link" href="/pwater/home">Home <span class="sr-only">(current)</span></a>
		      </li>
		      <c:choose>
			      <c:when test = "${!sessionScope.isLogin}">
			    	  <li class="nav-item">
				        <a class="nav-link" href="/pwater/login">Login</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="/pwater/register">Register</a>
				      </li>
				  </c:when>
				  <c:otherwise>
				  	  <li class="nav-item">
				        <a class="nav-link" href="#">${sessionScope.loggedId}</a>
				      </li>
				  	  <li class="nav-item">
				        <a class="nav-link" href="/pwater/logout">Logout</a>
				      </li>
		    	  </c:otherwise>
	    	  </c:choose>
		    </ul>
	  	</div>
	</div> 
</nav>