package edu.pdx.cs410J.tennis;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class TennisGameTest
{

  @Test
  public void canCreateTennisObject() {
    new TennisGame();
  }

  @Test
  public void whenANewTennisGameIsCreatedBothPlayersHaveAScoreOfZero() {
    TennisGame game = new TennisGame();
    assertThat(game.getPlayer1().getScore(), equalTo(0));
    assertThat(game.getPlayer2().getScore(), equalTo(0));
  }

  @Test
  public void whenANewTennisGameIsCreatedNoPlayerHasAdvantage() {
    TennisGame game = new TennisGame();
    assertThat(game.getPlayerWithAdvantage(), nullValue());
  }

  @Test
  public void whenPlayer1ScoresFirstPlayer1Has15Points() {
    TennisGame game = new TennisGame();
    TennisPlayer player1 = game.getPlayer1();
    game.playerWinsVolley(player1);
    assertThat(player1.getScore(), equalTo(15));
    assertThat(game.getPlayer2().getScore(), equalTo(0));
    assertThat(game.getPlayerWithAdvantage(), nullValue());
  }

  @Test
  public void whenEachPlayerScoresOnceBothHave15Points() {
    TennisGame game = new TennisGame();

    TennisPlayer player1 = game.getPlayer1();
    game.playerWinsVolley(player1);

    TennisPlayer player2 = game.getPlayer2();
    game.playerWinsVolley(player2);

    assertThat(player1.getScore(), equalTo(15));
    assertThat(player2.getScore(), equalTo(15));
    assertThat(game.getPlayerWithAdvantage(), nullValue());

  }

  @Test
  public void aPlayerThatScoresTwiceHasAScoreOf30() {
    TennisGame game = new TennisGame();
    TennisPlayer player1 = game.getPlayer1();
    game.playerWinsVolley(player1);
    game.playerWinsVolley(player1);
    assertThat(player1.getScore(), equalTo(30));
    assertThat(game.getPlayerWithAdvantage(), nullValue());
  }

  @Test
  public void aPlayerThatScoresThriceHasAScoreOf40AndWinsGame() {
    TennisGame game = new TennisGame();
    TennisPlayer player1 = game.getPlayer1();
    game.playerWinsVolley(player1);
    game.playerWinsVolley(player1);
    game.playerWinsVolley(player1);
    assertThat(player1.getScore(), equalTo(40));
    assertThat(game.getPlayerWithAdvantage(), nullValue());
    assertThat(game.getWinner(), equalTo(player1));
  }

}
