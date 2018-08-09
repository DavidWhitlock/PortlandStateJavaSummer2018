package edu.pdx.cs410J.kata.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Date;

/**
 * A GWT remote service that returns a dummy Phone Bill
 */
@RemoteServiceRelativePath("pacman")
public interface PacManService extends RemoteService {

  public GameState createNewGame(String board, Date endTime);
}
