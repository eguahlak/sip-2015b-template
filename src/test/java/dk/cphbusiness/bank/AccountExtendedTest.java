package dk.cphbusiness.bank;

import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.Matchers;
import static org.jmock.AbstractExpectations.returnValue;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountExtendedTest {
  final Mockery context = new JUnitRuleMockery();
  
  @Test
  public void testCreateCustomerAccount() throws Exception {
    Bank bank = context.mock(Bank.class);
    Customer customer = context.mock(Customer.class);
    String number = "4711";
    
    SimpleAccount account = new SimpleAccount(bank, customer, number);
    
    assertThat(account.getBank(), is(bank));
    assertThat(account.getCustomer(), is(customer));
    assertThat(account.getNumber(), is(number));
    assertThat(account.getBalance(), is(0));
    assertFalse(account.isInternal());
    }
  
  @Test
  public void testCreateInternalAccount() throws Exception {
    Bank bank = context.mock(Bank.class);
    String number = "4711";
    
    SimpleAccount account = new SimpleAccount(bank, number);
    
    assertThat(account.getBank(), is(bank));
    assertThat(account.getNumber(), is(number));
    assertThat(account.getBalance(), is(0));
    assertTrue("Account should be internal", account.isInternal());
    }
  
  @Test(expected = UnsupportedOperationException.class)
  public void testCreateInternalAccountGetCustomer() throws Exception {
    Bank bank = context.mock(Bank.class);
    String number = "4711";
    
    SimpleAccount account = new SimpleAccount(bank, number);
    account.getCustomer();
    }

  @Test
  public void testTransferAmount() throws Exception {
    String sourceNumber = "4711";
    String targetNumber = "4712";
    int amount = 10000;

    Customer kurt = context.mock(Customer.class, "kurt");
    final Customer sonja = context.mock(Customer.class, "sonja");
    final Bank bank = context.mock(Bank.class);

    SimpleAccount source = new SimpleAccount(bank, kurt, sourceNumber);
    SimpleAccount target = new SimpleAccount(bank, sonja, targetNumber);
    
    context.checking(new Expectations(){{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
      }});
    
    source.transfer(amount, targetNumber);
    
    assertThat(source.getBalance(), is(-amount));
    assertThat(target.getBalance(), is(amount));
    }

  @Test
  public void testTransferAmounts() throws Exception {
    String sourceNumber = "4711";
    String targetNumber = "4712";
    int first = 10000;
    int second = 15000;
    
    Customer kurt = context.mock(Customer.class, "kurt");
    Customer sonja = context.mock(Customer.class, "sonja");
    Bank bank = context.mock(Bank.class);

    SimpleAccount source = new SimpleAccount(bank, kurt, sourceNumber);
    SimpleAccount target = new SimpleAccount(bank, sonja, targetNumber);
    
    context.checking(new Expectations(){{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
      oneOf(bank).getAccount(sourceNumber);
      will(returnValue(source));
      }});
    
    source.transfer(first, targetNumber);
    target.transfer(second, sourceNumber);
    
    assertThat(source.getBalance(), is(-first + second));
    assertThat(target.getBalance(), is(first - second));
    }

  // Movement creation tests go here!
  
  @Test
  public void testListMovements() throws Exception {
    String sourceNumber = "4711";
    String targetNumber = "4712";
    int first = 10000;
    int second = 15000;
    
    Customer kurt = context.mock(Customer.class, "kurt");
    Customer sonja = context.mock(Customer.class, "sonja");
    Bank bank = context.mock(Bank.class);

    SimpleAccount source = new SimpleAccount(bank, kurt, sourceNumber);
    SimpleAccount target = new SimpleAccount(bank, sonja, targetNumber);
    
    context.checking(new Expectations(){{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
      oneOf(bank).getAccount(sourceNumber);
      will(returnValue(source));
      }});
    
    source.transfer(first, targetNumber);
    target.transfer(second, sourceNumber);
    
    assertThat(source.listMovements().size(), is(2));
    assertThat(target.listMovements().size(), is(2));
    
    }

  }
