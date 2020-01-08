<%@ page import = "java.util.*" %>
<%@ page import="webd4201.mariscalh.*" %> 

<%
	String message = (String)session.getAttribute("message");
	if(message == null) { 
		message = "";  
	}else{ 
		session.removeAttribute("message");  
	}
%>

<%

	String href1 = "login.jsp"; 
	String link1 = "Login"; 
	String href2 = "register.jsp"; 
	String link2 = "Register"; 
	String href3 = "index.jsp";
	String link3 = "Home Page";
	String href4 = "";
	String link4 = "";
	if((Student) session.getAttribute("student") != null) { 
		href1 = "./Logout"; 
		link1 = "Logout"; 
		href2 = "update.jsp"; 
		link2 = "Update"; 
		href3 = "dashboard.jsp";
		link3 = "Dashboard";
		href4 = "changePassword.jsp";
		link4 = "Change Password";
		
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" 
lang="en"> 

<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%= title %></title> 
<!-- <link href="./css/durham.css" rel="stylesheet" type="text/css" /> -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {font-family: "Lato", sans-serif}
.mySlides {display: none}
</style>
</head> 



<body> 

<!-- Navbar -->
<div class="w3-top">
  <div class="w3-bar w3-black w3-card">
    <a href="<%= href1 %>" class="w3-bar-item w3-button w3-padding-large"><%= link1 %></a>
    <a href="<%= href2 %>" class="w3-bar-item w3-button w3-padding-large w3-hide-small"><%= link2 %></a>
    <div class="w3-dropdown-hover w3-hide-small">
      <button class="w3-padding-large w3-button" title="More">MORE <i class="fa fa-caret-down"></i></button>     
      <div class="w3-dropdown-content w3-bar-block w3-card-4">
		<a href="<%= href3 %>" class="w3-bar-item w3-button"><%= link3 %></a>
        <a href="<%= href4 %>" class="w3-bar-item w3-button"><%= link4 %></a>
        <a href="#" class="w3-bar-item w3-button">Nothing else lol</a>
      </div>
    </div>
  </div>
</div>


