 /**
 * DuplicateUserExceptionException - 	this file contains extends the generic Exception class so that
 * 			            			we have a custom Exception, this one will be used to flag an invalid
 * 					                phone number (use for data validation for Customer creation)
 * @author Hector Mariscal
 * @version 2019-04-10
 * @since 1.0
 */ 
package webd4201.mariscalh;

/**
 *an exception
 * @author hlmar
 */
@SuppressWarnings("serial")
class DuplicateUserException extends Exception{
        public DuplicateUserException()
    { super();}
}
