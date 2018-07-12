package edu.pdx.cs410J.tennis;

import org.junit.Test;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class TennisTest
{

  @Test
  public void canCreateTennisObject() {
    new Tennis();
  }

}
