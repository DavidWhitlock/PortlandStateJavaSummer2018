package edu.pdx.cs410J.kata;

public class GameBoard {
  private char[][] board;

  private int pacManColumn;
  private int pacManRow;
  private PacManDirection direction;

  public GameBoard(String board) {
    initializeBoard(board);
    locatePacMan();
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
    int rows = 1;
    int columns = board.length();

    this.board = new char[rows][columns];
    this.board[0] = board.toCharArray();
  }

  public void tick() {
    this.board[this.pacManRow][this.pacManColumn] = ' ';

    int nextRow = getNextRow();
    int nextColumn = getNextColumn();
    this.board[nextRow][nextColumn] = getPacManChar();
  }

  private char getPacManChar() {
    switch (this.direction) {
      case EAST:
        return '<';
      case WEST:
        return '>';
      default:
        throw new UnsupportedOperationException("Don't know how to handle" + this.direction);
    }
  }

  private int getNextColumn() {
    if (this.direction == PacManDirection.WEST) {
      return this.pacManColumn - 1;

    } else if (this.direction == PacManDirection.EAST) {
      return this.pacManColumn + 1;

    } else {
      throw new UnsupportedOperationException("Don't know how to handle " + this.direction + " yet");
    }
  }

  private int getNextRow() {
    return this.pacManRow;
  }

  public String getRow(int row) {
    return new String(this.board[row]);
  }

  public PacManDirection getPacManDirection() {
    return this.direction;
  }
}
