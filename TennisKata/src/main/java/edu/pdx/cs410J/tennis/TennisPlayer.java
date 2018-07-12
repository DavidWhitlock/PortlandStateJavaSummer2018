package edu.pdx.cs410J.tennis;

public class TennisPlayer {

  private int score = 0;

  public int getScore() {
    return score;
  }

  public void winsVolley() {
    if (score == 0) {
      score = 15;

    } else if (score == 15) {
      score = 30;

    } else if (score == 30) {
      score = 40;
    }

  }
}
