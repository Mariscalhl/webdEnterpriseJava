/**
 *This is the user class that defines a user in the college.
 *
 * @author Hector Luis Mariscal
 * @since 2019-01-15
 * @version 1.0 
 */
package webd4201.mariscalh;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;




public class User implements CollegeInterface {

    //Shared class constants
    /**
     * I just have Stores the users default Id
     */
    public static final long DEFAULT_ID = 100123456;
    /**
     * Sets the default password
     */
    public static final String DEFAULT_PASSWORD = "password";
    /**
     * Sets the users default first name
     */
    public static final String DEFAULT_FIRST_NAME = "John";
    /**
     * Sets the users default last name
     */
    public static final String DEFAULT_LAST_NAME = "Doe";
    /**
     * Sets users default email address
     */
    public static final String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.ca";
    /**
     * Sets users default status
     */
    public static final boolean DEFAULT_ENABLED_STATUS = true;
    /**
     * Sets the users default type
     */
    public static final char DEFAULT_TYPE = 's';
    /**
     * Sets users default Id number length
     */
    public static final byte ID_NUMBER_LENGTH = 9;
    /**
     * Sets the date format constant
     */
    public static final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);
    /**
     * Stores the minimum password length constant
     */
    public static final byte MINIMUM_PASSWORD_LENGTH = 8; 
    /**
     * Stores the maximum password length constant
     */
    public static final byte MAXIMUM_PASSWORD_LENGTH = 20;
    /**
     * Constant for the hash algorithm
     */
    public static final String HASH_ALGO = "sha1";

    //Instance attributes 
    /**
     * Stores the users unique identification number
     */
    private long id;
    /**
     * Stores the users password
     */
    private String password;
    /**
     * Stores the users first name
     */
    private String firstName;
    /**
     * stores the users last name
     */
    private String lastName;
    /**
     * Stores the users email address.
     */
    private String emailAddress;
    /**
     * Stores when the last accessed he system
     */
    private Date lastAccess;
    /**
     * Stores when the user account was first created on the system.
     */
    private Date enrolDate;
    /**
     * Stores whether the user is enabled or disabled
     */
    private boolean enabled;
    /**
     * Stores what type of user this user is.
     */
    private char type;
    
	/**
	 * initializes the connection to the database
	 * 
	 * @param c this is the connection string
	 */
	public static void initialize(Connection c) {
		UserDA.initialize(c);
	}

	/**
	 * Retrieves the number of records with the key
	 * 
	 * @param key	users id
	 * @return	UserDA.retrieve(key)
	 * @throws NotFoundException throws this exception
	 */
	public static User retrieve(long key) throws NotFoundException {
		return UserDA.retrieve(key);
	}

////	public static Vector<Customer> retrieveAll()
////		{return StudentDA.retrieveAll();}
	/**
	 * Terminates the connection
	 */
	public static void terminate() {
		UserDA.terminate();
	}

//
//	// DA instance methods, you NEED to be a User object to do these *********************************
	/**
	 * 
	 * @return user
	 * @throws DuplicateException	throws this exception
	 * @throws NoSuchAlgorithmException	throws this exception
	 * @throws InvalidIdException	throws this exception
	 * @throws InvalidNameException	throws this exception
	 * @throws SQLException	throws this exception
	 */
	public boolean create() throws DuplicateException, NoSuchAlgorithmException, InvalidIdException, InvalidNameException, SQLException {
		return UserDA.create(this);
	}

	/**
	 * Deletes a user object
	 * @return UserDA.delete(this)
	 * @throws NotFoundException throws this exception
	 */
	public int delete() throws NotFoundException {
		return UserDA.delete(this);
	}

	/**
	 * updates an existing user object
	 * @return UserDA.delete(this)
	 * @throws NotFoundException throws this exception
	 */
	public int update() throws NotFoundException {
		return UserDA.update(this);
	}
	
	public static boolean isExistingLogin(long id) throws SQLException
    { return UserDA.isExistingLogin(id);}

    //Constructors
