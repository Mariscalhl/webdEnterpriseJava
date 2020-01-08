/**
 * Register Servlet
 * 
 * Description: This servlet handles the form validation for the register page and creates a new user in the database.
 * @author Hector Luis mariscal
 * @since 2019-03-25
 * @version 1.0
 */
package webd4201.mariscalh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Register Servlet
 * 
 * Description: This servlet handles the form validation for the register page and creates a new user in the database.
 * @author Hector Luis mariscal
 * @since 2019-04-10
 * @version 1.0
 *
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
            
            String newId = new String();
            String newPassword = new String();
            String confirmNewPassword = new String();
            String newFirstName = new String();
            String newLastName = new String();
            String newEmailAddress = new String();
            char newType = 's';
            
            Date newEnrolDate = new Date();
            Date newLastAccess = new Date();
            
            String newProgramCode = new String();
            String newProgramYear = new String();
            String newProgramDescription = new String();

            newId = request.getParameter("Login").trim();
            newPassword = request.getParameter("Password").trim();
            confirmNewPassword = request.getParameter("ConfirmPassword").trim();
            newFirstName = request.getParameter("FirstName").trim();
            newLastName = request.getParameter("LastName").trim();
            newEmailAddress = request.getParameter("EmailAddress").trim();
            newProgramCode = request.getParameter("ProgramCode").trim();
            newProgramYear = request.getParameter("Year").trim();
//            newProgramDescription = request.getParameter("ProgramDescription");
                
            long newKey = 0l;
            int newYear = 0;
			session.setAttribute("NewId", newId);
			session.setAttribute("FirstName", newFirstName);
			session.setAttribute("LastName", newLastName);
			session.setAttribute("EmailAddress", newEmailAddress);
			session.setAttribute("NewProgramCode", newProgramCode);
			session.setAttribute("NewYear", newProgramYear);
              
			StringBuffer messages = new StringBuffer();
			boolean anyErrors = false;
			
			if (newId.isEmpty()) {
				messages.append("You must enter an id <br/>");
				anyErrors = true;
				session.setAttribute("NewId", "");
			}else {
				try {
					 newKey = Long.parseLong(newId);
					 if(newKey < User.MINIMUM_ID_NUMBER) {
						 messages.append("Your Id must be between " + User.MINIMUM_ID_NUMBER + " and " + User.MAXIMUM_ID_NUMBER +   ".<br/>");
						 anyErrors = true;
						 session.setAttribute("NewId", "");
					 }else if(newKey > User.MAXIMUM_ID_NUMBER) {
						 messages.append("Your Id must be between " + User.MINIMUM_ID_NUMBER + " and " + User.MAXIMUM_ID_NUMBER +   ".<br/>");
						 anyErrors = true;
						 session.setAttribute("NewId", "");
					 }
			 
					 boolean keyExists = UserDA.isExistingLogin(newKey);
					 
					 if (keyExists) {
						messages.append("Your Id is already taken. Please choose another one.<br/>");
						anyErrors = true;
						session.setAttribute("NewId", "");
					 }
						 
				}catch(NumberFormatException nfe) {
					messages.append("You must enter a " + User.ID_NUMBER_LENGTH + " digit number id between " + CollegeInterface.MINIMUM_ID_NUMBER + " and "
							+ CollegeInterface.MAXIMUM_ID_NUMBER + "<br/>");
					anyErrors = true;
					session.setAttribute("NewId", "");
				}
				
			}
			if (newPassword.isEmpty()) {
				messages.append("You must enter a password <br/>");
				anyErrors = true;
			}else if (!newPassword.equals(confirmNewPassword)) {
					messages.append("Your new password and confirm password do not match.<br/>");
					anyErrors = true;
			}else if (newPassword.length() < User.MINIMUM_PASSWORD_LENGTH){
				messages.append("Your new password must be at least " + User.MINIMUM_PASSWORD_LENGTH + " characters long.<br/>");
				anyErrors = true;
			}else if (newPassword.length() > User.MAXIMUM_PASSWORD_LENGTH) {
				messages.append("Your new password must be less than " + User.MAXIMUM_PASSWORD_LENGTH + " characters long.<br/>");
				anyErrors = true;
			}
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
			}else if (newProgramCode.equals("CPA")) {
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
				newYear = Integer.parseInt(newProgramYear);
				//try catch numberformatexception for this
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
					String hashedPassword = User.hash(newPassword, User.HASH_ALGO).toLowerCase();					
					Student newStudent = new Student(newKey, hashedPassword, newFirstName, newLastName, newEmailAddress, newLastAccess, newEnrolDate, true, 
							newType, newProgramCode, newProgramDescription, newYear);
					newStudent.create();
					
	                session.setAttribute("student", newStudent);
					//empty out any errors if there were some
	                session.setAttribute("message", "");
	                // redirect the user to a JSP
	                response.sendRedirect("./dashboard.jsp");
				} catch (Exception e) {
//					System.out.println("Problem occurred attempting to create student");
					messages.append("Problem occurred attempting to create a new Student user <br/>");
				}

			}else {
				session.setAttribute("message", messages.toString());
				response.sendRedirect("./register.jsp");
			}
			
        }catch (Exception e) //not connected
        {
            System.out.println(e);
            String line1="<h2>A netwrk error has occurred</h2>";
            String line2="<p>Please notify your system " +
                                                    "administrator and check log. "+e.toString()+"</p>";
            formatErrorPage(line1, line2,response);
            
        }
	}
	
	/**
	 * Handles the get and post modes
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
