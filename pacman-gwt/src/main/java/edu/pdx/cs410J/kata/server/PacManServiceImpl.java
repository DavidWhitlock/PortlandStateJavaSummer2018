package edu.pdx.cs410J.kata.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.kata.client.GameState;
import edu.pdx.cs410J.kata.client.PacManService;

/**
 * The server-side implementation of the Phone Bill service
 */
public class PacManServiceImpl extends RemoteServiceServlet implements PacManService
{

  /**
   * Log unhandled exceptions to standard error
   *
   * @param unhandled
   *        The exception that wasn't handled
   */
  @Override
  protected void doUnexpectedFailure(Throwable unhandled) {
    unhandled.printStackTrace(System.err);
    super.doUnexpectedFailure(unhandled);
  }

  @Override
  public GameState createNewGame(String board) {
    char[][] chars = new char[3][3];
    chars[0][0] = '+';
    chars[0][1] = '+';
    chars[0][2] = '+';
    chars[1][0] = ' ';
    chars[1][1] = '<';
    chars[1][2] = ' ';
    chars[2][0] = '+';
    chars[2][1] = '+';
    chars[2][2] = '+';
    return new GameState(chars);
  }
}
