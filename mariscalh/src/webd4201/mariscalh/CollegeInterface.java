package webd4201.mariscalh;

/**
 *This is the interface for the college website.
 *
 * @author Hector Luis Mariscal
 * @since 2019-01-15
 * @version 1.0 
 */
public interface CollegeInterface {
    /**
     * A constant that stores the name of the college
     */
    public final String COLLEGE_NAME = "Durham College";
    /**
     * A constant that stores the phone number of the college
     */
    public final String PHONE_NUMBER = "(905)721-2000";
    /**
     * a constant that stores the minimum id number length
     */
    public final long MINIMUM_ID_NUMBER = 100000000;
    /**
     * a constant that stores the maximum id number length
     */
    public final long MAXIMUM_ID_NUMBER = 999999999;
    
    /**
     *A method that returns a string that must be used on any class that 
     * implements this interface.
     * @return string
     */
    public String getTypeForDisplay();
}
