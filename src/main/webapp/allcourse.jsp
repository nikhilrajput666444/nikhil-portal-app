<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="model.Course"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All course</title>
</head>
<body>
<!-- navbar started -->
	<%@include file="navbar.jsp"%>
	<!-- navbar ends -->
	
	<!-- filter portion starts -->
	<div class="d-flex flex-row bd-highlight mb-3">
		<div class="p-2 bd-highlight">
			<form action="viewcourses" method="get"
				class="form-inline my-2 my-lg-0">

				<input class="form-control mr-sm-2" type="text" name="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>

			</form>
		</div>

		<div class="p-2 bd-highlight">
			<form action="viewcourses" method="get"
				class="form-inline my-2 my-lg-0">
				<div class="form-group">
					<select name="status" class="form-control"
						id="exampleFormControlSelect1" onchange="this.form.submit()">

						<option value="">All Status</option>

						<%
						List<String> statuses = (List<String>) application.getAttribute("statuslist");
						if (statuses != null) {

							for (String s : statuses) {
						%>

						<option value="<%=s%>"
							<%=s.equals(request.getParameter("status")) ? "selected" : ""%>><%=s%></option>

						<%
						}

						}
						%>

					</select>
				</div>
			</form>
		</div>


		<div class="p-2 bd-highlight">
			<form action="viewcourses" method="get"
				class="form-inline my-2 my-lg-0">
				<div class="form-group">
					<select name="fee" class="form-control"
						id="exampleFormControlSelect1" onchange="this.form.submit()">
						<option value="">Sorted by fee</option>
						<option value="low-high"
							<%="low-high".equals(request.getParameter("fee")) ? "selected" : ""%>>Low-High</option>
						<option value="high-low"
							<%="high-low".equals(request.getParameter("fee")) ? "selected" : ""%>>High-Low</option>

					</select>
				</div>
			</form>
		</div>


	</div>

	<!-- filter portion ends -->

	<!-- success msg and error msg starts -->
	<c:if test="${not empty sessionScope.successmsg}">


		<div class="alert alert-success" role="alert">${sessionScope.successmsg}</div>

		<c:remove var="successmsg" scope="session" />
	</c:if>

	<c:if test="${not empty sessionScope.errormsg}">


		<div class="alert alert-warning" role="alert">${sessionScope.errormsg}</div>

		<c:remove var="errormsg" scope="session" />
	</c:if>
	<!-- successmsg & error msg end -->

	<div class="row m-2">

		<%
		List<Course> list = (List<Course>) request.getAttribute("courses");

		if (list == null || list.isEmpty()) {
		%>

		<p class=" text-success">No Course Available.</p>
		<%
		} else {
		for (Course c : list) {
		%>


		<div class="col-md-4  col-12">
			<div class="card m-4">
				<img src="course_img/<%=c.getCourseImg()%>" width="150px"
					height="200px" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title"><%=c.getCourseName()%></h5>
					<p class="card-text"><%=c.getCourseDesc()%></p>
					<p class="card-text">
						Fee :-<%=c.getCourseFee()%></p>
					<p class="card-text"><%=c.getStatus()%></p>
					<a href="enrollment?cid=<%=c.getCourseId()%>"
						class="btn btn-primary">Enroll</a> <a
						href="download?cid=<%=c.getCourseId()%>" class="btn btn-primary">Download
						Syllabus</a>
				</div>
			</div>
		</div>


		<%
		}
		}
		%>



	</div>	
</body>
</html>