package edu.pdx.cs410J.kata.server;

import edu.pdx.cs410J.kata.client.PhoneBill;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PacManServiceImplTest {

  @Test
  public void serviceReturnsExpectedPhoneBill() {
    PhoneBillServiceImpl service = new PhoneBillServiceImpl();
    PhoneBill bill = service.getPhoneBill();
    assertThat(bill.getPhoneCalls().size(), equalTo(1));
  }
}