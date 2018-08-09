package edu.pdx.cs410J.kata;

import java.util.Scanner;

/**
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class PacMan {

  private static final String BOARD = (
    "+--+ +------------+\n" +
    "+    |            +\n" +
    "+  + + + -- +     +\n" +
    "+      +    +     +\n" +
    "+  | | +----+     +\n" +
    "   | |   <         \n" +
    "+--+ +------------+\n"
    ).replace(' ', '.');

  public static void main(String[] args) {
    PacManGame board = new PacManGame(BOARD);

    printBoard(board);

    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String input = scanner.next();
      setDirectionFromInput(input, board);

      board.tick();
      printBoard(board);

      if (board.getPelletCount() == 0) {
        break;
      }
    }

    System.out.println("Congratulations!  You won the game.");
  }

  private static void setDirectionFromInput(String input, PacManGame board) {
    if (input.length() == 0) {
      return;

    } else if (input.startsWith("h")) {
      board.setPacManDirection(PacManDirection.WEST);

    } else if (input.startsWith("j")) {
      board.setPacManDirection(PacManDirection.NORTH);

    } else if (input.startsWith("k")) {
      board.setPacManDirection(PacManDirection.SOUTH);

    } else if (input.startsWith("l")) {
      board.setPacManDirection(PacManDirection.EAST);

    } else {
      System.err.println("Don't know what to do with " + input);
      System.err.flush();
    }

  }

  private static void printBoard(PacManGame board) {
    System.out.println("");
    System.out.println("Score: " + board.getScore());
    board.forEachRow(System.out::println);
    System.out.println("");
  }
}