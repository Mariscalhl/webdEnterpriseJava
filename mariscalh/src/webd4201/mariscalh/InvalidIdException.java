 /**
 * InvalidIdException - this file contains extends the generic Exception class so that
 * 			            we have a custom Exception.
 * @author Hector Mariscal
 * @version 1.0 (01 February 2019)
 * @since 1.0
 */ 
package webd4201.mariscalh;

@SuppressWarnings("serial")
/**
 * exception handling for the invalidIdException
 */
public class InvalidIdException extends Exception{
    public InvalidIdException()
    { super();}
    /**
     * Exception handling for the invalidIdException
     * @param message 
     */
    public InvalidIdException(String message)
    { super(message);}
}
