package edu.pdx.cs410J.kata;

import java.util.function.Consumer;

public class GameBoard {
  private char[][] board;

  private int pacManColumn;
  private int pacManRow;
  private PacManDirection direction;
  private int score = 0;
  private int pelletCount = 0;

  public GameBoard(String board) {
    initializeBoard(board);
    locatePacMan();
    countPellets();
  }

  private void countPellets() {
    for (int row = 0; row < this.board.length; row++) {
      for (int column = 0; column < this.board[row].length; column++) {
        if (cellContainsPellet(row, column)) {
          this.pelletCount++;
        }
      }
    }
  }

  private void locatePacMan() {
    for (int row = 0; row < this.board.length; row++) {
      for (int column = 0; column < this.board[row].length; column++) {
        char c = this.board[row][column];

        switch (c) {
          case '<':
            setPacManState(row, column, PacManDirection.EAST);
            continue;
          case '>':
            setPacManState(row, column, PacManDirection.WEST);
            continue;
          case 'V':
            setPacManState(row, column, PacManDirection.NORTH);
            continue;
          case '^':
            setPacManState(row, column, PacManDirection.SOUTH);
            continue;
        }
      }
    }

    if (this.direction == null) {
      throw new IllegalArgumentException("Board is missing Pac-Man");
    }

  }

  private void setPacManState(int row, int column, PacManDirection direction) {
    this.direction = direction;
    this.pacManRow = row;
    this.pacManColumn = column;
  }

  private void initializeBoard(String board) {
    String[] lines = board.split("\n");
    int rows = lines.length;
    int columns = lines[0].length();

    this.board = new char[rows][columns];
    for (int line = 0; line < lines.length; line++) {
      this.board[line] = lines[line].toCharArray();
    }
  }

  public void tick() {

    int nextRow = getNextRow();
    int nextColumn = getNextColumn();

    if (cellContainsWall(nextRow, nextColumn)) {
      return;

    } else if (cellContainsPellet(nextRow, nextColumn)) {
      eatPellet();
    }

    this.board[this.pacManRow][this.pacManColumn] = ' ';
    this.pacManRow = nextRow;
    this.pacManColumn = nextColumn;
    this.board[nextRow][nextColumn] = getPacManChar();
  }

  private void eatPellet() {
    this.score += 10;
    this.pelletCount--;
  }

  private boolean cellContainsPellet(int row, int column) {
    return getCell(row, column) == '.';
  }

  private char getCell(int row, int column) {
    return this.board[row][column];
  }

  private boolean cellContainsWall(int row, int column) {
    if (cellContainsPellet(row, column)) {
      return false;

    } else {
      return getCell(row, column) != ' ';
    }
  }

  private char getPacManChar() {
    switch (this.direction) {
      case EAST:
        return '<';
      case WEST:
        return '>';
      case SOUTH:
        return '^';
      case NORTH:
        return 'V';
      default:
        throw new UnsupportedOperationException("Don't know how to handle " + this.direction);
    }
  }

  private int getNextColumn() {
    int nextColumnOffset;

    if (this.direction == PacManDirection.WEST) {
      nextColumnOffset = -1;

    } else if (this.direction == PacManDirection.EAST) {
      nextColumnOffset = 1;

    } else if (this.direction == PacManDirection.SOUTH) {
      nextColumnOffset = 0;

    } else if (this.direction == PacManDirection.NORTH) {
      nextColumnOffset = 0;

    } else {
      throw new UnsupportedOperationException("Don't know how to handle " + this.direction + " yet");
    }

    int nextColumn = this.pacManColumn + nextColumnOffset;
    if (nextColumn < 0) {
      nextColumn = getNumberOfColumns() - 1;

    } else if (nextColumn >= getNumberOfColumns()) {
      nextColumn = 0;
    }
    return nextColumn;
  }

  private int getNumberOfColumns() {
    return this.board[0].length;
  }

  private int getNextRow() {
    int nextRowOffset;

    if (this.direction == PacManDirection.SOUTH) {
      nextRowOffset = 1;

    } else if (this.direction == PacManDirection.NORTH) {
      nextRowOffset = -1;

    } else if (this.direction == PacManDirection.EAST) {
      nextRowOffset = 0;

    } else if (this.direction == PacManDirection.WEST) {
      nextRowOffset = 0;

    } else {
      throw new UnsupportedOperationException("Don't know how to handle " + this.direction + " yet");
    }

    int nextRow = this.pacManRow + nextRowOffset;
    if (nextRow < 0) {
      nextRow = getNumberOfRows() - 1;

    } else if (nextRow >= getNumberOfRows()) {
      nextRow = 0;
    }
    return nextRow;
  }

  private int getNumberOfRows() {
    return this.board.length;
  }

  String getRow(int row) {
    return new String(this.board[row]);
  }

  PacManDirection getPacManDirection() {
    return this.direction;
  }

  public int getScore() {
    return score;
  }

  public void setPacManDirection(PacManDirection direction) {
    this.direction = direction;
    this.board[this.pacManRow][this.pacManColumn] = getPacManChar();
  }

  public int getPelletCount() {
    return pelletCount;
  }

  public void forEachRow(Consumer<String> consumer) {
    for (char[] row : this.board) {
      consumer.accept(new String(row));
    }
  }
}
