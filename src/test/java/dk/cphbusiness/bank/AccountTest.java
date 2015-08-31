package dk.cphbusiness.bank;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;


public class AccountTest {
  final Mockery context = new JUnitRuleMockery();
  
  @Test
  public void testCreateSimpleAccount() throws Exception {
    Bank bank = new BankStubForTransferMoney();
    Customer customer = new Customer(){};
    String number = "4711";
    SimpleAccount account = new SimpleAccount(bank, customer, "4711");
    assertThat(account.getNumber(), is(number));
    assertThat(account.getBank(), is(bank));
    assertThat(account.getCustomer(), is(customer));
    assertThat(account.getBalance(), is(0));
    }
  
  @Test
  public void testTransferAmountWithStub() throws Exception {
    Customer kurt = new Customer(){};
    Customer sonja = new Customer(){};
    String sourceNumber = "4711";
    String targetNumber = "4712";
    Bank bank = new BankStubForTransferMoney();

    int amount = 10000;
    SimpleAccount account = new SimpleAccount(bank, kurt, sourceNumber);
    
    account.transfer(amount, targetNumber);
    
    assertThat(account.getBalance(), is(-amount));
    assertThat(bank.getAccount(targetNumber).getBalance(), is(amount));
    }
  
  @Test
  public void testTransferAmountWithMock() throws Exception {
    Customer kurt = context.mock(Customer.class, "kurt");
    final Customer sonja = context.mock(Customer.class, "sonja");
    String sourceNumber = "4711";
    String targetNumber = "4712";
    final Bank bank = context.mock(Bank.class);

    int amount = 10000;
    SimpleAccount source = new SimpleAccount(bank, kurt, sourceNumber);
    SimpleAccount target = new SimpleAccount(bank, sonja, targetNumber);
    
//    Expectations expectations = new Expectations();
//    expectations.oneOf(bank).getAccount(targetNumber);
//    expectations.will(Expectations.returnValue(target));
//    context.checking(expectations);
    
    context.checking(new Expectations(){{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
      }});
    
    source.transfer(amount, targetNumber);
    
    assertThat(source.getBalance(), is(-amount));
    assertThat(target.getBalance(), is(amount));
    }
  
  
  
  }