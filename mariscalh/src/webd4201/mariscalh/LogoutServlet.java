/**
 *This class defines the servlet that handles the logout of a user
 *
 * @author Hector Luis Mariscal
 * @since 2019-02-27
 * @version 1.0 
 */

package webd4201.mariscalh;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LogoutServlet extends HttpServlet{
    /**
	 * Not sure what this is for
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Handles all of the log out logic
	 */
	public void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
					throws ServletException, IOException{
		
		 try
	        { 
	            // connect to database
	            Connection c = DatabaseConnect.initialize();
	            Student.initialize(c);
	            HttpSession session = request.getSession(true);
	            try 
	            {   

	                session.removeAttribute("student");
	                session.removeAttribute("grades");
	                session.removeAttribute("login");
			
	                session.setAttribute("message", "You have successfully logged out");
	                
	                // redirect the user to a JSP
	                response.sendRedirect("./login.jsp");
	            }catch( Exception e)
	            {
	                //new code == way better, if I do say so myself
	                //sending errors to the page thru the session
	                StringBuffer errorBuffer = new StringBuffer();
	                errorBuffer.append("<strong>You were not logged in.<br/>");
	                errorBuffer.append("Please log in.</strong>");
	             
	                session.setAttribute("message", errorBuffer.toString());
	                response.sendRedirect("./login.jsp");
	            
	            //for the first deliverable you will have to check if there was a problem
	            //with just the password, or login id and password
	            //this will require a special method e.g. public static boolean isExistingLogin(String arg);
	            }
	        }    
	   	 catch (Exception e) //not connected
	        {
	            System.out.println(e);
	            String line1="<h2>A network error has occurred!</h2>";
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
     * handles the error formatting strings
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
