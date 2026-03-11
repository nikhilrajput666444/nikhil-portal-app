<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@ page import="model.Job"%>
<%@ page import="dao.JobApplyDao"%>
<%@ page import="dbconnection.ConnectionProvider"%>
<%@ page import="java.util.*"%>
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

	<!-- course list started -->
	<div class="container-fluid ">

		<div class="row">

			<div class="col-12 col-md-10 offset-md-1">

				<!-- data table starts -->
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Sno</th>
							<th scope="col">Job Title</th>
							<th scope="col">Total Applicants</th>
							<th scope="col">Action</th>

						</tr>
					</thead>
					<tbody>

						<%
						JobApplyDao jad = new JobApplyDao(ConnectionProvider.getDbConnection());
						List<Job> jlist = jad.getJobApplicantsCount();

						int sno = 1;

						for (Job j : jlist) {
						%>


						<tr>

							<td><%=sno++%></td>
							<td><%=j.getTitle()%></td>
							<td><%=j.getTotalJobApplicants()%></td>
							<td><a href="viewapplicantsdata.jsp?jobid=<%= j.getJobid() %>" class="btn btn-primary">View Applicants</a></td>
						</tr>

						<%
						}
						%>


					</tbody>
				</table>
				<!-- data table ends -->
			</div>



		</div>

	</div>




	<!-- course list ended -->

	<!-- success or fail message for signup -->

	<c:if test="${not empty sessionScope.successmsg}">


		<div class="alert alert-success" role="alert">${sessionScope.successmsg}</div>

		<c:remove var="successmsg" scope="session" />
	</c:if>

	<c:if test="${not empty sessionScope.errormsg}">


		<div class="alert alert-warning" role="alert">${sessionScope.errormsg}</div>

		<c:remove var="errormsg" scope="session" />
	</c:if>







</body>
</html>