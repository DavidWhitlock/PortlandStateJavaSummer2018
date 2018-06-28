package edu.pdx.cs410J.whitlock;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
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
    createStudentWithGpa(4.1);
  }

  @Test
  public void aFourPointZeroGpaIsPossible() {
    createStudentWithGpa(4.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void gpaMustBeGreaterThanZero() {
    createStudentWithGpa(-1.0);
  }

  @Test
  public void aZeroGpaIsPossible() {
    createStudentWithGpa(0.0);
  }

  private void createStudentWithGpa(double gpa) {
    new Student("??", new ArrayList<>(), gpa, "Doesn't matter");
  }

  @Test
  public void javaClassShowsUpInToString() {
    String className = "Java";
    var classes = new ArrayList<String>();
    classes.add(className);

    //When:
    Student student = new Student("??", classes, 3.8, "Doesn't matter");

    //Then:
    assertThat(student.toString(), containsString(className));
  }

}
