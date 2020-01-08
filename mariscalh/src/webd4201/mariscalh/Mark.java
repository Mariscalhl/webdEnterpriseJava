/**
 *This is the Mark class that defines the grades in the college.
 *
 * @author Hector Luis Mariscal
 * @since 2019-01-15
 * @version 1.0 
 */
package webd4201.mariscalh;

import java.text.DecimalFormat;
import java.util.Vector;

public class Mark {
    /**
     * stores a constant float that stores the minimum gpa possible
     */
    public final float MINIMUM_GPA = 0.0f;
    /**
     * stores a constant float that stores the maximum gpa possible
     */
    public final float MAXIMUM_GPA = 5.0f;
    /**
     * Stores the decimal format constant that gpa must be in
     */
    public final static DecimalFormat GPA = new DecimalFormat("0.0");
    /**
     * Stores the course code in a string variable
     */
    private String courseCode;
    /**
     * stores the course name in a string variable 
     */
    private String courseName;
    /**
     * Stores the final grade for the class
     */
    private int result;
    /**
     * stores the gpa weighting of a grade
     */
    private float gpaWeighting;

    /**
     * accesses the course code instance attribute
     * @return courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }
    /**
     * Allows for the mutation of the class variable 
     * @param courseCode 
     */
    public final void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    /**
     * Allows for the mutation of the class variable 
     * @return courseName
     */
    public String getCourseName() {
        return courseName;
    }
    /**
     * Allows for the mutation of the class variable 
     * @param courseName 
     */
    public final void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    /**
     * Allows for the mutation of the class variable 
     * @return result
     */
    public int getResult() {
        return result;
    }
    /**
     * Allows for the mutation of the class variable 
     * @param result 
     */
    public final void setResult(int result) {
        this.result = result;
    }
    /**
     * Allows for the mutation of the class variable 
     * @return gpaWeighting
     */
    public final float getGpaWeighting() {
        return gpaWeighting;
    }
    /**
     * Allows for the mutation of the class variable 
     * @param gpaWeighting 
     */
    public final void setGpaWeighting(float gpaWeighting) {
        this.gpaWeighting = gpaWeighting;
    }
    /**
     * Parameterized constructor that takes in 4 parameters and sets them using
     * the mutator methods.
     * @param courseCode
     * @param courseName
     * @param result
     * @param gpaWeighting 
     */
    public Mark(String courseCode, String courseName, int result, float gpaWeighting){
        setCourseCode(courseCode);
        setCourseName(courseName);
        setResult(result);
        setGpaWeighting(gpaWeighting);
    }
    /**
     * Returns a string format of the course code, course name, grade and gpaweight
     * @return string
     */
    @Override
    public String toString() { // overrides Object.toString(){

        return String.format("%-11s %-34s %-9d %.1f\n",
                 getCourseCode(), getCourseName(), getResult(), getGpaWeighting());
    }
    /**
     * This method assigns a gpa value to a number grade
     * @param result
     * @param weight
     * @return gpaGrade * weight
     */
    public static double cumulativeGpa(int result, float weight) {

		double gpaGrade = 0.0;

		if(result >= 0 && result <= 49){
			gpaGrade = 0.0;
		}else if(result >= 50 && result <= 54){
			gpaGrade = 1.0;
		}else if(result >= 55 && result <= 59){
			gpaGrade = 1.5;
		}else if(result >= 60 && result <= 64){
			gpaGrade = 2.0;
		}else if(result >= 65 && result <= 69){
			gpaGrade = 2.5;
		}else if(result >= 70 && result <= 74){
			gpaGrade = 3.0;
		}else if(result >= 75 && result <= 79){
			gpaGrade = 3.5;
		}else if(result >= 80 && result <= 84){
			gpaGrade = 4.0;
		}else if(result >= 85 && result <= 89){
			gpaGrade = 4.5;
		}else if(result >= 90 && result <= 100){
			gpaGrade = 5.0;
		}
    	
    	
    	return gpaGrade * weight;
    }
    /**
     * This method calculates weighted gpa for the marks in the marks vector.
     * @param marks
     * @return weightedGpa
     */
    public static double weightedGpa(Vector<Mark> marks) {
    	double weightedGpa = 0.0;
    	double addedWeightedGrades = 0.0;
    	double totalCreditHours = 0.0;
    	
    	for(int i=0; i < marks.size(); i++) {
    		
    		addedWeightedGrades += cumulativeGpa(marks.get(i).getResult(), marks.get(i).getGpaWeighting());
    		totalCreditHours += marks.get(i).getGpaWeighting();
    	}
    	
    	weightedGpa = addedWeightedGrades / totalCreditHours;
    	
    	return weightedGpa;
    }
    
}
