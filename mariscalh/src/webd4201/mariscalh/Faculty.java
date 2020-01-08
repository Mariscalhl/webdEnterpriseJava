/**
 *This class defines a faculty member of the college.
 *
 * @author Hector Luis Mariscal
 * @since 2019-01-15
 * @version 1.0 
 */
package webd4201.mariscalh;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Faculty extends User{
    /**
     * Holds the default value for the school code
     */
    public final String DEFAULT_SCHOOL_CODE = "SET";
    /**
     * Holds the default value for the description of the school
     */
    public final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";
    /**
     * Holds the default value for an office in the school
     */
    public final String DEFAULT_OFFICE = "H-140";
    /**
     * Holds the default value for the schools phone extension
     */
    public final int DEFAULT_PHONE_EXTENSION = 1234;
    /**
     * String variable that holds the code for the school the faculty is associated with.
     */
    private String schoolCode;
    /**
     * String variable that will store the description  of the school that is related to the code
     */
    private String schoolDescription;
    /**
     * Stores a string of the location of the office
     */
    private String office;
    /**
     * An int variable holding the faculty members phone extension.
     */
    private int extension;
    
    
   //ACCESSORS AND MUTATORS
    
    /**
     * Returns the class instance attribute schoolCode
     * @return schoolCode
     */
    public String getSchoolCode() {
        return schoolCode;
    }
    /**
     * allows mutation of the class instance attribute schoolCode
     * @param schoolCode
     */
    public final void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
    /**
     * Returns the class instance attribute schoolDescription
     * @return 
     */
    public String getSchoolDescription() {
        return schoolDescription;
    }
    /**
     * allows mutation of the class instance attribute schoolDescription
     * @param schoolDescription 
     */
    public final void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }
    /**
     * Returns the class instance attribute office
     * @return office
     */
    public String getOffice() {
        return office;
    }
    /**
     * allows mutation of the class instance attribute office
     * @param office 
     */
    public final void setOffice(String office) {
        this.office = office;
    }
    /**
     * Returns the class instance attribute extension
     * @return extension
     */
    public int getExtension() {
        return extension;
    }
    /**
     * allows mutation of the class instance attribute extension
     * @param extension 
     */
    public final void setExtension(int extension) {
        this.extension = extension;
    }
/**
 * This parameterized constructor will set all of the class instance 
 * attributes from the faculty and user class.
 * @param id
 * @param password
 * @param firstName
 * @param lastName
 * @param emailAddress
 * @param lastAccess
 * @param enrolDate
 * @param enabled
 * @param type
 * @param schoolCode
 * @param schoolDescription
 * @param office
 * @param extension
 * @throws InvalidIdException
 * @throws InvalidNameException 
 * @throws NoSuchAlgorithmException 
 */
    public Faculty(long id, String password, String firstName, String lastName,
            String emailAddress, Date lastAccess, Date enrolDate, boolean enabled,
            char type, String schoolCode, String schoolDescription, String office
            , int extension) throws InvalidIdException, InvalidNameException, NoSuchAlgorithmException{
        super(id, password, firstName, lastName, emailAddress, lastAccess, 
                enrolDate, enabled, type);
        setSchoolCode(schoolCode);
        setSchoolDescription(schoolDescription);
        setOffice(office);
        setExtension(extension);
    }
/**
 * Sets the default variables from both the faculty and user classes.
 * @throws InvalidIdException
 * @throws InvalidNameException 
 * @throws NoSuchAlgorithmException 
 */
    public Faculty() throws InvalidIdException, InvalidNameException, NoSuchAlgorithmException{
        super();
        setSchoolCode(DEFAULT_SCHOOL_CODE);
        setSchoolDescription(DEFAULT_SCHOOL_DESCRIPTION);
        setOffice(DEFAULT_OFFICE);
        setExtension(DEFAULT_PHONE_EXTENSION);
    }
    /**
     * This method only returns the string "faculty", it is overridden and the 
     * string value will change based on which type of user class calls this.
     * @return string
     */
    @Override
    public String getTypeForDisplay() {
        return "Faculty";
    }
    /**
     * Overrides the toString method to output a formatted using the replacefirst method
     * to properly reuse and reformat the string
     *
     * @return String
     */
    @Override
    public String toString() { // overrides Object.toString(){
        String parent = super.toString();
        parent = parent.replaceFirst("User", getTypeForDisplay());
        parent = parent.replaceFirst("Name: " + getFirstName() + " " + getLastName(), 
                "Name: " + getFirstName() + " " + getLastName() + " (" + getEmailAddress() + ")" );
        return String.format("\n%s " 
                + "\n\t%s, (%s)"
                +"\n\tOffice: %s"
                +"\n\t%s x%s",
                 parent, getSchoolDescription(), getSchoolCode(), getOffice(), 
                    PHONE_NUMBER, getExtension());
                    
    }
}
