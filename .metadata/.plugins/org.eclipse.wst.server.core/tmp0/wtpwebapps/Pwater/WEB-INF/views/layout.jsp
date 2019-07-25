<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp"></jsp:include>
   <jsp:include page="_nav.jsp"></jsp:include>
   <div class="row">
      <div class="col-6 offset-sm-3 mt-5">
         <h5><%= request.getAttribute("listBook") %></h5>
         <form method="POST" action="${pageContext.request.contextPath}/login">
            <div class="form-group">
               <label for="exampleInputEmail1">Email address</label>
               <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" value= "${listBook}">
               <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
               <label for="exampleInputPassword1">Password</label>
               <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"  value="${user.password}">
            </div>
            <div class="form-group form-check">
               <input type="checkbox" class="form-check-input" id="exampleCheck1">
               <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
         </form>
      </div>
   </div>
<jsp:include page="_footer.jsp"></jsp:include>
