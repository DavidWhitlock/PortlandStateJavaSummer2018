package edu.pdx.cs410J.tennis;

public class TennisGame {

  private final TennisPlayer player1 = new TennisPlayer();
  private final TennisPlayer player2 = new TennisPlayer();

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the TennisKata to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    System.err.println("Missing command line arguments");
    System.exit(1);
  }

  public int getPlayer1Score() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public TennisPlayer getPlayer1() {
    return this.player1;
  }

  public TennisPlayer getPlayer2() {
    return this.player2;
  }

  public TennisPlayer getPlayerWithAdvantage() {
    return null;
  }

  public void playerWinsVolley(TennisPlayer player) {
    player.winsVolley();

  }

  public TennisPlayer getWinner() {
    return getPlayer1().getScore() == 40 ? player1 : null;
  }
}