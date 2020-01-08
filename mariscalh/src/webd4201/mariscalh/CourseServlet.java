/**
 *This class handles the courses available for the college
 *
 * @author Hector Luis Mariscal
 * @since 2019-03-20
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

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,
            HttpServletResponse response) 
	throws ServletException, IOException{
		
		
		try
        { 
            // connect to database
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);
            StringBuffer errorBuffer = new StringBuffer();
            String programCode = new String();
            String programDescription = new String();
		
            programCode = request.getParameter("HiddenProgCode");
            
            //courseCode = (String)session.getAttribute("code");
            
            programDescription = StudentDA.getProgramDescription(programCode);
            
            
            session.setAttribute("programDescription", programDescription);
            
		
		
		
        }
		catch (Exception e) //not connected
        {
            System.out.println(e);
            String line1="<h2>A netwrk error has occurred!!!!!!!!!!!!</h2>";
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
     * method for formatting an error page
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
