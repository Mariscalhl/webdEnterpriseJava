<% String title = "Home Page"; %>
<%@ include file="./header.jsp" %>



<div>
	
	<center>
	<hr/>
	<hr/>
	<div><h1><em><font color="green">Durham College Mark Tracking</font></em></h1></div>
	<hr>
	<img src = "images/dclogo.png">
	<hr></center>]
	<center>
	<p>
	This Web Application supports the tracking of grades for Durham College students. 
	This app was created for the WEB4201 Web Development Enterprise class using Java 
	classes, JDBC, POSTGRESQL Database, HTML, JSP.
	</p>
	<strong>Durham College Mark Tracking provides:</strong><br>
	</center>

	<table align="center" cellspacing="7" cellpadding="7">
	<tr>
		<td><li>
			Mark Tracking
		</li></td>
		<td><li>
			Login Functionality
		</li></td>
	</tr>
	<tr>
		<td><li>
			Registration 
		</li></td>
		<td><li>
			Update Functionality
		</li></td>
	</tr>
	</table>
	<center><br>If you are a Durham College member, please log in.
	<table align="center" bgcolor="lightgreen">
	<tr>
		<td width="100" align="Center">
			<a href="login.jsp">
			<strong><font size="+1">Log In</font></strong></a>
		</td>
	</tr>
	</table>
</div>

<%@ include file="./footer.jsp" %>