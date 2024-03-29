<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-4 offset-sm-4 mt-5">
		<h1 class="text-center">Login page</h1>
		<form method="post">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		    <input type="text" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" value="${user}" />
		    <c:set var="_false" scope = "session" value = "false"/>
		    <c:if test = "${_false != error.email}">
		    	<small id="emailHelp" class="form-text text-danger">${error.email}</small>
		    </c:if>
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Enter password" />
		    <c:if test = "${_false != error.password} }}">
		    	<small id="emailHelp" class="form-text text-danger">${error.password}</small>
		    </c:if>
		  </div>
		  <div class="form-group form-check">
		  	<c:set var="cookie_user" scope = "session" value = "${cookie['COOKIE_USER'].getValue()}"/>
		    <input type="checkbox" class="form-check-input" name="remember" value="Y" id="remember" checked="${(cookie_user != null)}"/>
		    <label class="form-check-label" for="remember">Check me out</label>
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</div>