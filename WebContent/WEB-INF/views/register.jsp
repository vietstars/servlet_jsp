
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-4 offset-sm-4 mt-5">
		<h1 class="text-center">Register page</h1>
		<form method="post">
		  	<div class="form-group">
			    <label for="exampleInputEmail1">Email address</label>
			    <input type="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" />
			    <c:if test = "${error.email}">
			    	<small id="emailHelp" class="form-text text-danger">${errorString.email}</small>
			    </c:if>
		  	</div>
		  	<div class="form-group">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Enter password" />
			    <c:if test = "${error.password}">
			    	<small id="emailHelp" class="form-text text-danger">${errorString.password}</small>
			    </c:if>
		  	</div>
		  	<div class="form-group">
			  	<div class="custom-control custom-radio d-inline">
				  <input type="radio" id="genderM" name="gender" value="M" class="custom-control-input" checked />
				  <label class="custom-control-label" for="genderM">Male</label>
				</div>
				<div class="custom-control custom-radio d-inline">
				  <input type="radio" id="genderF" name="gender" value="F" class="custom-control-input" />
				  <label class="custom-control-label" for="genderF">Female</label>
				</div>
			</div>
		  	<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</div>