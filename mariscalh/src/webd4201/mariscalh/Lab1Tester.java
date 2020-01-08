package webd4201.mariscalh;

import java.sql.Connection;
/**
 * Main method to test the Student JDBC methods
 * 
 * @author Darren Puffer
 * @version 1.0 (2018 January 18)
 * @since 1.0
 */
import java.util.*;

public class Lab1Tester {
/**
 * Tester main class
 * @param args
 * @throws InvalidIdException
 * @throws InvalidNameException 
 */
	public static void main(String[] args) throws InvalidIdException, InvalidNameException {
		System.out.println("******************** Lab 1 Output ********************\n");
		Connection c = null;
		Student mainStudent;  //object for a program created Student
		Student dbStudent;   //object for database retrieved Student
		Student hashTestStudent;
		long possibleId = 100222222L;
		GregorianCalendar cal = new GregorianCalendar();
		Date lastAccess = cal.getTime();
		cal.set(2019, Calendar.JANUARY, 18);
		Date enrol = cal.getTime();
		Student testStudent;
		User testUser;
		try{
			hashTestStudent = new Student();
			mainStudent = new Student();
			dbStudent = new Student();
//			System.out.println("\nCreate a Student user to insert/delete later in the program, passing:\n\t" +
//					"Student student1 = new Student(" + possibleId + "L, \"password\", \"Robert\", \"McReady\"," +
//					" \"bob.mcready@dcmail.ca\", enrol, lastAccess, true, 's', \"CPA\", \"Computer Programmer Analyst\", 3);\n"); 
			
			mainStudent = new Student(100999999,"password", "Robert", "McReady", "bob.mcready@dcmail.ca",
					enrol, lastAccess, true, 's', "CPA", "Computer Programmer Analyst", 3);
			hashTestStudent = new Student(100696969, "password", "Hash", "McHashington", "hash.mchashy@dcmail.ca",
					enrol, lastAccess, true, 's', "HSH", "Hash Class", 1);
			//mainStudent.displayToConsole();
			try{
				
	            // initialize the database (i.e. create a database connection)
	            c = DatabaseConnect.initialize();
	            User.initialize(c);
	            Student.initialize(c);
	            
	           /* try // attempt to get a Student that does NOT exist, throws Exception
	            {
	            	
//	            	System.out.println("\nAttempt to retrieve a student that does not exist (Id: " + possibleId + ")");
	            	dbStudent = Student.retrieve(100222222L);
	            	//System.out.println("Student record with id " + possibleId + " retrieved from the database\n");       	
	            	
	      
	            	//System.out.println(StudentDA.psStudentUpdate);
	            	System.out.println("************************************************************************************");
	            	//Student.authenticate(100999999, "password");
	            	//Student.authenticate(100111111, "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8");
	            	
	            	//System.out.println(StudentDA.psRetrieveStudent);
	            	
	            }
	            catch(NotFoundException e)
	            {	System.out.println(e.getMessage());}*/

//	            try // attempt to get a Student that does exist
//	            {
//	            	possibleId = 100111111L;
//	            	System.out.println("\nAttempt to retrieve a student that does exist (Id: " + possibleId + ")");
//	            	dbStudent = Student.retrieve(possibleId);
//	            	System.out.println("Student record with id " + possibleId + " retrieved from the database\n");
//	            	dbStudent.dump();
//	            }
//	            catch(NotFoundException e)
//	            {	System.out.println(e.getMessage());}
//	            
	            try
	            {
	            	System.out.println("************************************************************************************");

	            	
	    			testStudent = new Student(100777778L, "password", "Tommy", "Wiseau", "tommy.wiseau@dcmail.ca",
	    					enrol, lastAccess, true, 's', "ACCB", "Accounting - Business", 2);
	    			
	            	System.out.println("\nAttempt to insert a new student record for " 
    						+ testStudent.getFirstName() + " " + testStudent.getLastName() 
    						+ "(Id: " + testStudent.getId()+")");
	            	
	            	 testUser = new User(100888888, "password", "Test", "User", "test.user@dcmail.ca",
	    					enrol, lastAccess, true, 's');
	            	 testStudent.dump();
	           
	            	
	            	 testStudent.create();
	            	
	            	UserDA.isExistingLogin(100696969);
	            	
	            	
	    			//StudentDA.create(testStudent);
//	            	testStudent.create();
	                System.out.println("Student record added to the database.\n");
	                
	            	System.out.println("************************************************************************************");
	            	
	            	System.out.println(hashTestStudent);
	            	//hashTestStudent.create();
	            	//hashTestStudent.delete();
	            }
	            catch(DuplicateException e)
	            {	System.out.println(e);}
//	            
	            try
	            {
	            	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	            	Student gradesStudent = Student.authenticate(100111110, "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8");
	            	
	            	
	            	System.out.println(gradesStudent);
	            	System.out.println(gradesStudent.getMarks());
	            	
	            	System.out.println(StudentDA.psGetGrades);
	                
	            }
	            catch(NotFoundException e)
	            {	System.out.println(e);}
//	            
//	            System.out.println("\nStudents are encouraged to comment out the folowing try...catch block to"
//	            		+ " verify the new record exists in pgAdmin by running the \"SELECT * FROM Students;\" command "); 
//		            	
//	            try // now, attempt to delete the new Student
//	            {
//	            	System.out.println("\nAttempt to delete the new student record for " 
//	            	   						+ mainStudent.getFirstName() + " " + mainStudent.getLastName() 
//   						+ "(Id: " + mainStudent.getId() + ")");
//	        	   		mainStudent.delete();
//	        	   		
//	        	   		
//	        	   	System.out.println("Student record with id " + mainStudent.getId() + " successfully removed from the database.\n");
//	        	   	
//	        	   	System.out.println("************************************************************************************");
//	        	   	//Student aStudent = Student.authenticate(100222222, "password");
//	        	   	//Student aStudent = Student.authenticate(100111111, "newpassword");
//	        	   	//System.out.println(aStudent);
//	        	   	
//	        	   	final String HASH_ALGO = "sha1";
//	        	   	String hashPassword = User.Hash("password", HASH_ALGO).toLowerCase();
//	        	   	System.out.println(hashPassword);
//
//	        	  
//	        	   	
//	            }
//	            catch(NotFoundException e)
//	                    {	System.out.println(e);}
//
	            try // now, try to find the deleted Student
	            {
	            	possibleId = 100999999L;
	                mainStudent = Student.retrieve(possibleId);
	                mainStudent.dump();
	                //mainStudent.delete();
	            }
	            catch(NotFoundException e)
	            {
	            	System.out.println("Did not find student record with id " + possibleId + ".\n");
	            }
//			 }catch(Exception e){   //catch for database initialize/connect try
//				  System.out.println(e.toString());
			 }finally{ // close the database resources, if possible            
		          try{  User.terminate(); Student.terminate(); }catch(Exception e){}
		          try{  DatabaseConnect.terminate(); }catch(Exception e){}
			 }
		}catch(Exception iude){
			System.out.println(iude.getMessage());
		}
	}
}

