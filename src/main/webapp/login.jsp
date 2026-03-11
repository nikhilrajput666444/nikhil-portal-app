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

	<div class="container">

		<div class="row">

			<div class="col-12  col-md-6 offset-md-3 my-4  border  ">

				<!-- login form starts -->
				<form action="login" method="post">



					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label> <input
							type="Email" name="Email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp"> <small
							id="emailHelp" class="form-text text-muted">We'll never
							share your email with anyone else.</small>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" name="Password" class="form-control"
							id="exampleInputPassword1">
					</div>

					<button type="submit" class="btn btn-primary">login</button>




				</form>

<c:if test="${not empty sessionScope.successmsg}">


		<div class="alert alert-success" role="alert">${sessionScope.successmsg}</div>

		<c:remove var="successmsg" scope="session" />
	</c:if>


				<!-- login form ends  -->
				<%-- 	<!-- display message for invalid login -->
				<c:if test="${not empty sessionScope.msg}">
				
				<c:if test="${sessionScope.msg=='Invalid email or password'}">
				
				<div class="alert alert-danger" role="alert">Invalid Username or Password</div>
				</c:if>
				
				<c:remove var="msg" scope="session"/>
				
				</c:if> --%>


				<%
				String message=(String)session.getAttribute("msg");
				if(session!=null && message!=null && message.equals("Invalid email or password"))
				{
				%>


				<div class="alert alert-danger" role="alert">Invalid
					Credentials!!</div>

				<%
				}
				
				session.removeAttribute("msg");
				%>
				
				
				
				



			</div>



		</div>






	</div>







</body>
</html>