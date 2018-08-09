package edu.pdx.cs410J.kata;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class PacManTest {

  @Test(expected = IllegalArgumentException.class)
  public void boardWithNoPacManThrowsIllegalArgumentException() {
    new PacManGame("");
  }

  @Test
  public void pacManIsFacingWest() {
    PacManGame board = new PacManGame(">");
    assertThat(board.getPacManDirection(), equalTo(PacManDirection.WEST));
  }

  @Test
  public void pacManIsFacingEast() {
    PacManGame board = new PacManGame("<");
    assertThat(board.getPacManDirection(), equalTo(PacManDirection.EAST));
  }

  @Test
  public void pacManIsFacingNorth() {
    PacManGame board = new PacManGame("V");
    assertThat(board.getPacManDirection(), equalTo(PacManDirection.NORTH));
  }

  @Test
  public void pacManIsFacingSouth() {
    PacManGame board = new PacManGame("^");
    assertThat(board.getPacManDirection(), equalTo(PacManDirection.SOUTH));
  }

  @Test
  public void pacManFacingWestCanMoveIntoAnEmptyCell() {
    PacManGame board = new PacManGame(" >");
    board.tick();
    assertThat(board.getRow(0), equalTo("> "));
  }

  @Test
  public void pacManFacingEastCanMoveIntoAnEmptyCell() {
    PacManGame board = new PacManGame("< ");
    board.tick();
    assertThat(board.getRow(0), equalTo(" <"));
  }

  @Test
  public void pacManFacingSouthCanMoveIntoAnEmptyCell() {
    PacManGame board =
      line("^").
      line(" ").board();
    board.tick();
    assertThat(board.getRow(0), equalTo(" "));
    assertThat(board.getRow(1), equalTo("^"));
  }

  private GameBoardBuilder line(String line) {
    return new GameBoardBuilder(line);
  }

  @Test
  public void pacManFacingNorthCanMoveIntoAnEmptyCell() {
    PacManGame board =
      line(" ").
      line("V").board();
    board.tick();
    assertThat(board.getRow(0), equalTo("V"));
    assertThat(board.getRow(1), equalTo(" "));
  }

  @Test
  public void wallPreventsPacManFromMovingEast() {
    PacManGame board = new PacManGame("<|");
    board.tick();
    assertThat(board.getRow(0), equalTo("<|"));
  }

  @Test
  public void wallPreventsPacManFromMovingWest() {
    PacManGame board = new PacManGame("|>");
    board.tick();
    assertThat(board.getRow(0), equalTo("|>"));
  }

  @Test
  public void wallPreventsPacManFromMovingNorth() {
    PacManGame board =
      line("-").
      line("V").board();
    board.tick();
    assertThat(board.getRow(0), equalTo("-"));
    assertThat(board.getRow(1), equalTo("V"));
  }

  @Test
  public void wallPreventsPacManFromMovingSouth() {
    PacManGame board =
      line("^").
      line("-").board();
    board.tick();
    assertThat(board.getRow(0), equalTo("^"));
    assertThat(board.getRow(1), equalTo("-"));
  }

  @Test
  public void pacManFacingWestAtTheEdgeOfTheBoardWrapsAround() {
    PacManGame board = new PacManGame("> ");
    board.tick();
    assertThat(board.getRow(0), equalTo(" >"));
  }

  @Test
  public void pacManFacingEastAtTheEdgeOfTheBoardWrapsAround() {
    PacManGame board = new PacManGame(" <");
    board.tick();
    assertThat(board.getRow(0), equalTo("< "));
  }

  @Test
  public void pacManFacingNorthAtTheEdgeOfTheBoardWrapsAround() {
    PacManGame board =
      line("V").
      line(" ").board();
    board.tick();
    assertThat(board.getRow(0), equalTo(" "));
    assertThat(board.getRow(1), equalTo("V"));
  }

  @Test
  public void pacManFacingSouthAtTheEdgeOfTheBoardWrapsAround() {
    PacManGame board =
      line(" ").
      line("^").board();
    board.tick();
    assertThat(board.getRow(0), equalTo("^"));
    assertThat(board.getRow(1), equalTo(" "));
  }

  @Test
  public void initiallyScoreIsZero() {
    PacManGame board = line(">").board();
    assertThat(board.getScore(), equalTo(0));
  }

  @Test
  public void pacManCanEatAPellet() {
    PacManGame board = line("<..").board();
    board.tick();
    assertThat(board.getRow(0), equalTo(" <."));

    board.tick();
    assertThat(board.getRow(0), equalTo("  <"));
  }

  @Test
  public void whenPacManEatsAPelletScoreIncreasesBy10() {
    PacManGame board = line("<..").board();
    board.tick();
    assertThat(board.getScore(), equalTo(10));

    board.tick();
    assertThat(board.getScore(), equalTo(20));
  }

  @Test
  public void whenPacManEatsAPelletPelletCountDecreasesByOne() {
    PacManGame board = line("<..").board();
    assertThat(board.getPelletCount(), equalTo(2));

    board.tick();
    assertThat(board.getPelletCount(), equalTo(1));

    board.tick();
    assertThat(board.getPelletCount(), equalTo(0));
  }

  @Test
  public void pacManTurnsNorth() {
    PacManGame board = line(">").board();
    board.setPacManDirection(PacManDirection.NORTH);
    assertThat(board.getRow(0), equalTo("V"));
  }

  @Test
  public void pacManTurnsSouth() {
    PacManGame board = line(">").board();
    board.setPacManDirection(PacManDirection.SOUTH);
    assertThat(board.getRow(0), equalTo("^"));
  }

  @Test
  public void forEachRowReturnsExpectedRow() {
    PacManGame board =
      line(" ").
      line("^").
      line(".").
      board();

    Consumer<String> consumer = mock(Consumer.class);
    board.forEachRow(consumer);

    InOrder order = inOrder(consumer);

    order.verify(consumer).accept(" ");
    order.verify(consumer).accept("^");
    order.verify(consumer).accept(".");
  }

  private class GameBoardBuilder {
    private final StringBuilder sb = new StringBuilder();

    GameBoardBuilder(String line) {
      sb.append(line);
    }

    public GameBoardBuilder line(String line) {
      sb.append("\n").append(line);
      return this;
    }

    public PacManGame board() {
      return new PacManGame(this.sb.toString());
    }
  }
}
