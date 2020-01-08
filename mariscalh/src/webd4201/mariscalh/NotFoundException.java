package webd4201.mariscalh;

 /**
 * NotFoundException - this file contains extends the generic Exception class so that
 *                 we have a custom Exception, this one will be used to flag when 
 *                 no record was found in the database (and therefore nothing can be
 *                 done to it)
 * @author Hector Mariscal
 * @version 2019-04-10
 * @since 1.0
 */

@SuppressWarnings("serial")
/**
 * Exception handling for notFoundException
 */
public class NotFoundException extends Exception
{
	public NotFoundException()
	{ super();}
	
	public NotFoundException(String message)
	{ super(message);}
}