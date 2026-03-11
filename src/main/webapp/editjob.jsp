<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


<!-- navbar started -->
	<%@include file="navbar.jsp"%>
	<!-- navbar ends -->


	<%

String jobId=request.getParameter("jid");

JobDao jdao=new JobDao(ConnectionProvider.getDbConnection());
Job j=jdao.getJobsDataById(Integer.parseInt(jobId));

%>



	<!-- body starts -->

	<div class="container my-2">

		<div class="row">

			<div class="col-12 col-md-6 offset-md-3">

				<!-- form started -->
				<form action="updatejob" method="post">
					<h1>Edit Job</h1>

					<input type="hidden" name="jobid" value="<%= j.getJobid()%>">


					<div class="form-group">
						<label for="cn">Title</label> <input type="text" name="title"
							class="form-control" id="ln" value="<%= j.getTitle() %>"
							aria-describedby="lnn">
					</div>



					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="inputState">Location</label> <select id="inputState"
								class="form-control" name="location">
								<option value="<%= j.getLocation() %>"><%= j.getLocation() %></option>
								<option value="pune">Pune</option>
								<option value="bengaluru">bengaluru</option>
								<option value="hyderabad">hyderabad</option>
							</select>
						</div>
						<div class="form-group col-md-4">
							<label for="inputState">Category</label> <select id="inputState"
								class="form-control" name="category">
								<option value="<%= j.getCategory() %>"><%= j.getCategory()%></option>
								<option value="IT">It</option>
								<option value="NON-IT">Non-It</option>
							</select>
						</div>
						<div class="form-group col-md-4">
							<label for="inputState">Status</label> <select id="inputState"
								class="form-control" name="status">
								<option value="<%= j.getStatus()%>"><%= j.getStatus() %></option>
								<option value="active">Active</option>
								<option value="inactive">In-Active</option>
							</select>
						</div>
					</div>



					<div class="form-group">
						<label for="exampleFormControlTextarea1">Job Desc</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							name="jobdesc" rows="3"><%= j.getJobdesc() %></textarea>
					</div>




					<button type="submit" class="btn btn-primary">Update</button>
				</form>


				
				<!-- form ended -->
			</div>
		</div>
	</div>


</body>
</html>