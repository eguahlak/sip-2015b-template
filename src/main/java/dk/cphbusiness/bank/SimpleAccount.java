package dk.cphbusiness.bank;

import java.util.ArrayList;
import java.util.List;

public class SimpleAccount {
  private final Bank bank;
  private final Customer customer;
  private final String number;
  private final List<Movement> movements = new ArrayList<>();
  
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
    int balance = 0;
    for (Movement movement : movements) {
      if (movement.getSource() == this) balance -= movement.getAmount();
      else balance += movement.getAmount();
      }
    return balance;
    }
  
  public void transfer(int amount, String targetNumber) {
    SimpleAccount target = bank.getAccount(targetNumber);
    Movement movement = new Movement(amount, this, target);
    movements.add(movement);
    target.movements.add(movement);
    }
  
  public boolean isInternal() {
    return customer == null;
    }
  
  public List<Movement> listMovements() {
    return movements;
    }
  
  }
