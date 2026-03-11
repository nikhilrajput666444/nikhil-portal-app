<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- navbar started -->
<%@include file="navbar.jsp" %>
<!-- navbar ended -->

<!-- slider started -->
<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="banner_img/bg1.png" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="banner_img/bg2.png" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="banner_img/bg3.png" class="d-block w-100" alt="...">
    </div>
  </div>
 <button class="carousel-control-prev" type="button" data-target="#carouselExampleControls" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-target="#carouselExampleControls" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </button>
</div>
<!-- slider ends -->

<!-- main content starts -->
<div class="container">



<div class="row">

<%
CourseDao cdao=new CourseDao(ConnectionProvider.getDbConnection());
  List<Course> list= cdao.getLatestCourses();
  
  for(Course c:list)
  {
%>


<div class="col-md-4  col-12">
<div class="card m-4" >
  <img src="course_img/<%= c.getCourseImg() %>" width="150px" height="200px" class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title"><%= c.getCourseName() %></h5>
    <p class="card-text"><%= c.getCourseDesc() %></p>
    <p class="card-text">Fee :-<%= c.getCourseFee() %></p>
       <p class="card-text"><%= c.getStatus() %></p>
    <a  href="download?cid=<%= c.getCourseId() %>" class="btn btn-primary">Download Syllabus</a>
  </div>
</div>
</div>


<%
  }


%>



</div>

</div>

<!-- main content ends -->



</body>
</html>