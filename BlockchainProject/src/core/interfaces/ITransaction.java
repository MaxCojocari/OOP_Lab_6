package core.interfaces;

public interface ITransaction {
  public String transactionInfo();

  public Object sender();

  public Object receiver();

  public boolean isChecked();

  public void setChecked();
}
