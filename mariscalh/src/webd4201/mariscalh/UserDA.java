/**
 *This is the UserDa class that handles the database functionality for a student.
 *
 * @author Hector Luis Mariscal
 * @since 2019-03-20
 * @version 1.0 
 */
package webd4201.mariscalh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDA {
	
	/**
	 * a variable the holds the connection to the database
	 */
	static Connection aConnection;
	/**
	 * A variable that holds the statement for a database
	 */
	static Statement aStatement;
	/**
	 * User object
	 */
	static User aUser;
	// User Object attributes
	/**
	 * Stores the class attribute for student
	 */
	static long id;
	/**
	 * Stores the users password
	 */
	static String password;
	/**
	 * Stores the users first name
	 */
	static String firstName;
	/**
	 * stores the users last name
	 */
	static String lastName;
	/**
	 * Stores the users email address.
	 */
	static String emailAddress;
	/**
	 * Stores when the last accessed he system
	 */
	static Date lastAccess;
	/**
	 * Stores when the user account was first created on the system.
	 */
	static Date enrolDate;
	/**
	 * Stores whether the user is enabled or disabled
	 */
	static boolean enabled;
	/**
	 * Stores what type of user this user is.
	 */
	static char type;
	/**
	 * Prepare statement for updating a user
	 */
	static String psUserUpdate = "UPDATE users SET " + "password = ?, " + "firstName = ?," + "lastName = ?, "
			+ "emailAddress = ?, " + "lastAccess = ?, " + "enrolDate = ?, " + "enabled = ?, " + "type = ? "
			+ "WHERE id = ?";
	/**
	 * Prepare Statement for inserting a new user
	 */
	static String psInsertUser = "INSERT INTO users "
			+ "(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	/**
	 * Prepare statement to delete user
	 */
	static String psDeleteUser = "DELETE FROM users WHERE id = ?";
	/**
	 * Prepare statement for retrieving an existing user/User by id 
	 */
	static String psRetrieveUser = "SELECT id, password, firstName, lastName, emailAddress, lastAccess, "
			+ "enrolDate, enabled, type FROM users "
			+ "WHERE id = ?";
	
	static String psIsExisting = "SELECT * FROM users WHERE id = ?";
	
	
	
	
	/**
	 * establish the database connection
	 * 
	 * @param c
	 */
	public static void initialize(Connection c) {
		try {
			aConnection = c;
			aStatement = aConnection.createStatement();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * close the database connection
	 */
	public static void terminate() {
		try { // close the statement
			aStatement.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Creates a new user
	 * @param aUser
	 * @return inserted
	 * @throws DuplicateException
	 */
	public static boolean create(User aUser) throws DuplicateException {
		boolean inserted = false; // insertion success flag

		// see if this customer already exists in the database
		try {
//			System.out.println(aUser.getId());
//			System.out.println(aUser);
			retrieve(aUser.getId());
//			System.out.println(aUser);
			

			throw (new DuplicateException(
					"Problem with creating User record, id " + id + " already exists in the system."));
		}
		// if NotFoundException, add student to database
		catch (NotFoundException e) {
			try { // execute the SQL update statement
				
				PreparedStatement newUser = aConnection.prepareStatement(psInsertUser);
									
				newUser.setLong(1, aUser.getId());
				newUser.setString(2, aUser.getPassword());
				newUser.setString(3, aUser.getFirstName());
				newUser.setString(4, aUser.getLastName());
				newUser.setString(5, aUser.getEmailAddress());
				newUser.setDate(6, new java.sql.Date(aUser.getLastAccess().getTime()));
				newUser.setDate(7, new java.sql.Date(aUser.getEnrolDate().getTime()));
				newUser.setBoolean(8, aUser.isEnabled());
				newUser.setString(9, Character.toString(aUser.getType()));

				int recordsCreated = newUser.executeUpdate();
				if(recordsCreated > 0)
					inserted = true;
				
				newUser.close();
				//inserted = true;
			} catch (SQLException ee) {
				System.out.println(ee);
			}
		}
		//System.out.println(inserted);
		return inserted;
	}

	/**
	 * Retrieves a user object from the database
	 * 
	 * @param key
	 * @return aStudent
	 * @throws NotFoundException
	 */
	public static User retrieve(long key) throws NotFoundException {
		// retrieve Customer and Boat data
//		System.out.println(key);
		aUser = null;
//		System.out.println(key);

		// execute the SQL query statement
		try {
			
			PreparedStatement userRetrieve = aConnection.prepareStatement(psRetrieveUser);
			//PreparedStatement userRetrieve = null;
			
			userRetrieve.setLong(1, key);
			ResultSet rs = userRetrieve.executeQuery();
			// next method sets cursor & returns true if there is data
			boolean gotIt = rs.next();
			if (gotIt) { // extract the data
				id = rs.getLong("id");
				password = rs.getString("password");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				emailAddress = rs.getString("emailAddress");
				lastAccess = rs.getDate("lastAccess");
				enrolDate = rs.getDate("enrolDate");
				enabled = rs.getBoolean("enabled");
				type = rs.getString("type").charAt(0);

				// create Customer
				try {
					aUser = new User(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
							enabled, type);
				} catch (Exception e) {
					System.out.println("Record for " + firstName + " contains an invalid id.  Verify and correct.");
				}

			} else // nothing was retrieved
			{
				throw (new NotFoundException(
						"Problem retrieving Student record, id " + key + " does not exist in the system."));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return aUser;
	}

	/**
	 * Updates an existing user object in the database
	 * 
	 * @param aUser
	 * @return records
	 * @throws NotFoundException
	 */
	public static int update(User aUser) throws NotFoundException {
		int records = 0; // records updated in method
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//System.out.println(Student.retrieve(id));
		//Student.retrieve(id);

		// see if this customer exists in the database
		// NotFoundException is thrownby find method
		try {
			User.retrieve(aUser.getId()); // determine if there is a Customer recrod to be updated
			// retrieve(id);
			PreparedStatement updateUser = aConnection.prepareStatement(psUserUpdate);

			updateUser.setLong(9, aUser.getId());
			updateUser.setString(1, aUser.getPassword());
			updateUser.setString(2, aUser.getFirstName());
			updateUser.setString(3, aUser.getLastName());
			updateUser.setString(4, aUser.getEmailAddress());
			updateUser.setDate(5, new java.sql.Date(aUser.getLastAccess().getTime()));
			updateUser.setDate(6, new java.sql.Date(aUser.getEnrolDate().getTime()));
			updateUser.setBoolean(7, aUser.isEnabled());
			updateUser.setString(8, Character.toString(aUser.getType()));

			records = updateUser.executeUpdate();
			updateUser.close();

//        	System.out.println(aStudent);

			// if found, execute the SQL update statement
//            records = aStatement.executeUpdate(psUserUpdate);
//            records = aStatement.executeUpdate(psStudentUpdate);
		} catch (NotFoundException e) {
			throw new NotFoundException("User with id " + aUser.getId() + " cannot be updated, does not exist in the system.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}

	/**
	 * Deletes an existing student object in the database
	 * 
	 * @param aUser
	 * @return records
	 * @throws NotFoundException
	 */
	public static int delete(User aUser) throws NotFoundException {
		int records = 0;
		// retrieve the phone no (key)
		id = aUser.getId();
		// see if this customer already exists in the database
		try {
			Student.retrieve(id); // used to determine if record exists for the passed Customer

			PreparedStatement deleteUser = aConnection.prepareStatement(psDeleteUser);
			
			deleteUser.setLong(1, id);
			
			records = deleteUser.executeUpdate();	//execute user after
		} catch (NotFoundException e) {
			throw new NotFoundException("Students with id " + id + " cannot be deleted, does not exist.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
	
	/**
	 * Checks to see if user exists in the database
	 * @param id
	 * @return boolean
	 * @throws SQLException 
	 */
	public static boolean isExistingLogin(long id) throws SQLException
	{ 
		// retrieve User data
		PreparedStatement isExisting = aConnection.prepareStatement(psIsExisting);
		isExisting.setLong(1, id);

			//System.out.println(sqlQuery);
		 boolean exists = true;                  
		// execute the SQL query statement
		 
		try
		{
			ResultSet rs = isExisting.executeQuery();
			exists = rs.next();
		}catch (SQLException e)
		{ 
			System.out.println(e);
		}
		return exists;
	}
	
	
	
}
