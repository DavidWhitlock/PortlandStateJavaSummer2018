package edu.pdx.cs410J.kata.server;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.kata.client.GameState;
import edu.pdx.cs410J.kata.client.PacManService;

import java.text.SimpleDateFormat;
import java.util.Date;

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
  public GameState createNewGame(String board, Date endTime) {

    System.out.println("JRE: " + new SimpleDateFormat("MM/dd/yyyy hh:mm a").format(endTime));
    System.out.println("GWT: " + DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a").format(endTime));


    PacManGame game = new PacManGame(board);
    return game.getGameState(endTime);
  }
}
