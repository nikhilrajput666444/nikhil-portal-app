<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="jakarta.tags.core"%>
     <%@ page import="model.Course" %>
    <%@ page import="dao.CourseDao" %>
    <%@ page import="dbconnection.ConnectionProvider" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- navbar started -->
<%@include file="navbar.jsp" %>
<!-- navbar ends -->

<%

CourseDao cdao=new CourseDao(ConnectionProvider.getDbConnection());

String cid=request.getParameter("cid");


Course c=cdao.getCourseById(Integer.parseInt(cid));


%>

<!-- body starts -->

	<div class="container my-2">

		<div class="row">

			<div class="col-12 col-md-6 offset-md-3">

				<!-- form started -->
				<form action="editcourse" method="post" enctype="multipart/form-data">
					<h1>Edit Course</h1>
                   <input type="hidden" name="courseid" value="<%= c.getCourseId() %>">
					<div class="form-group">
						<label for="exampleFormControlSelect1">Status</label> <select
							class="form-control" name="cstatus"
							id="exampleFormControlSelect1">
							<option value="<%= c.getStatus() %>" ><%= c.getStatus() %></option>
							<option value="started">started</option>
							<option value="Comming Soon">Comming Soon</option>
							<option value="Currently Not Available">Currently Not
								Available</option>
						</select>
					</div>

					<div class="form-group">
						<label for="cn">Course Name</label> <input type="text"
							name="cname" value="<%= c.getCourseName() %>" class="form-control" id="ln" aria-describedby="lnn">
					</div>


					<div class="form-group">
						<label for="exampleFormControlSelect1">Course Duration</label> <select
							class="form-control" name="cdur" id="exampleFormControlSelect1">
							<option value="<%=c.getCourseDuration() %>" ><%=c.getCourseDuration() %></option>
							<option value="3 Months">3 Months</option>
							<option value="6 Months">6 Months</option>
							<option value="12 Months">12 Months</option>
							<option value="24 Months (2 years)">24 Months (2 years)</option>
						</select>
					</div>

					<div class="form-group">
						<label for="cn">Course Fee</label> <input type="number"
							name="cfee" value="<%= c.getCourseFee() %>" class="form-control" id="ln" aria-describedby="lnn">
					</div>


					<div class="form-group">
						<label for="exampleFormControlTextarea1">Course Desc</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							name="cdesc" rows="3"><%= c.getCourseDesc() %></textarea>
					</div>
					<div class="form-group">
					
					  <input type="hidden" name="existingpic" value="<%=c.getCourseImg() %>">
					
							<img src="course_img/<%=c.getCourseImg() %>" height="90px" width="90px" class="m-2"  >
							<input type="file" name="cpic" class="form-control"
							id="ln" aria-describedby="lnn">
								<label for="cpic"><span style="color: red">*</span>upload
							course image</label> 

						<c:if test="${not empty sessionScope.imgmsg}">


							<div class="alert alert-warning" role="alert">${sessionScope.imgmsg}</div>

							<c:remove var="imgmsg" scope="session" />
						</c:if>
					</div>

					<div class="form-group">
					 <input type="hidden" name="existingpdf" value="<%=c.getPdfName() %>">
					
					<a href="course_pdf/<%=c.getPdfName() %>" target="_blank">View Pdf</a>
						<input
							type="file" name="cpdf" class="form-control"
							accept="application/pdf" id="ln" aria-describedby="lnn">
							<label for="cpdf">upload course syllabus</label> 
						<c:if test="${not empty sessionScope.pdfimg}">


							<div class="alert alert-warning" role="alert">${sessionScope.pdfimg}</div>

							<c:remove var="pdfimg" scope="session" />
						</c:if>
					</div>


					<button type="submit" class="btn btn-primary">Update</button>
				</form>
				<!-- form ended -->


				<!-- success or fail message for signup -->

				<c:if test="${not empty sessionScope.successmsg}">


					<div class="alert alert-success" role="alert">${sessionScope.successmsg}</div>

					<c:remove var="successmsg" scope="session" />
				</c:if>
				
					<c:if test="${not empty sessionScope.errormsg}">


					<div class="alert alert-warning" role="alert">${sessionScope.errormsg}</div>

					<c:remove var="errormsg" scope="session" />
				</c:if>


			</div>


		</div>

	</div>





	<!-- body ends -->





</body>
</html>