/**
 * parameterized constructor for this class that sets each of the class attributes 
 * @param id the users unique id
 * @param password the users sign in password
 * @param firstName	users first name
 * @param lastName	users last name
 * @param emailAddress	users email address
 * @param lastAccess	users last access date
 * @param enrolDate	users enrol date
 * @param enabled	if the user is enabled or not
 * @param type	users type of faculty or student
 * @throws InvalidIdException	throws this exception
 * @throws InvalidNameException 	throws this exception
 * @throws NoSuchAlgorithmException 	throws this exception
 */
    public User(long id, String password, String firstName, String lastName,
            String emailAddress, Date lastAccess, Date enrolDate, boolean enabled,
            char type) throws InvalidIdException, InvalidNameException, NoSuchAlgorithmException {
        setId(id);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setEmailAddress(emailAddress);
        setLastAccess(lastAccess);
        setEnrolDate(enrolDate);
        setEnabled(enabled);
        setType(type);
    }
    
/**
 * default constructor that sets to the default variables
 * @throws InvalidIdException throws this exception
 * @throws InvalidNameException throws this exception
 * @throws NoSuchAlgorithmException throws this exception
 */
    public User() throws InvalidIdException, InvalidNameException, NoSuchAlgorithmException {
//        this.id = DEFAULT_ID;
//        this.password = DEFAULT_PASSWORD;
//        this.firstName = DEFAULT_FIRST_NAME;
//        this.lastName = DEFAULT_LAST_NAME;
//        this.emailAddress = DEFAULT_EMAIL_ADDRESS;
//        this.enabled = DEFAULT_ENABLED_STATUS;
//        this.type = DEFAULT_TYPE;
//        this.enrolDate = new Date();
//        this.lastAccess = new Date();

        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME,
                DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS,
                DEFAULT_TYPE);

    }

    /**
     * accesses the class attribute id
     *
     * @return id
     */
    public long getId() {
        return id;
    }

  /**
   *  Mutates the class attribute id
   * @param id	the users unique id number
   * @throws InvalidIdException  throws this exception
   */
    public final void setId(long id) throws InvalidIdException {
        if (id > MINIMUM_ID_NUMBER && id < MAXIMUM_ID_NUMBER){
            this.id = id;
        }
        else
        {
            {throw (new InvalidIdException("Invalid id " + id +" needs to be more than" + MINIMUM_ID_NUMBER + " and less than " + MAXIMUM_ID_NUMBER + "."));}
        }
        
    }

    /**
     * Accesses the class attribute password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * mutates the class attribute password
     *
     * @param password	the users sign in password
     * @throws NoSuchAlgorithmException  throws this exception
     */
    public final void setPassword(String password) throws NoSuchAlgorithmException {
//    	String hashedPassword;
//    	hashedPassword = hash(password, HASH_ALGO).toLowerCase();
//    	
//        this.password = hashedPassword;
        this.password = password;
    }

    /**
     * Accesses the class attribute first name
     *
     * @return firstName	users first name
     */
    public String getFirstName() {
        return firstName;
    }

