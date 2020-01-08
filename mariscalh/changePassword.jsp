<% String title = "changePassword"; %>
<%@ include file="./header.jsp" %>


<%
	Student loggedInUser= (Student)session.getAttribute("student"); 
	if(loggedInUser == null){ 
		session.setAttribute("message", "Please log in to access the dashboard"); 
		response.sendRedirect("./login.jsp"); 
	} 
%>
	<div>
		<center>
		<hr/>
		<hr/>
		<div><h1><em><font color="green">Durham College Mark Tracking - Update</font></em></h1></div>
		<hr>
		<img src = "images/dclogo.png">
		<hr>
		
		<form name="Input" method="post" action="./ChangePasswordServlet" >
		<!-- action="http://localhost:81/Bradshaw/LoginServlet" > -->
		<table border=0 bgcolor="lightgreen" cellpadding=10 >
		<tr><td colspan="2" align="center"><%= message %></td></tr>
		<tr>
			<td><strong>Current Password</strong></td>
			<td><input type="password" name="CurrentPassword"  size=20></td>
		</tr>
		<tr>
			<td><strong>New Password</strong></td>
			<td><input type="password" name="NewPassword" size=20></td>
		</tr>
		<tr>
			<td><strong>Confirm New Password</strong></td>
			<td><input type="password" name="ConfirmNewPassword"  size=20></td>
		</tr>
		</table>
		<table border=0 cellspacing=15 >
		<tr>
			<td><input type="submit" value = "Log In"></td>
			<td><input type="reset" value = "Clear"></td>
		</tr>
		</table>
	</form>
		
		</center>
		<p>
		
		</p>
	</div>
	
<%@ include file="./footer.jsp" %>