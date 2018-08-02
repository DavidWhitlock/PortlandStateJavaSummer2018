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
    String[] lines = board.split("\n");
    int rows = lines.length;
    int columns = lines[0].length();

    this.board = new char[rows][columns];
    for (int line = 0; line < lines.length; line++) {
      this.board[line] = lines[line].toCharArray();
    }
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

    this.pacManColumn = this.pacManColumn + nextColumnOffset;
    return this.pacManColumn;
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

    this.pacManRow = this.pacManRow + nextRowOffset;
    return this.pacManRow;
  }

  public String getRow(int row) {
    return new String(this.board[row]);
  }

  public PacManDirection getPacManDirection() {
    return this.direction;
  }
}
