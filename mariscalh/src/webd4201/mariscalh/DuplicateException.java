package webd4201.mariscalh;

 /**
 * DuplicateException - this file contains extends the generic Exception class so that
 * 			            we have a custom Exception, this one will be used to flag an attempt
 * 			            at a duplicate record in our database
 * @author Hector Mariscal
 * @version 2019-04-10
 * @since 1.0
 */ 

@SuppressWarnings("serial")
public class DuplicateException extends Exception
{
    /**
     * calls super class
     */
    public DuplicateException()
    { super();}
    /**
     * 
     * @param message 
     */
    public DuplicateException(String message)
    { super(message);}
}