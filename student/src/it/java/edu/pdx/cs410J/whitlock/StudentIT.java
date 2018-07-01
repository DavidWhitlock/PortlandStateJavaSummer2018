package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Integration tests for the <code>Student</code> class's main method.
 * These tests extend <code>InvokeMainTestCase</code> which allows them
 * to easily invoke the <code>main</code> method of <code>Student</code>.
 */
public class StudentIT extends InvokeMainTestCase {
  @Test
  public void invokingMainWithNoArgumentsHasExitCodeOf1() {
    MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void invokingMainWithNoArgumentsPrintsMissingArgumentsToStandardError() {
    MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

  @Test
  public void missingGenderPrintsMessageToStandardError() {
    MainMethodResult result = invokeMain(Student.class, "Name");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing gender"));
  }

  @Test
  public void invalidateGpaPrintMessageToStandardError() {
    MainMethodResult result = invokeMain(Student.class, "Name", "gender", "BAD GPA");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Invalid GPA: BAD GPA"));
  }

  @Ignore
  @Test
  public void invokingMainWithArgumentsFromAssignmentPrintsStudentToStringToStandardOut() {
    MainMethodResult result = invokeMain(Student.class, "Dave", "male", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(result.getTextWrittenToStandardOut(), containsString("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems, and Java.  He says \"This class is too much work\"."));
    assertThat(result.getExitCode(), equalTo(0));
  }


}
