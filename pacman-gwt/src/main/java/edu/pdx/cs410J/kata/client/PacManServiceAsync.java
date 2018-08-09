package edu.pdx.cs410J.kata.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The client-side interface to the phone bill service
 */
public interface PacManServiceAsync {

  void createNewGame(String board, AsyncCallback<GameState> async);
}
