<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body>


<!--   if user loffed in as a normal use then its role will be normal in that case that user try to open admin.jsp then
    it will redirected to login page -->
	<c:if
		test="${not empty sessionScope.userobj.role and  sessionScope.userobj.role != 'admin'}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>


  <!-- is any user directly try to open admin.jsp then redirected to login page -->
	<c:if test="${empty sessionScope.userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>



<!-- navbar started -->
	<%@include file="navbar.jsp"%>
	<!-- navbar ended -->

     <!-- body starts -->
     
     <div class="container">
     <div class="row">
     
     
     <div class="col-md-3">
     <div class="card admincard  mycard" >
 
  <div class="card-body">
   <a href="addcourse.jsp" class="card-link  text-light">Add Course</a>
  </div>
</div>
     </div>
     
     
     
      <div class="col-md-3">
     <div class="card admincard  mycard" >
 
  <div class="card-body">
   <a href="viewcourse.jsp" class="card-link text-light">View Course Info</a>
  </div>
</div>
     </div>
     
      <div class="col-md-3">
     <div class="card admincard  mycard" >
 
  <div class="card-body">
   <a href="addjobs.jsp" class="card-link text-light">Post Job</a>
  </div>
</div>
     </div>
     
     
      <div class="col-md-3">
     <div class="card admincard  mycard" >
 
  <div class="card-body">
   <a href="viewalljobs.jsp" class="card-link text-light">View Jobs</a>
  </div>
  
 </div>
     </div>
     
     
     
     
     <div class="col-md-3">
				<div class="card mycard admincard">
					<div class="card-body">
						<h>
							<a href="jobapplicants.jsp" class="card-link text-light ">View
								Applicants</a>
						</h>
					</div>
				</div>
			</div>
     
     
     
     
     
     
     
     
     </div>
     
     
     </div>
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     <!-- body ends  -->














</body>
</html>