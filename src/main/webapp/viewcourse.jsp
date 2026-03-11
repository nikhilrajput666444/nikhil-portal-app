<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="jakarta.tags.core"%>
     
     <%@ page import="model.Course" %>
    <%@ page import="dao.CourseDao" %>
    <%@ page import="dbconnection.ConnectionProvider" %>
     <%@ page import="java.util.*" %>
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
<%@include file="navbar.jsp" %>
<!-- navbar ends -->

<!-- course list started -->

<div class="container-fluid ">
<div class="row">

<div class="col-12 col-md-10 offset-md-1">

<!-- data table starts -->
<table class="table">
<thead>
<tr>

<th scope="col">Course Image</th>
      <th scope="col">Course Name</th>
      <th scope="col">Status</th>
       <th scope="col">Edit</th>
        <th scope="col">Delete</th>
         <th scope="col">Enrollments</th>


</tr>


</thead>
<tbody>
<%
  
  CourseDao cdao=new CourseDao(ConnectionProvider.getDbConnection());
  List<Course> clist=cdao.getAllCourse();
  
  for(Course c:clist)
  {
  %>
<tr>
      <td><img src="course_img/<%= c.getCourseImg() %>" class="img-thumbnail" width="50px" height="50px"   alt="..."></td>
      <td><%= c.getCourseName() %></td>
      <td><%= c.getStatus() %></td>
      <td><a href="editcourse.jsp?cid=<%= c.getCourseId() %>" class="btn btn-warning">Edit</a></td>
      <td><a href="delete?cid=<%= c.getCourseId() %>" class="btn btn-danger">Delete</a></td>
      <td><a href="viewenrollment?cid=<%= c.getCourseId() %>" class="btn btn-success">View Enrollments</a></td>
    </tr>
<%
  }
  
  %>
</tbody>
</table>


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