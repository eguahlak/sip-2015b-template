package dk.cphbusiness.bank;

public class Movement {
  private final int amount;
  private final SimpleAccount source;
  private final SimpleAccount target;

  public Movement(int amount, SimpleAccount source, SimpleAccount target) {
    this.amount = amount;
    this.source = source;
    this.target = target;
    }

  public int getAmount() {
    return amount;
    }

  public SimpleAccount getSource() {
    return source;
    }

  public SimpleAccount getTarget() {
    return target;
    }
  
  }
