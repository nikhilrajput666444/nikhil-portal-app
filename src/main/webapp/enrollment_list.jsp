<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Course"%>
<%@ page import="dao.CourseDao"%>
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
							<th scope="col">SNO</th>
							<th scope="col">username</th>
							<th scope="col">email</th>
							<th scope="col">mobile</th>
						</tr>
					</thead>
					<tbody>

						<%
  
  List<User> enrolledUser=(List<User>)request.getAttribute("allenrolleduser");
  
  if(enrolledUser==null || enrolledUser.isEmpty())
  {
  %>

						<tr>
							<td>No User Enrolled till Now in this Course !!!</td>

						</tr>

						<%
  }
  else
  {
	  int sno=1;
	  for(User u:enrolledUser)
	  {
  
  %>


						<tr>
						<td><%= sno++ %></td>
							<td><%= u.getUserfirstname() %></td>
							<td><%= u.getUserEmail() %></td>
							<td><%= u.getMobilenumber() %></td>
						</tr>
	<%
    
	  }
  }
    %>



					</tbody>
				</table>
				<!-- data table ends -->
			</div>

</body>
</html>