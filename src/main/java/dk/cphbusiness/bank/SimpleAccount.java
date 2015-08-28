package dk.cphbusiness.bank;

public class SimpleAccount {
  private final Bank bank;
  private final Customer customer;
  private final String number;
  
  
  public SimpleAccount(Bank bank, Customer customer, String number) {
    this.bank = bank;
    this.customer = customer;
    this.number = number;
    }
  
  public String getNumber() {
    return number;
    }
  
  public Bank getBank() {
    return bank;
    }
  
  public Customer getCustomer() {
    return customer;
    }
  
  }
