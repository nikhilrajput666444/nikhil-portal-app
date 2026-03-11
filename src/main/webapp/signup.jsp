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
	<!-- navbar started -->
	<%@include file="navbar.jsp"%>
	<!-- navbar ended -->


	<div class="container my-3">
		<div class="row">

			<div class="col-12 col-md-6 offset-md-3">

				<!-- form started -->


				<form action="signup" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="First name">First name</label> <input type="Text"
							name="Firstname" class="form-control" id="First name"
							aria-describedby="fn">

					</div>

					<div class="form-group">
						<label for="Last name">Last name</label> <input type="Text"
							name="Lastname" class="form-control" id="Last name"
							aria-describedby="ln">

					</div>







					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label> <input
							type="email" name="Email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp">


						<%
   String existsValue = (String) session.getAttribute("msg");
   if(existsValue!=null && existsValue.equals("email already exists please try with another email !!!"))
   {
   
   %>

						<div class="alert alert-warning" role="alert">Email Already
							Exists Please Try With New Email !</div>

						<%
   
   }
   session.removeAttribute("msg");
   
   %>

					</div>



					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" name="Password" class="form-control"
							id="exampleInputPassword1">
					</div>

					<div class="form-group">
						<label for="mb">Mobile number</label> <input type="number"
							name="Mobilenumber" class="form-control" id="Last name"
							aria-describedby="ln">

					</div>





					<div class="form-group">
						<label for="pic">Profile picture</label> <input type="file"
							name="Pic" class="form-control" id="Last name"
							aria-describedby="ln">

					</div>







					<button type="submit" class="btn btn-primary">Submit</button>
				</form>

				<!-- form ends -->

				<!-- sucess or fail message for signup -->

				<c:if test="${not empty sessionScope.msg}">

					<c:if test="${sessionScope.msg=='success'}">
						<div class="alert alert-success" role="alert">Signup
							Successfully!!!!! Please login</div>
					</c:if>


					<c:if test="${sessionScope.msg=='fail'}">
						<div class="alert alert-success" role="alert">Something Went
							Wrong !!!1</div>
					</c:if>
             	<c:remove var="msg" scope="session" />
				</c:if>




			</div>


		</div>

	</div>

</body>
</html>