package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {
  @Override
  public String getCaller() {
    return "No Caller yet";
  }

  @Override
  public String getCallee() {
    return "This method is not implemented yet";
  }

  @Override
  public String getStartTimeString() {
    return "Start time";
  }

  @Override
  public String getEndTimeString() {
    return "End Time";
  }
}
