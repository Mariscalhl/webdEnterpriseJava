/**
 *This class defines servlet that handles the login of a user
 *
 * @author Hector Luis Mariscal
 * @since 2019-02-26
 * @version 1.0 
 */

package webd4201.mariscalh;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class LoginServlet extends HttpServlet {

    /**
	 * Not sure what this is for
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String HASH_ALGO = "sha1";
	/**
	 * Handles all of the log in logic
	 */
	public void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
					throws ServletException, IOException
    {
	   	  
	   	//CREATE A TEXT FILE 
	   	String logFile = "./test_log.log";
	    File f = new File(logFile);
	    PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(f)), true);
	    System.setErr(printStream);
	    System.setOut(printStream);
	    System.out.println("Log started: " + new java.util.Date());
	    
        try
        { 
            // connect to database
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);
            String login = new String();
            String password = new String();
            long key = 0l;
            StringBuffer errorBuffer = new StringBuffer();
            try 
            {   // retrieve data from DB
            	session.setAttribute("login", "");
                login = request.getParameter("Login"); //this is the name of the text input box on the form
                password = request.getParameter("Password");
                key = Long.parseLong(login);
                String hashPassword = User.hash(password, HASH_ALGO).toLowerCase();
                Student aStudent = Student.authenticate(key, hashPassword); //attempt to find the Student, this could cause a NotFoundException
                if(aStudent.getMarks() != null) {
                	double weightedGpa = Mark.weightedGpa(aStudent.getMarks());
                	session.setAttribute("weightedGpa", weightedGpa);
                }else {
                	session.setAttribute("weightedGpa", 0.0);
                }
                session.setAttribute("student", aStudent);
                
                //session.setAttribute("grades", aStudent.getMarks());
				//empty out any errors if there were some
                session.setAttribute("message", "");
                // redirect the user to a JSP
                response.sendRedirect("./dashboard.jsp");
            }catch( NumberFormatException nfe)
            {
                errorBuffer.append("<strong>Your sign in ID MUST be a number, you entered: ");
                errorBuffer.append(login);
                errorBuffer.append("<br/>");
                session.setAttribute("message", errorBuffer.toString());
          	  	session.setAttribute("login", "");
          	  	response.sendRedirect("./login.jsp");
            
            }catch( NotFoundException nfe)
            {
                //new code == way better, if I do say so myself
                //sending errors to the page thru the session
                errorBuffer = new StringBuffer();
                errorBuffer.append("<br/><strong>Your sign in information is not valid.<br/>");
                errorBuffer.append("Please try again.</strong>");
                try{
                	Student.retrieve(key);
                	session.setAttribute("login", login);
                }catch(NotFoundException nfe2)
                {
                	  session.setAttribute("login", "");
                }
                session.setAttribute("message", errorBuffer.toString());
                response.sendRedirect("./login.jsp");
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