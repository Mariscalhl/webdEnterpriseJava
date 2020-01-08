/**
 * Update Servlet
 * 
 * Description: This servlet handles the form validation for the Update page and creates a new user in the database.
 * @author Hector Luis mariscal
 * @since 2019-0-15
 * @version 1.0
 */
package webd4201.mariscalh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Handles the doPost server requests
	 */
	public void doPost(HttpServletRequest request,
            HttpServletResponse response) 
	throws ServletException, IOException{
		
		
		try
        { 
            // connect to database
            Connection c = DatabaseConnect.initialize();
            User.initialize(c);
            Student.initialize(c);
            
            HttpSession session = request.getSession(true);
            
        	Student studentToBeUpdated = (Student)session.getAttribute("student");
        	
        	if(studentToBeUpdated == null){ 
        		session.setAttribute("message", "Please log in to access the update page"); 
        		response.sendRedirect("./login.jsp"); 
        	} 
            String newFirstName = new String();
            String newLastName = new String();
            String newEmailAddress = new String();

            String newProgramCode = new String();
            String newProgramYear = new String();
            String newProgramDescription = new String();

            newFirstName = request.getParameter("FirstName").trim();
            newLastName = request.getParameter("LastName").trim();
            newEmailAddress = request.getParameter("EmailAddress").trim();
            newProgramCode = request.getParameter("ProgramCode").trim();
            newProgramYear = request.getParameter("Year").trim();
//            newProgramDescription = request.getParameter("ProgramDescription");
                
            int newYear = 0;
			session.setAttribute("FirstName", newFirstName);
			session.setAttribute("LastName", newLastName);
			session.setAttribute("EmailAddress", newEmailAddress);
			session.setAttribute("NewProgramCode", newProgramCode);
			session.setAttribute("NewYear", newProgramYear);
              
			StringBuffer messages = new StringBuffer();
			boolean anyErrors = false;
			
//			if (newId.isEmpty()) {
//				messages.append("You must enter an id <br/>");
//				anyErrors = true;
//				session.setAttribute("NewId", "");
//			}else {
//				try {
//					 newKey = Long.parseLong(newId);
//					 if(newKey < User.MINIMUM_ID_NUMBER) {
//						 messages.append("Your Id must be between " + User.MINIMUM_ID_NUMBER + " and " + User.MAXIMUM_ID_NUMBER + " you entered: " + newKey  + ".<br/>");
//						 anyErrors = true;
//						 session.setAttribute("NewId", "");
//					 }else if(newKey > User.MAXIMUM_ID_NUMBER) {
//						 messages.append("Your Id must be between " + User.MINIMUM_ID_NUMBER + " and " + User.MAXIMUM_ID_NUMBER + " you entered: " + newKey  + ".<br/>");
//						 anyErrors = true;
//						 session.setAttribute("NewId", "");
//					 }
//			 
//					 boolean keyExists = UserDA.isExistingLogin(newKey);
//					 
//					 if (keyExists) {
//						messages.append("Your Id is already taken. Please choose another one. You entered:"+ newKey + "<br/>");
//						anyErrors = true;
//						session.setAttribute("NewId", "");
//					 }
//						 
//				}catch(NumberFormatException nfe) {
//					messages.append("You must enter a " + User.ID_NUMBER_LENGTH + " digit number id between " + CollegeInterface.MINIMUM_ID_NUMBER + " and "
//							+ CollegeInterface.MAXIMUM_ID_NUMBER + " you entered: " + newKey  + "<br/>");
//					anyErrors = true;
//					session.setAttribute("NewId", "");
//				}
//				
//			}
			if (newFirstName.isEmpty()) {
				messages.append("You must enter your first name <br/>");
				anyErrors = true;
				session.setAttribute("FirstName", "");
			}
			if (newLastName.isEmpty()) {
				messages.append("You must enter your first name <br/>");
				anyErrors = true;	
				session.setAttribute("LastName", "");
			}
			if (newProgramCode.equals("-1")) {
				messages.append("You must select your program code <br/>");
				anyErrors = true;	
				session.setAttribute("NewProgramCode", "-1");
			}	

			if (newProgramCode.equals("CPA")) {
				newProgramDescription = "Computer Programmer Analyst";	
			}else if(newProgramCode.equals("ACCB")){
				newProgramDescription = "Accounting - Business";	
			}else if(newProgramCode.equals("CP")) {
				newProgramDescription = "Computer Programmer";
			}else if(newProgramCode.equals("RPN")) {
				newProgramDescription = "Registered Practical Nurse";
			}else if(newProgramCode.equals("CSTC")) {
				newProgramDescription = "Computer Systems Technician";
			}else if(newProgramCode.equals("CFND")) {
				newProgramDescription = "Computer Foundations";
			}
			
			if (newProgramYear.equals("-1")) {
				messages.append("You must select your program year <br/>");
				anyErrors = true;
				session.setAttribute("NewYear", "-1");
			}else {
				try {
					newYear = Integer.parseInt(newProgramYear);
					//try catch numberformatexception for this
				}catch(NumberFormatException nfeee) {
					messages.append("You must choose a year <br/>");
					anyErrors = true;
					session.setAttribute("NewYear", "-1");
	}
			}
			
			if (newEmailAddress.isEmpty()) {
				messages.append("You must enter your email address <br/>");
				anyErrors = true;
				session.setAttribute("EmailAddress", "");
			}else if (!User.isValidEmailAddress(newEmailAddress)) {
				anyErrors = true;
				messages.append(newEmailAddress + " is not a valid email <br/>");
				session.setAttribute("EmailAddress", "");
			}
			
			
			if (!anyErrors) {
				try {				
					Student newStudent = new Student(studentToBeUpdated.getId(), studentToBeUpdated.getPassword(), newFirstName, newLastName, newEmailAddress, studentToBeUpdated.getLastAccess(), studentToBeUpdated.getEnrolDate(), true, 
							studentToBeUpdated.getType(), newProgramCode, newProgramDescription, newYear);
					newStudent.update();
					StudentDA.update(newStudent);
					
	                session.setAttribute("student", newStudent);
					//empty out any errors if there were some
	                session.setAttribute("message", "Update Successful!");
	                // redirect the user to a JSP
	                response.sendRedirect("./dashboard.jsp");
				} catch (Exception e) {
//					System.out.println("Problem occurred attempting to create student");
					messages.append("Problem occurred attempting to create a new Student object"+ e + "<br/>");
					response.sendRedirect("./update.jsp");
				}

			}else {
				session.setAttribute("message", messages.toString());
				response.sendRedirect("./update.jsp");
			}
			
        }catch (Exception e) //not connected
        {
            System.out.println(e);
            String line1="<h2>A netwrk error has occurred</h2>";
            String line2="<p>Please notify your system " +
                                                    "administrator and check log!!!!!!!!!!. "+e.toString()+"</p>";
            formatErrorPage(line1, line2,response);
            
        }
	}
	
	/**
	 * Handles the doGet server requests
	 */
    public void doGet(HttpServletRequest request,
                            HttpServletResponse response)
                                    throws ServletException, IOException {
        doPost(request, response);
    }
    /**
     * handling the error formatting page
     * @param first
     * @param second
     * @param response
     * @throws IOException
     */
    public void formatErrorPage( String first, String second,
            HttpServletResponse response) throws IOException
    {
        PrintWriter output = response.getWriter();
        response.setContentType( "text/html" );
        output.println(first);
        output.println(second);
        output.close();
    }
    
}
