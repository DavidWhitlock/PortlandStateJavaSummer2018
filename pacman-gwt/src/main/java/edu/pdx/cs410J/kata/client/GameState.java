package edu.pdx.cs410J.kata.client;

import java.io.Serializable;

public class GameState implements Serializable {

  private char[][] board;

  public GameState(char[][] board) {
    this.board = board;
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
}
