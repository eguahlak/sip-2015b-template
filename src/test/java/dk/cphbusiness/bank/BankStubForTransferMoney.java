package dk.cphbusiness.bank;

public class BankStubForTransferMoney implements Bank {

  Customer sonja = new Customer() {};
  SimpleAccount sonjaAccount = new SimpleAccount(this, sonja, "4712");

  @Override
  public SimpleAccount getAccount(String number) {
    return sonjaAccount;
    }

  @Override
  public String getName() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
  }
