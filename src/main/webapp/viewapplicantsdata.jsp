<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="model.ApplicantsData"%>
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
							<th scope="col">FullName</th>
							<th scope="col">Email</th>
							<th scope="col">MobNo</th>
							<th scope="col">Application Date</th>

						</tr>
					</thead>
					<tbody>
					
					<%
					
					String jid=request.getParameter("jobid");
					int jobId=Integer.parseInt(jid);
					JobApplyDao jad=new JobApplyDao(ConnectionProvider.getDbConnection());
					List<ApplicantsData> appList=jad.getApplicantsData(jobId);
					
					int sno=1;
					for(ApplicantsData ad:appList)
					{
						
					%>
					
					
						<tr>

							<td><%= sno++ %></td>
							<td><%= ad.getFullName() %></td>
							<td><%= ad.getEmail() %></td>
							<td><%= ad.getMobno() %></td>
							<td><%= ad.getAppliedDate() %></td>
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




</body>
</html>