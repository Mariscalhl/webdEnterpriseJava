<% String title = "Dashboard"; %>
<%@ include file="./header.jsp" %>
<%@ page import = "java.lang.Math" %>

<%
	Student loggedInUser= (Student)session.getAttribute("student"); 
	//Mark loggedInStudentMarks = (Mark)loggedInUser.getMarks();
	double weightedGpa = (double)session.getAttribute("weightedGpa");

	if(loggedInUser.getMarks() == null){
		message = "You do not have any marks yet.";
	}


	if(loggedInUser == null){ 
		session.setAttribute("message", "Please log in to access the dashboard"); 
		response.sendRedirect("./login.jsp"); 
	} 

%>
	<div>
		<center>
		<hr/>
		<hr/>
		<div><h1><em><font color="green">Durham College Mark Tracking - Dashboard</font></em></h1></div>
		<hr>
		<img src = "images/dclogo.png">
		<hr></center>
		<p>
		Welcome <%= loggedInUser.getFirstName() %> to your dashboard!
		</p>

		<!-- <c:forEach var="grades" items="${student.getMarks()}">
    		<c:out value="${grades}"/>
		</c:forEach> -->

			<% if(loggedInUser.getMarks() != null) {%>
		<table border = 1>
			<thead>
				<tr>
					<th>Course Code</th>
					<th>Course Name</th>
					<th>Grade</th>
					<th>GPA Weighting</th>
				</tr>
			</thead>	
			<tbody>
				<% for (int i = 0; i < loggedInUser.getMarks().size(); i++){ %>
					<tr>
						<td>
							<%= 
							loggedInUser.getMarks().get(i).getCourseCode()
							%>
						</td>
						<td>
							<%= 
							loggedInUser.getMarks().get(i).getCourseName()
							%>
						</td>
						<td>
							<%= 
							loggedInUser.getMarks().get(i).getResult()
							%>
						</td>
						<td>
							<%= 
							loggedInUser.getMarks().get(i).getGpaWeighting()
							%>
						</td>
					</tr>	
				<% } %>
			<% } %>
			</tbody>
		</table>


		<p>Cumulative GPA: <%= Mark.GPA.format(weightedGpa) %></p>

		<%= message %>
	</div>

<%@ include file="./footer.jsp" %>