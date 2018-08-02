package edu.pdx.cs410J.kata;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class PacManTest {

  @Test(expected = IllegalArgumentException.class)
  public void boardWithNoPacManThrowsIllegalArgumentException() {
    new GameBoard("");
  }

  @Test
  public void pacManIsFacingWest() {
    GameBoard board = new GameBoard(">");
    assertThat(board.getPacManDirection(), equalTo(PacManDirection.WEST));
  }

  @Test
  public void pacManIsFacingEast() {
    GameBoard board = new GameBoard("<");
    assertThat(board.getPacManDirection(), equalTo(PacManDirection.EAST));
  }

  @Test
  public void pacManIsFacingNorth() {
    GameBoard board = new GameBoard("V");
    assertThat(board.getPacManDirection(), equalTo(PacManDirection.NORTH));
  }

  @Test
  public void pacManIsFacingSouth() {
    GameBoard board = new GameBoard("^");
    assertThat(board.getPacManDirection(), equalTo(PacManDirection.SOUTH));
  }

  @Test
  public void pacManFacingWestCanMoveIntoAnEmptyCell() {
    GameBoard board = new GameBoard(" >");
    board.tick();
    assertThat(board.getRow(0), equalTo("> "));
  }

  @Test
  public void pacManFacingEastCanMoveIntoAnEmptyCell() {
    GameBoard board = new GameBoard("< ");
    board.tick();
    assertThat(board.getRow(0), equalTo(" <"));
  }

  @Test
  public void pacManFacingSouthCanMoveIntoAnEmptyCell() {
    GameBoard board =
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
    GameBoard board =
      line(" ").
      line("V").board();
    board.tick();
    assertThat(board.getRow(0), equalTo("V"));
    assertThat(board.getRow(1), equalTo(" "));
  }

  @Ignore
  @Test
  public void pacManFacingWestAtTheEdgeOfTheBoardWrapsAround() {
    GameBoard board = new GameBoard("> ");
    board.tick();
    assertThat(board.getRow(0), equalTo(" >"));
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

    public GameBoard board() {
      return new GameBoard(this.sb.toString());
    }
  }
}
