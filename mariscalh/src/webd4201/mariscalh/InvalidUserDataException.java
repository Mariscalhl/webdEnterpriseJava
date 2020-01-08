 /**
 * InvalidUserDataException - this file contains extends the generic Exception class so that
 * 			            we have a custom Exception.
 * @author Hector Mariscal
 * @version 1.0 (01 February 2019)
 * @since 1.0
 */ 

package webd4201.mariscalh;

@SuppressWarnings("serial")
/**
 * exception handling for the invalidUserDataException
 */
public class InvalidUserDataException extends Exception{
    public InvalidUserDataException()
    { super();}
    /**
     * 
     * @param message 
     */
    public InvalidUserDataException(String message)
    { super(message);}
    
}
