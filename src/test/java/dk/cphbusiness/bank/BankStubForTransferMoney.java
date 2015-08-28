package dk.cphbusiness.bank;

public class BankStubForTransferMoney implements Bank {

  Customer sonja = new Customer() {};
  SimpleAccount sonjaAccount = new SimpleAccount(this, sonja, "4712");

  @Override
  public SimpleAccount getAccount(String number) {
    return sonjaAccount;
    }
  
  }
