package edu.pdx.cs410J.kata.client;

import java.io.Serializable;
import java.util.Date;

public class GameState implements Serializable {

  private Date endTime;
  private char[][] board;

  public GameState(char[][] board, Date endTime) {
    this.board = board;
    this.endTime = endTime;
  }

  public GameState() {
  }

  public int getNumberOfRows() {
    return this.board.length;
  }

  public int getNumberOfColumns() {
    return this.board[0].length;
  }

  public char[] getRow(int row) {
    return this.board[row];
  }

  public Date getEndTime() {
    return this.endTime;
  }

}
