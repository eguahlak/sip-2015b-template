package dk.cphbusiness.bank;

public class SimpleAccount {
  private final Bank bank;
  private final Customer customer;
  private final String number;
  private int balance = 0;
  
  
  public SimpleAccount(Bank bank, Customer customer, String number) {
    this.bank = bank;
    this.customer = customer;
    this.number = number;
    }
  
  public SimpleAccount(Bank bank, String number) {
    this(bank, null, number);
    }
  
  public String getNumber() {
    return number;
    }
  
  public Bank getBank() {
    return bank;
    }
  
  public Customer getCustomer() {
    if (isInternal())
        throw new UnsupportedOperationException("No customer on internal accounts");
    return customer;
    }
  
  public int getBalance() {
    return balance;
    }
  
  public void transfer(int amount, String targetNumber) {
    SimpleAccount target = bank.getAccount(targetNumber);
    target.deposit(amount);
    balance -= amount;
    }
  
  public void deposit(int amount) {
    balance += amount;
    }
  
  public boolean isInternal() {
    return customer == null;
    }
  
  }
