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
	<!-- navbar ends -->


        <!-- body starts -->

	<div class="container my-2">

		<div class="row">

			<div class="col-12 col-md-6 offset-md-3">

				<!-- form started -->
				<form action="addjob" method="post" >
					<h1>Add Job</h1>



					<div class="form-group">
						<label for="cn">Title</label> <input type="text" name="title"
							class="form-control" id="ln" aria-describedby="lnn">
					</div>



					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="inputState">Location</label> <select id="inputState"
								class="form-control" name="location">
								<option selected disabled="disabled">Choose...</option>
								<option value="pune">Pune</option>
								<option value="bengaluru">bengaluru</option>
								<option value="hyderabad">hyderabad</option>
							</select>
						</div>
						<div class="form-group col-md-4">
							<label for="inputState">Category</label> <select id="inputState"
								class="form-control" name="category">
								<option selected disabled="disabled">Choose...</option>
								<option value="IT">It</option>
								<option value="NON-IT">Non-It</option>
							</select>
						</div>
						<div class="form-group col-md-4">
							<label for="inputState">Status</label> <select id="inputState"
								class="form-control" name="status">
								<option selected disabled="disabled">Choose...</option>
								<option value="active">Active</option>
									<option value="inactive">In-Active</option>
							</select>
						</div>
					</div>



					<div class="form-group">
						<label for="exampleFormControlTextarea1">Job Desc</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							name="jobdesc" rows="3"></textarea>
					</div>




					<button type="submit" class="btn btn-primary">Add</button>
				</form>
				
				
				<!-- success or fail message for signup -->

				<c:if test="${not empty sessionScope.successmsg}">


					<div class="alert alert-success" role="alert">${sessionScope.successmsg}</div>

					<c:remove var="successmsg" scope="session" />
				</c:if>
				
					<c:if test="${not empty sessionScope.errormsg}">


					<div class="alert alert-warning" role="alert">${sessionScope.errormsg}</div>

					<c:remove var="errormsg" scope="session" />
				</c:if>
				<!-- form ended -->
			</div>
		</div>
	</div>


</body>
</html>