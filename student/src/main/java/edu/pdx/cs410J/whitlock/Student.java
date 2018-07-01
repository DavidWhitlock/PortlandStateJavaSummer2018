package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.lang.Human;

import java.util.List;

/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {

  private final List<String> classes;
  private double gpa;

  /**
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male" or "female", case insensitive)             
   */                                                                               
  public Student(String name, List<String> classes, double gpa, String gender) {
    super(name);

    validateGPA(gpa);
    this.gpa = gpa;

    this.classes = classes;
  }

  private void validateGPA(double gpa) {
    if (gpa > 4.0) {
      throw new IllegalArgumentException("GPA cannot be greater than 4.0");
    }

    if (gpa < 0.0) {
      throw new IllegalArgumentException("GPA cannot be less than 0.0");
    }
  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    throw new UnsupportedOperationException("Not implemented yet");
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.name);
    sb.append(" has a GPA of ").append(this.gpa);

    int numberOfClasses = this.classes.size();
    sb.append(" and is taking ").append(numberOfClasses)
      .append(" class").append(numberOfClasses == 1 ? "" : "es");

    if (numberOfClasses > 0) {
      sb.append(": ");

      for (int i = 0; i < numberOfClasses; i++) {
        sb.append(this.classes.get(i));

        if (i < numberOfClasses - 1) {
          if (numberOfClasses > 2) {
            sb.append(",");
          }
          sb.append(" ");
        }

        if (i == numberOfClasses - 2) {
          sb.append("and ");
        }
      }
    }

    sb.append(".");

    return sb.toString();
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    System.err.println("Missing command line arguments");
    System.exit(1);
  }
}