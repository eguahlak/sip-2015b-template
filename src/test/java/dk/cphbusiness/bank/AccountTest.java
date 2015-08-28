package dk.cphbusiness.bank;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
  
  @Test
  public void testCreateSimpleAccount() throws Exception {
    Bank bank = new Bank(){};
    Customer customer = new Customer(){};
    String number = "4711";
    SimpleAccount account = new SimpleAccount(bank, customer, "4711");
    assertThat(account.getNumber(), is(number));
    }
  
  }
