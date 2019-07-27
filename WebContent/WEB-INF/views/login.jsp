<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-4 offset-sm-4 mt-5">
		<h1 class="text-center">Login page</h1>
		<form method="post">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		    <input type="text" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
		    <c:if test = "${hasError}">
		    	<small id="emailHelp" class="form-text text-danger">${errorString[0]}</small>
		    </c:if>
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password">
		    <c:if test = "${hasError}">
		    	<small id="emailHelp" class="form-text text-danger">${errorString[1]}</small>
		    </c:if>
		  </div>
		  <div class="form-group form-check">
		    <input type="checkbox" class="form-check-input" name="remember" id="exampleCheck1">
		    <label class="form-check-label" for="exampleCheck1">Check me out</label>
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</div>