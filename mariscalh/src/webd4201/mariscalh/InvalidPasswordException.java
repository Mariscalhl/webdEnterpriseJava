 /**
 * InvalidPasswordException - this file contains extends the generic Exception class so that
 * 			            we have a custom Exception.
 * @author Hector Mariscal
 * @version 1.0 (01 February 2019)
 * @since 1.0
 */ 
package webd4201.mariscalh;

@SuppressWarnings("serial")
/**
 * Exception Handling for the invalid password exception
 */
public class InvalidPasswordException extends Exception {
    public InvalidPasswordException()
    { super();}
    /**
     * Invalid password exception handling
     * @param message 
     */
    public InvalidPasswordException(String message)
    { super(message);}
}
