/**
 *This is the change password servlet that handles the changing of a password in the website.
 *
 * @author Hector Luis Mariscal
 * @since 2019-01-15
 * @version 1.0 
 */
package webd4201.mariscalh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePasswordServlet extends HttpServlet {
	/**
	 * Not sure what this is for
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * constant that holds the hashing algorithm that the password on this site is hashed to.
	 */
	private static final String HASH_ALGO = "sha1";

	/**
	 * Handles all of the password change logic
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// connect to database
			Connection c = DatabaseConnect.initialize();
			Student.initialize(c);
			HttpSession session = request.getSession(true);
			String currentPasswordDB = new String();
			String currentPassword = new String();
			String newPassword = new String();
			String confirmNewPassword = new String();
			long key = 0l;
			StringBuffer errorBuffer = new StringBuffer();

			try { 

				Student aStudent = (Student) session.getAttribute("student");

				key = aStudent.getId();
				currentPasswordDB = aStudent.getPassword();
				
				currentPassword = request.getParameter("CurrentPassword");
				newPassword = request.getParameter("NewPassword");
				confirmNewPassword = request.getParameter("ConfirmNewPassword");

				StringBuffer messages = new StringBuffer();
				boolean anyErrors = false;
				if (currentPassword.isEmpty()) {
					messages.append("You must enter your existing password <br/>");
					anyErrors = true;
				} else {
					String hashCurrentPassword = User.hash(currentPassword, HASH_ALGO).toLowerCase();
					
					if (!hashCurrentPassword.equals(currentPasswordDB)) {
						messages.append("Your entered password does not match our records<br/>" + currentPasswordDB);
						anyErrors = true;
					}
				}
				if (!newPassword.equals(confirmNewPassword)) {
					messages.append("Your new password and confirm password do not match.<br/>");
					anyErrors = true;
				}else if (newPassword.length() == 0){
					messages.append("You must enter a new password and confirm password.<br/>");
					anyErrors = true;
				}else if (newPassword.length() < User.MINIMUM_PASSWORD_LENGTH){
					messages.append("Your new password must be at least " + User.MINIMUM_PASSWORD_LENGTH + " characters long.<br/>");
					anyErrors = true;
				}else if (newPassword.length() > User.MAXIMUM_PASSWORD_LENGTH) {
					messages.append("Your new password must be less than " + User.MAXIMUM_PASSWORD_LENGTH + " characters long.<br/>");
					anyErrors = true;
				}
					
						
				if(!anyErrors) {
								String hashNewPassword = User.hash(newPassword, HASH_ALGO).toLowerCase();
								Student.changePassword(key, hashNewPassword);
								
								// empty out any errors if there were some
								session.setAttribute("message", "Password Successfully Changed");
								// redirect the user to a JSP
								response.sendRedirect("./dashboard.jsp");
				}else {
						session.setAttribute("message", messages.toString());
						response.sendRedirect("./changePassword.jsp");
				}
				

			} catch (NotFoundException nfe) {
				// new code == way better, if I do say so myself
				// sending errors to the page thru the session
				errorBuffer = new StringBuffer();
				errorBuffer.append("<br/><strong>Your password does not match our records.<br/>");
				errorBuffer.append("Please try again.</strong>");

				session.setAttribute("message", errorBuffer.toString());
				response.sendRedirect("./changePassword.jsp");

				// for the first deliverable you will have to check if there was a problem
				// with just the password, or login id and password
				// this will require a special method e.g. public static boolean
				// isExistingLogin(String arg);
			}
		} catch (Exception e) // not connected
		{
			System.out.println(e);
			String line1 = "<h2>A network error has occurred!</h2>";
			String line2 = "<p>Please notify your system " + "administrator and check log. " + e.toString() + "</p>";
			formatErrorPage(line1, line2, response);

		}
	}

	/**
	 * Handles the get and post modes
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * handles the error formatting strings
	 * 
	 * @param first
	 * @param second
	 * @param response
	 * @throws IOException
	 */
	public void formatErrorPage(String first, String second, HttpServletResponse response) throws IOException {
		PrintWriter output = response.getWriter();
		response.setContentType("text/html");
		output.println(first);
		output.println(second);
		output.close();
	}
}
