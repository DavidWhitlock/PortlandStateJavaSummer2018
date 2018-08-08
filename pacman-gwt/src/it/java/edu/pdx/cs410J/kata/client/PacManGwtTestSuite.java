package edu.pdx.cs410J.kata.client;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;

public class PacManGwtTestSuite {
  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Phone Bill GWT Integration Tests");

    suite.addTestSuite(PacManGwtIT.class);

    return suite;
  }

}
