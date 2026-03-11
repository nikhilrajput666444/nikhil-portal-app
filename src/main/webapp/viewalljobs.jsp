<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.*"%>
<%@ page import="dbconnection.ConnectionProvider"%>
<%@ page import="dao.JobDao"%>
<%@ page import="model.Job"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty sessionScope.userobj}">

    <c:redirect url="login.jsp"></c:redirect>
		
	</c:if>
	
	
		<!--   if user loffed in as a normal use then its role will be normal in that case that user try to open admin.jsp then
    it will redirected to login page -->



  <!-- is any user directly try to open admin.jsp then redirected to login page -->
	<c:if test="${empty sessionScope.userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<!-- navbar started -->
	<%@include file="navbar.jsp"%>
	<!-- navbar ends -->
	
	
	
	


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

	<div class="container-fluid">

		<div class="row">



			<div class="col-12 col-md-10 offset-md-1 ">

				<%
				JobDao jdao = new JobDao(ConnectionProvider.getDbConnection());
				User user = (User) session.getAttribute("userobj");

				List<Job> jobList = jdao.getAllJobs(user.getRole());

				if (jobList != null && !jobList.isEmpty()) {
					
					
					
					
					for (Job j : jobList) {
						
						
				%>

				<div class="card w-100 m-2">
					<div class="card-body">
						<h5 class="card-title">
							Title:-
							<%=j.getTitle()%></h5>
						<p class="card-text">
							Desc:-
							<%=j.getJobdesc()%></p>
						<p class="card-text">
							<b>Location </b><%=j.getLocation()%>
							| <b>Category </b>
							<%=j.getCategory()%>
							|<b>Status </b><span
								style="color:<%=j.getStatus().equals("active") ? "green" : "red"%>"><%=j.getStatus()%></span>
						</p>
						<p class="card-text">
							Posted on:-
							<%=j.getpDate()%>
						</p>

                  <c:if test="${sessionScope.userobj.role=='normal'}">

						<a href="applyjob?jid=<%=j.getJobid()%>" class="btn btn-primary">Apply</a> 
						
						
						<a href=""
							class="btn btn-primary">Read More</a> 
							
							
						</c:if>	
							
							
							 <c:if test="${sessionScope.userobj.role=='admin'}">
							<a href="deletejob?jid=<%=j.getJobid()%>"
							class="btn btn-primary">Delete</a> 
							
							<a
							href="editjob.jsp?jid=<%=j.getJobid()%>" class="btn btn-primary">Edit</a>
           </c:if>
					
					
					
					
					
					
					
					</div>
				</div>

				<%
				}
				} else {
					
				
				%>
				<p class="text-primary">No Active Jobs Available !!!</p>
				<%
				}
				%>

			
			
			
			
			</div>







		</div>



	</div>

</body>
</html>