/**
 * Stores the first name
 * @param firstName	users first name
 * @throws InvalidNameException throws this exception
 */
    public final void setFirstName(String firstName) throws InvalidNameException {
        if (firstName.length() < 1) {
            throw new InvalidNameException("Person names must be at least "
                    + "one character.");
        }
        else{
                for (int index = 0; index < firstName.length(); index++) {
                    
                    char letter = firstName.charAt(index);
                   
                    if (!Character.isAlphabetic(letter)) {
                       
                        throw new InvalidNameException(String.format("\"%s\" "
                         + "is invalid. ",firstName));
                        
                    }
                }
            this.firstName = firstName;
        }
    }

    /**
     * accesses the class attribute lastName
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Mutates the class attribute lastName
     * @param lastName	users last name
     * @throws InvalidNameException throws this exception
     */
    public final void setLastName(String lastName) throws InvalidNameException {
                if (lastName.length() < 1) {
            throw new InvalidNameException("Person names must be at least "
                    + "one character.");
        }
        else{
            
                
                for (int index = 0; index < lastName.length(); index++) {
                   
                    char letter = lastName.charAt(index);
                 
                    if (!Character.isAlphabetic(letter)) {
                       
                        throw new InvalidNameException(String.format("\"%s\" "
                         + "is invalid. ",lastName));
                        
                    }
                }
            this.lastName = lastName;
        }
    }
    

    /**
     * accesses the class attribute emailAddress
     *
     * @return emailAddress	users email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Mutates the class attribute emailAdress
     *
     * @param emailAddress	users email address
     */
    public final void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Accesses the class attribute lastAccess
     *
     * @return lastAccess	users last access date
     */
    public Date getLastAccess() {
        return lastAccess;
    }

    /**
     * Mutates the class attribute lastAccess
     *
     * @param lastAccess	users last access date
     */
    public final void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Accesses the class attribute enrolDate
     *
     * @return enrolDate	users enrol date
     */
    public Date getEnrolDate() {
        return enrolDate;
    }

    /**
     * Mutates the class attribute enrolDate
     *
     * @param enrolDate users enrol date
     */
    public final void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    /**
     * Accesses the class attribute isEnabled
     *
     * @return isEnabled	identifier is the user is enabled or disabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Mutates the class attribute enabled
     *
     * @param enabled	identifier is the user is enabled or disabled
     */
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Accesses the class attribute type
     *
     * @return	type
     */
    public char getType() {
        return type;
    }

    /**
     * Mutates the class attribute type
     *
     * @param type	what type of user they are
     */
    public final void setType(char type) {
        this.type = type;
    }

    /**
     * Overrides the toString method to output a formatted
     *
     * @return String
     */
    @Override
    public String toString() { // overrides Object.toString(){

        return String.format("\nUser Info for: %s "
                + "\n\tName: %s %s "
                + "\n\tCreated on: " + DF.format(getEnrolDate())
                + "\n\tLast access: " + DF.format(getLastAccess()),
                 getId(), getFirstName(), getLastName());
    }

    /**
     *This is not supported
     * @return nothing
     */
    @Override
    public String getTypeForDisplay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * This method just displays the overridden toString method
     */
    public void dump() {
        System.out.println(toString());
    }
    /**
     * This method checks to see if the id is valid and returns a boolean
     * @param id	user unique identifier 
     * @return boolean
     */
    public static Boolean verifyId(long id) {
        if (id >= MINIMUM_ID_NUMBER && id <= MAXIMUM_ID_NUMBER) {
            return true;
        } else {
            return false;
        }
    }
   /**
    * Used to hash a password/String 
    * @param data	data to be hashed
    * @param algorithm	hashing algorithm
    * @return	bytesToStringHex(hash)
    * @throws NoSuchAlgorithmException	throws this exception
    */
   public static String hash(String data, String algorithm) throws NoSuchAlgorithmException{
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.reset();
		byte[] hash = digest.digest(data.getBytes());
		return bytesToStringHex(hash);
	}
   /**
    * provides the hexArray value
    */
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	/**
	 * Used in the Hash method
	 * @param bytes	the bytes used to hash
	 * @return	String(hexChars)
	 */
	public static String bytesToStringHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length*2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j*2] = hexArray[v >>> 4];
			hexChars[j*2+1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	/**
	 * validates an email using the javax.email method
	 * @param email	users email address
	 * @return result
	 */
	public static boolean isValidEmailAddress(String email){
		boolean result = true;
		try 
		{
		   InternetAddress emailAddr = new InternetAddress(email);
		   emailAddr.validate();
		} catch (AddressException ex) {
		   result = false;
		}
		return result;
		}

    
}
