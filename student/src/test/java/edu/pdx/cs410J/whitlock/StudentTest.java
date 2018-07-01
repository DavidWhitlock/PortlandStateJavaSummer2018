package edu.pdx.cs410J.whitlock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

  @Test
  public void toStringContainsStudentName() {
    String name = "Test Name";
    Student student = new Student(name, Collections.emptyList(), 1.23, "Doesn't matter");
    assertThat(student.toString(), containsString(name));
  }

  @Test
  public void toStringContainsGpa() {
    double gpa = 2.34;
    Student student = new Student("Name", Collections.emptyList(), gpa, "Doesn't matter");
    assertThat(student.toString(), containsString("has a GPA of " + gpa));
  }

  @Test
  public void toStringContainsOneClass() {
    List<String> classes = List.of("Java");
    Student student = new Student("Name", classes, 2.34, "Doesn't matter");
    assertThat(student.toString(), containsString("and is taking 1 class: Java."));
  }

  @Test
  public void toStringContainsTwoClasses() {
    List<String> classes = List.of("Java", "Compilers");
    Student student = new Student("Name", classes, 2.34, "Doesn't matter");
    assertThat(student.toString(), containsString("and is taking 2 classes: Java and Compilers."));
  }

  @Test
  public void toStringContainsThreeClasses() {
    List<String> classes = List.of("Java", "Compilers", "Operating Systems");
    Student student = new Student("Name", classes, 2.34, "Doesn't matter");
    assertThat(student.toString(), containsString("and is taking 3 classes: Java, Compilers, and Operating Systems."));
  }

  @Test
  public void toStringContainsZeroClasses() {
    List<String> classes = List.of();
    Student student = new Student("Name", classes, 2.34, "Doesn't matter");
    assertThat(student.toString(), containsString("and is taking 0 classes."));
  }

  @Test
  public void malePronounIsHe() {
    String gender = "male";
    Student student = new Student("Name", Collections.emptyList(), 2.34, gender);
    assertThat(student.getGenderPronoun(), equalTo("He"));
    assertThat(student.toString(), containsString("He says "));
  }

  @Test
  public void femalePronounIsShe() {
    String gender = "female";
    Student student = new Student("Name", Collections.emptyList(), 2.34, gender);
    assertThat(student.getGenderPronoun(), equalTo("She"));
    assertThat(student.toString(), containsString("She says "));
  }

  @Test
  public void allStudentsSayThisClassIsTooMuchWork() {
    Student student = new Student("Name", Collections.emptyList(), 2.34, "female");
    assertThat(student.says(), equalTo("This class is too much work"));
    assertThat(student.toString(), containsString("says \"This class is too much work\"."));
  }

  @Test
  public void exampleInputFromAssignmentGeneratesExpectedToStringValue() {
    List<String> classes = List.of("Algorithms", "Operating Systems", "Java");
    Student dave = new Student("Dave", classes, 3.64, "male");

    assertThat(dave.toString(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems, and Java.  He says \"This class is too much work\"."));
  }

}
