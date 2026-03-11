<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<div class="container my-3">
		<div class="row">

			<div class="col-12 col-md-6 offset-md-3">

				<!-- form started -->


				<form action="addcourse" method="post" enctype="multipart/form-data">
				<h1>Add course</h1>
				
					 <div class="form-group">
    <label for="exampleFormControlSelect1">Status</label>
    <select class="form-control" name="cstatus"id="exampleFormControlSelect1">
      <option value="" selected disabled  >--Select--</option>
      <option value="Started">Started</option>
      <option value="Comming soon">Comming soon</option>
      <option value="Currently not available">Currently not available</option>
      
    </select>
  </div>

					<div class="form-group">
						<label for="cn">Course Name</label> <input type="Text"
							name="cname" class="form-control" id="Last name"
							aria-describedby="ln">

					</div>


 <div class="form-group">
    <label for="exampleFormControlSelect1">Course Duration</label>
    <select class="form-control"  name="cdur" id="exampleFormControlSelect1">
      <option value="" selected disabled>--Select--</option>
      <option  value="3 Months">3 Months</option>
      <option value="6 Months">6 Months</option>
      <option value = "12 Months">12 Months</option>
       <option  value = "24 Months(2 years)">24 Months(2 years)</option>
    </select>
  </div>



<div class="form-group">
						<label for="cn">Course Fee</label> <input type="number"
							name="cfee" class="form-control" id="Last name"
							aria-describedby="ln">

					</div>
					

                     <div class="form-group">
    <label for="exampleFormControlTextarea1">Course Desc</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" name="cdesc" rows="3"></textarea>
  </div>

					
					





					<div class="form-group">
						<label for="cpic">Upload Course Image </label> <input  type="file"
							name="cpic" class="form-control" id="Last name"
							aria-describedby="ln">
							
							<c:if test="${not empty sessionScope.imgmsg}">

					
						<div class="alert alert-warning" role="alert">${sessionScope.imgmsg }</div>
					


					
             	<c:remove var="imgmsg" scope="session" />
				</c:if> 
							
							

					</div>


                       	<div class="form-group">
						<label for="cpdf">Upload Course Syllabus </label> <input type="file"
							name="cpdf" class="form-control"  accept="application/pdf"  id="Last name"
							aria-describedby="ln">
							
							 <c:if test="${not empty sessionScope.pdfmsg}">

					
						<div class="alert alert-warning" role="alert">${sessionScope.pdfmsg }</div>
					


					
             	<c:remove var="pdfmsg" scope="session" />
				</c:if>

					</div>
               



					<button type="submit" class="btn btn-primary">Add Course</button>
				</form>

				<!-- form ends -->

				<!-- sucess or fail message for signup -->

				 <c:if test="${not empty sessionScope.successmsg}">


					<div class="alert alert-success" role="alert">${sessionScope.successmsg}</div>

					<c:remove var="successmsg" scope="session" />
				</c:if> 
				
				<c:if test="${not empty sessionScope.errormsg}">


					<div class="alert alert-warning" role="alert">${sessionScope.errormsg}</div>

					<c:remove var="errormsg" scope="session" />
				</c:if>
				 




			</div>


		</div>

	</div>











<!-- body starts -->






</body>
</html>