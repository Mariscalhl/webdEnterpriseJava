 /**
 * InvalidNameException - this file contains extends the generic Exception class so that
 * 			            we have a custom Exception.
 * @author Hector Mariscal
 * @version 1.0 (01 February 2019)
 * @since 1.0
 */ 
package webd4201.mariscalh;

@SuppressWarnings("serial")
public class InvalidNameException extends Exception {
    public InvalidNameException()
    { super();}
    /**
     * exception handling for the invalidNameException
     * @param message 
     */
    public InvalidNameException(String message)
    { super(message);}
}
