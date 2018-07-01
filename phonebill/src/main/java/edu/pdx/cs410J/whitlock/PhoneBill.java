package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  private Collection<PhoneCall> calls = new ArrayList<>();

  @Override
  public String getCustomer() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public void addPhoneCall(PhoneCall call) {
    this.calls.add(call);
  }

  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    return this.calls;
  }
}
