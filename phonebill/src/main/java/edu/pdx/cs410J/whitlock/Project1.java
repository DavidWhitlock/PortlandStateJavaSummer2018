package edu.pdx.cs410J.whitlock;

import java.util.Collection;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  public static void main(String[] args) {
    PhoneCall call = new PhoneCall();  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    PhoneBill bill = new PhoneBill();
    bill.addPhoneCall(call);

    Collection<PhoneCall> phoneCalls = bill.getPhoneCalls();
    for (PhoneCall c : phoneCalls) {
      System.out.println(c);
    }

    System.err.println("Missing command line arguments");
    for (String arg : args) {
      System.out.println(arg);
    }
    System.exit(1);
  }

}