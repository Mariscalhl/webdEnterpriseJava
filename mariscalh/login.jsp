<% String title = "Durham College Mark Tracking Login"; %>
<%@ include file="./header.jsp" %>



<%  //String message = (String)session.getAttribute("message"); 
	String login = (String)session.getAttribute("login");
	String password = (String)session.getAttribute("password");
	if(message == null)
		message="";
	if(login == null)
		login = "";
	if(password == null)
		password = "";
%>

	<center>
	<hr>
	<img src = "images/dclogo.png">
	<hr>
	<h2>Please log in</h2>
	<p>Enter your login information below.<br>
	   If you are not a Durham College member, please return to the
	   <a href="index.html">Durham College Mark Tracking</a> home page.</p>
	<p>
	   If you want to become a member on our system, please <a href="register.jsp">register</a>.</p>
	<form name="Input" method="post" action="./Login" >
		<!-- action="http://localhost:81/Bradshaw/LoginServlet" > -->
		<table border=0 bgcolor="lightgreen" cellpadding=10 >
		<tr><td colspan="2" align="center"><%= message %></td></tr>
		<tr>
			<td><strong>Login Id</strong></td>
			<td><input type="text" name="Login" value="<%= login %>" size=20></td>
		</tr>
		<tr>
			<td><strong>Password</strong></td>
			<td><input type="password" name="Password" value="<%= password %>" size=20></td>
		</tr>
		</table>
		<table border=0 cellspacing=15 >
		<tr>
			<td><input type="submit" value = "Log In"></td>
			<td><input type="reset" value = "Clear"></td>
		</tr>
		</table>
	</form>
	Please wait after pressing <strong>Log in</strong>
	while we retrieve your records from our database.<br>
	<em>(This may take a few moments)</em>
	</center>

<%@ include file="./footer.jsp" %>