/**
 *This is the studentDa class that handles the database functionality for a student.
 *
 * @author Hector Luis Mariscal
 * @since 2019-01-15
 * @version 1.0 
 */
package webd4201.mariscalh;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class StudentDA {
	// declare variables for the database connection
	/**
	 * a variable the holds the connection to the database
	 */
	static Connection aConnection;
	/**
	 * A variable that holds the statement for a database
	 */
	static Statement aStatement;
	/**
	 * a variable that holds a student object
	 */
	static Student aStudent;
	/**
	 * a variable that holds a Mark object
	 */
	static Mark myGrades;
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
	 * a variable that holds the progam's code
	 */
	static String programCode;
	/**
	 * A program that holds the program description that is attached to the code
	 */
	static String programDescription;
	/**
	 * A variable that holds the current year that the student is in
	 */
	static int year;
	/**
	 * A vector that will be used to hold the students grades
	 */
	static Vector<Mark> marks;
	/**
	 * Accesses the class variable programCode
	 * 
	 * @return programCode
	 */
	/**
	 * Date formatter so that it will be compatible with database
	 */
	private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");	//doesnt seem to work properly with setDate()

	/**
	 * Prepare Statement for updating a Student
	 */
	static String psStudentUpdate = "UPDATE students SET " + "programCode = ?, " + "programDescription = ?, "
			+ "year = ? " + "WHERE id = ?";
	/**
	 * Prepare statement for inserting a new student
	 */
	static String psInsertStudent = "INSERT INTO students " + "(id, programCode, programDescription, year) "
			+ "VALUES (?, ?, ?, ?)";
	/**
	 * Prepare statement for retrieving an existing user/student by id 
	 */
	static String psRetrieveStudent = "SELECT users.id, password, firstName, lastName, emailAddress, lastAccess, "
			+ "enrolDate, enabled, type, programCode, programDescription, year FROM users, students "
			+ "WHERE users.id = students.id AND users.id = ?";
	/**
	 * Prepare statement for authenticating a student by id and password
	 */
	static String psAuthenticateStudent = "SELECT users.id, password, firstName, lastName, emailAddress, lastAccess, "
			+ "enrolDate, enabled, type, programCode, programDescription, year FROM users, students "
			+ "WHERE users.id = students.id AND users.id = ? AND users.password = ?";
	/**
	 * Prepare statement to delete student
	 */
	static String psDeleteStudent = "DELETE FROM students WHERE id = ?";
	
	/**
	 * Prepare statement for changing password
	 */
	static String psChangePassword = "UPDATE users SET password = ? WHERE id = ?";
	/**
	 * prepare statement that gets course code
	 */
	static String psGetProgramDescription = "SELECT programDescription FROM courses WHERE courseCode = ?";
	/**
	 * Prepared statement that retrieves the grades of a student.
	 */
	static String psGetGrades = "SELECT m.coursecode, c.coursetitle, m.result, c.gpaweighting FROM students s "
			+ "INNER JOIN marks m ON s.id = m.id INNER JOIN courses c ON m.coursecode = c.coursecode AND s.id = ?";
	/**
	 * A constant for the type of hashing algorithm
	 */
//	private static final String ENCRYPTION_ALGO = "sha1";

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
	 * A method for creating a new student in the database.
	 * 
	 * @param aStudent
	 * @return inserted
	 * @throws DuplicateException
	 * @throws InvalidNameException 
	 * @throws InvalidIdException 
	 * @throws NoSuchAlgorithmException 
	 * @throws SQLException 
	 */
	public static boolean create(Student aStudent) throws DuplicateException, NoSuchAlgorithmException, InvalidIdException, InvalidNameException, SQLException {
		boolean inserted = false; // insertion success flag

		// see if this customer already exists in the database
		try {
			retrieve(aStudent.getId());
			throw (new DuplicateException(
					"Problem with creating Student record, id " + id + " already exists in the system."));
		}
		// if NotFoundException, add student to database
		catch (NotFoundException e) {
			try { // execute the SQL update statement

				aConnection.setAutoCommit(false);

				inserted = UserDA.create(aStudent);
				if (inserted != false) {
					
					try {
						PreparedStatement newStudent = aConnection.prepareStatement(psInsertStudent);
						
						newStudent.setLong(1, aStudent.getId());
						newStudent.setString(2, aStudent.getProgramCode());
						newStudent.setString(3, aStudent.getProgramDescription());
						newStudent.setInt(4, aStudent.getYear());

						int recordsAffected = newStudent.executeUpdate();
						if(recordsAffected > 0)
						{
							inserted = true;
						}else {
							inserted = false;
							aConnection.rollback();
						}
						newStudent.close();
					}catch(SQLException sqle){
						aConnection.rollback();
					}

				}else {
					aConnection.rollback();
				}
				if(inserted)
					aConnection.commit();
				
			} catch (SQLException ee) {
				System.out.println(ee);
			}

		} 
		//System.out.println(inserted);
		return inserted;
	}

	/**
	 * Retrieves a student object from the database
	 * 
	 * @param key
	 * @return aStudent
	 * @throws NotFoundException
	 */
	public static Student retrieve(long key) throws NotFoundException {
		aStudent = null;
		// define the SQL query statement using the key
		try {
			PreparedStatement studentRetrieve = aConnection.prepareStatement(psRetrieveStudent);
			studentRetrieve.setLong(1, key);
			
			ResultSet rs = studentRetrieve.executeQuery();
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
				programCode = rs.getString("programCode");
				programDescription = rs.getString("programDescription");
				year = rs.getInt("year");
				
				
				PreparedStatement getGrades = aConnection.prepareStatement(psGetGrades);
				getGrades.setLong(1, key);
				ResultSet grs = getGrades.executeQuery();
				boolean hasGrades = grs.next();
				if (hasGrades) {
					while (grs.next()) {
						marks = new Vector<Mark>();
						
						myGrades = new Mark(grs.getString("courseCode"),grs.getString("courseTitle"), grs.getInt("result"), grs.getInt("gpaWeighting"));
						
					}
					
				}else {
					throw (new NotFoundException(
							"Student with id " + key + " does not have any grades yet."));
				}
				
				//try to create a student
				try {
					aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
							enabled, type, programCode, programDescription, year);
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

		return aStudent;
	}

	/**
	 * Updates an existing student object in the database
	 * 
	 * @param aStudent
	 * @return records
	 * @throws NotFoundException
	 */
	public static int update(Student aStudent) throws NotFoundException {
		int records = 0; // records updated in method
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//System.out.println(Student.retrieve(id));
		// see if this customer exists in the database
		// NotFoundException is thrownby find method
		try {
			aConnection.setAutoCommit(false);
			
			Student.retrieve(id); // determine if there is a Customer recrod to be updated
			// retrieve(id);
			records = UserDA.update(aStudent);

			if (records > 0) {
				PreparedStatement updateStudent = aConnection.prepareStatement(psStudentUpdate);
				updateStudent.setLong(4, aStudent.getId());
				updateStudent.setString(1, aStudent.getProgramCode());
				updateStudent.setString(2, aStudent.getProgramDescription());
				updateStudent.setInt(3, aStudent.getYear());
	
				records = updateStudent.executeUpdate();
				if (records > 0) {
					aConnection.commit();
					updateStudent.close();
				}else {
					aConnection.rollback();
				}
			}
			else {
				aConnection.rollback();
			}
		} catch (NotFoundException e) {
			throw new NotFoundException("Student with id " + aStudent.getId() + " cannot be updated, does not exist in the system.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}

	/**
	 * Deletes an existing student object in the database
	 * 
	 * @param aStudent
	 * @return records
	 * @throws NotFoundException
	 */
	public static int delete(Student aStudent) throws NotFoundException {
		int records = 0;
		// retrieve the phone no (key)
		id = aStudent.getId();
		// see if this customer already exists in the database
		try {
			aConnection.setAutoCommit(false);
			Student.retrieve(id); // used to determine if record exists for the passed Customer
			PreparedStatement deleteStudent = aConnection.prepareStatement(psDeleteStudent);
			deleteStudent.setLong(1, id);
			records = deleteStudent.executeUpdate();
			
			if (records > 0) {
				records = UserDA.delete(aStudent);
				if(records > 0)
				{
					aConnection.commit();
				}else {
					aConnection.rollback();
				}
			
			//deleteStudent.executeUpdate();	//execute student first because it is foreign key
			records = deleteStudent.executeUpdate();	//execute user after
			}
			else {
				aConnection.rollback();
			}
		} catch (NotFoundException e) {
			throw new NotFoundException("Students with id " + id + " cannot be deleted, does not exist.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
/**
 * This method makes sure a user with the the id and password combo exists in the database.
 * @param key
 * @param password
 * @return aStudent
 * @throws NotFoundException
 * @throws InvalidNameException
 * @throws InvalidIdException
 * @throws NoSuchAlgorithmException 
 * @throws SQLException 
 */
	public static Student authenticate(long key, String password) throws NotFoundException, InvalidNameException, InvalidIdException, NoSuchAlgorithmException, SQLException {
		aStudent = null;
		marks = null;


		try {
			aConnection.setAutoCommit(false);
			PreparedStatement studentAuthenticate = aConnection.prepareStatement(psAuthenticateStudent);
			studentAuthenticate.setLong(1, key);
			studentAuthenticate.setString(2, password);
			
			ResultSet rs = studentAuthenticate.executeQuery();
			
			aConnection.commit();
			boolean gotIt = rs.next();
			if (gotIt) {
				id = rs.getLong("id");
				password = rs.getString("password");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				emailAddress = rs.getString("emailAddress");
				lastAccess = rs.getDate("lastAccess");
				enrolDate = rs.getDate("enrolDate");
				enabled = rs.getBoolean("enabled");
				type = rs.getString("type").charAt(0);
				programCode = rs.getString("programCode");
				programDescription = rs.getString("programDescription");
				year = rs.getInt("year");
				
				PreparedStatement getGrades = aConnection.prepareStatement(psGetGrades);
				getGrades.setLong(1, key);
				ResultSet grs = getGrades.executeQuery();
				aConnection.commit();
				boolean hasGrades = grs.next();
				if (hasGrades) {
					marks = new Vector<Mark>();
					while (grs.next()) {
						myGrades = new Mark(grs.getString("courseCode"),grs.getString("courseTitle"), grs.getInt("result"), grs.getInt("gpaWeighting"));
						
						marks.addElement(myGrades);
					}
					
				}else {
					marks = null;
					aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled,
					type, programCode, programDescription, year);
//					myGrades = new Mark("", "", 0, 0);
//					
//					marks.addElement(myGrades);
				}
				

				aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled,
						type, programCode, programDescription, year, marks);
			} else {
				//System.out.println(aStudent);
				aConnection.rollback();
				throw (new NotFoundException("not found!!!!! "));
				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
			aConnection.rollback();
		}
		return aStudent;
	}
/**
 * Method for changing password
 * @param key
 * @param password
 * @return
 * @throws NotFoundException
 */
	public static int changePassword(long key, String password) throws NotFoundException {
		int records = 0; // records updated in method

		Student.retrieve(key);

		// see if this customer exists in the database
		// NotFoundException is thrownby find method
		try {
			Student.retrieve(key); // determine if there is a Customer recrod to be updated
			// retrieve(id);
			PreparedStatement changePassword = aConnection.prepareStatement(psChangePassword);
	

			changePassword.setLong(2, key);
			changePassword.setString(1, password);


			changePassword.executeUpdate();
			changePassword.close();

			
		} catch (NotFoundException e) {
			throw new NotFoundException("Student with id " + key + " cannot be updated, does not exist in the system.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
	
	/**
	 * A method created to get the program description from the database.
	 * @param programCode
	 * @return
	 * @throws NotFoundException
	 */
	public static String getProgramDescription(String programCode) throws NotFoundException {
		String newProgramDescription = null;
		try {
			PreparedStatement programDescriptionRetrieve = aConnection.prepareStatement(psGetProgramDescription);
			programDescriptionRetrieve.setString(1, programCode);
			
			ResultSet rs = programDescriptionRetrieve.executeQuery();
			// next method sets cursor & returns true if there is data
			boolean gotIt = rs.next();
			if (gotIt) { // extract the data
				newProgramDescription = rs.getString("programDescription");


			} else // nothing was retrieved
			{
				throw (new NotFoundException(
						"Problem retrieving course description, course code " + programCode + " does not exist in the system."));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return newProgramDescription;
	}


	
	
	
}
