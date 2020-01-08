<% String title = "Register"; %>
<%@ include file="./header.jsp" %>
<%@ include file="./footer.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	

	<%
	String newId= (String)session.getAttribute("NewId");
	String firstName= (String)session.getAttribute("FirstName");
	String lastName= (String)session.getAttribute("LastName");
	String emailAddress= (String)session.getAttribute("EmailAddress");
	String newProgramCode= (String)session.getAttribute("NewProgramCode");
	//String newYear= (String)session.getAttribute("NewYear");

	if(newId == null){
		newId = "";
	}
	if(firstName == null){
		firstName = "";
	}
	if(lastName == null){
		lastName = "";
	}
	if(emailAddress == null){
		emailAddress = "";
	}
	if(newProgramCode == null){
		newProgramCode = "";
	}
	//if(newYear == null){
	//	newYear = "";
	//}


	%>

	<div>
		<center>
		<hr/>
		<hr/>
		<div><h1><em><font color="green">Durham College Mark Tracking - Register</font></em></h1></div>
		<hr>
		<img src = "images/dclogo.png">
		<hr>
		
		<form name="Input" id = "registrationForm" method="post" action="./RegisterServlet" >
		<table border=0 bgcolor="lightgreen" cellpadding=10 >
		<tr><td colspan="2" align="center"><%= message %></td></tr>
		<tr>
			<td><strong>Id</strong></td>
			<td><input type="text" name="Login" value="<%= newId %>" size=20></td>
		</tr>
		<tr>
			<td><strong>Password</strong></td>
			<td><input type="password" name="Password" value="<%/*= password */ %>" size=20></td>
		</tr>
		<tr>
			<td><strong>Confirm Password</strong></td>
			<td><input type="password" name="ConfirmPassword" value="" size=20></td>
		</tr>
		<tr>
			<td><strong>First Name</strong></td>
			<td><input type="text" name="FirstName" value="<%= firstName %>" size=20></td>
		</tr>
		<tr>
			<td><strong>Last Name</strong></td>
			<td><input type="text" name="LastName" value="<%= lastName %>" size=20></td>
		</tr>
		<tr>
			<td><strong>Email Address</strong></td>
			<td><input type="text" name="EmailAddress" value="<%= emailAddress %>" size=20></td>
		</tr>	

		<tr>
			<td><strong>Program Code</strong></td>
			<td><select name="ProgramCode" id="programCode" onchange="myFunction()">
				<option value="-1" selected>[choose your program]</option>
				<option value="CPA">CPA</option>
				<option value="ACCB">ACCB</option>
				<option value="CP">CP</option>
				<option value="RPN">RPN</option>
				<option value="CSTC">CSTC</option>
				<option value="CFND">CFND</option>				
				
			</select></td>

		</tr>
		<tr>
			<td><strong>Program Year</strong></td>
			<td><select name="Year" id="year">
				<option value="-1" selected>[Choose a year]</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
			</select></td>
		</tr>	

		<tr>
			<td><strong>Program Description</strong></td>
			<td><input type="text" name="ProgramDescription" id = "progDesc" disabled value="<%/*= courseDescription */%>" size=30></td>
		</tr>		
		
		</table>
		<table border=0 cellspacing=15 >
		<tr>
			<td><input type="submit" value = "Register"></td>
			<td><input type="reset" value = "Clear"></td>
		</tr>
		</table>
	</form>
		
		
		
		
		</center>

	</div>
	
	<script type="text/javascript">
		
		function myFunction(){
			var code = document.getElementById("programCode").value;

			

			// var hiddenProgCode = document.createElement("input");

			// hiddenProgCode.setAttribute("type", "hidden");

			// hiddenProgCode.setAttribute("name", "HiddenProgCode");

			// hiddenProgCode.setAttribute("value", code);

			// document.getElementById("registrationForm").appendChild(hiddenProgCode);

			// $.get('./CourseServlet', function(data) {
       		// 	 alert(data);
			// });
			
			if (code === "CPA"){
				document.getElementById("progDesc").value = "Computer Programmer Analyst";
			}
			else if (code === "ACCB"){
				document.getElementById("progDesc").value = "Accounting - Business";
			}
			else if (code === "CP"){
				document.getElementById("progDesc").value = "Computer Programmer";
			}	
			else if (code === "RPN"){
				document.getElementById("progDesc").value = "Registered Practical Nurse";
			}	
			else if (code === "CSTC"){
				document.getElementById("progDesc").value = "Computer Systems Technician";
			}		
			else if (code === "CFND"){
				document.getElementById("progDesc").value = "Computer Foundations";
			}	
			else{
				document.getElementById("progDesc").value = "";
			}	

			
			// var xmlhttp;
			// if(window.xmlhttpRequest){
			// 	xmlhttp = new XMLHttpRequest();
			// }else{
			// 	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			// }
	

			// xmlhttp.onreadystatechange = function(){
			// 	if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			// 		//Do stuff here when states changed
			// 	}
			// };

			// xmlhttp.open('POST', './CourseServlet', true);
			// xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			// xmlhttp.send(code);


		}
	
	</script>

	
	
<%@ include file="./footer.jsp" %>