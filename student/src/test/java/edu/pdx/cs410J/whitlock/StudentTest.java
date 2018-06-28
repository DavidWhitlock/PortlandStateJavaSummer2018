package edu.pdx.cs410J.whitlock;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  public void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = new Student(name, new ArrayList<>(), 0.0, "Doesn't matter");
    assertThat(pat.getName(), equalTo(name));
  }

  @Test(expected = IllegalArgumentException.class)
  public void gpaMustBeLessThanOrEqualToFourPointZero() {
    double gpa = 4.1;  // Given

    createStudentWithGpa(gpa); // When

    // Then we expect this exception to occur
  }

  @Test
  public void aFourPointZeroGpaIsPossible() {
    double gpa = 4.0;
    createStudentWithGpa(gpa);
  }

  private void createStudentWithGpa(double gpa) {
    new Student("??", new ArrayList<>(), gpa, "Doesn't matter");
  }

  /*
   * Interesting test cases for GPA:
   *   GPA must be less than or equal to 4.0
   *   GPA cannot be negative
   */

}
