<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
    <%@page import="helper.Helper" %>
    <%@page import="model.User"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
</head>
<body>

<!-- navbar starts -->


<nav class="navbar navbar-expand-lg  navbar-dark mybg">
  <a class="navbar-brand" href="index.jsp"><i class="fa-brands fa-dev"></i> ByteHub</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp"><i class="fa-solid fa-house"></i> <span class="sr-only">(current)</span></a>
      </li>
      
       <li class="nav-item active"><a class="nav-link"
					href="viewcourses">
					View Courses
						</a></li>
						
						 <li class="nav-item active"><a class="nav-link"
					href="viewalljobs.jsp">
					Career
						</a></li>

				<c:if test="${sessionScope.userobj.role == 'admin'}">
					<li class="nav-item active"><a class="nav-link"
						href="admin.jsp"> Dashboard</a></li>
				</c:if>
      
      
      
      </ul>
    
    
    
    
    
    
    
    
    
     <ul class="navbar-nav mr-3">
    <li class="nav-item ">
    
    <!-- profile pic starts -->
      
      
      <c:if test="${not empty sessionScope.userobj }">
      
      <div class="media">
      
      
      
      
      
  <img src="profile_img/<%=Helper.getUserProfileImage(session) %>"  height="35px" width="35px" class="..." alt="...">
  <div class="media-body">
  
  
       
  
    
  
         <%
         User u = (User)session.getAttribute("userobj");
         
         if(session !=null && u!=null && u.getUserfirstname()!=null)
         {
         
         %>
  
    
                 <a class = "nav-link"   href="#"><%=u.getUserfirstname() %></a>
  
          <%
          
          
         }
          %>
  
  
   
  </div>
</div>
<!--  <a class="nav-link  btn btn-outline-success mr-3" href="logout"><i class="fa-solid fa-lock-open mr-2"></i>logout</a>-->

<li class="nav-item "><a
							class="nav-link btn btn-outline-success mr-2" href="logout">
								<i class="fa-solid fa-lock-open"></i>logout
						</a></li>
</c:if>
<!-- profile pic ends -->
        
      </li>
       </ul>
    
    
    
    
    
       
      
       <c:if test="${ empty sessionScope.userobj }">
       <a class="nav-link  btn btn-outline-success mr-3" href="login.jsp">login</a>
        <a class="nav-link  btn btn-outline-success mr-3" href="signup.jsp"><i class="fa-solid fa-person-circle-plus mr-2"></i>signup</a>
       </c:if>
       
       
    
      
        
   
  </div>
</nav>



<!-- nav bar ends -->






<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>