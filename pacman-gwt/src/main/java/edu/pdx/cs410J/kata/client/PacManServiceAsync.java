package edu.pdx.cs410J.kata.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;

/**
 * The client-side interface to the phone bill service
 */
public interface PacManServiceAsync {

  void createNewGame(String board, Date endTime, AsyncCallback<GameState> async);
}
