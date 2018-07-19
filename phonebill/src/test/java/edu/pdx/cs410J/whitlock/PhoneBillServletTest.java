package edu.pdx.cs410J.whitlock;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link PhoneBillServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class PhoneBillServletTest {

  @Test
  public void initiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);
    PrintWriter mockPrintWriter = mock(PrintWriter.class);

    when(mockResponse.getWriter()).thenReturn(mockPrintWriter);

    servlet.doGet(mockRequest, mockResponse);

    int expectedWords = 0;
    verify(mockPrintWriter).println(Messages.formatWordCount(expectedWords));
    verify(mockResponse).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  public void addOneWordToDictionary() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String word = "TEST WORD";
    String definition = "TEST DEFINITION";

    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    when(mockRequest.getParameter("word")).thenReturn(word);
    when(mockRequest.getParameter("definition")).thenReturn(definition);

    HttpServletResponse mockResponse = mock(HttpServletResponse.class);
    PrintWriter mockPrintWriter = mock(PrintWriter.class);

    when(mockResponse.getWriter()).thenReturn(mockPrintWriter);

    servlet.doPost(mockRequest, mockResponse);

    verify(mockPrintWriter).println(Messages.definedWordAs(word, definition));
    verify(mockResponse).setStatus(HttpServletResponse.SC_OK);

    assertThat(servlet.getDefinition(word), equalTo(definition));
  }

}
