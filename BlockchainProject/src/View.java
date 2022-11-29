import java.util.ArrayList;

import actors.EOAccount;

public class View {
  public void getBalancesBefore(ArrayList<EOAccount> users) {
    System.out.println("Balances before");
    for (EOAccount a : users) {
      System.out.println(a.address);
      System.out.println(a.balanceETH + " ETH");
      System.out.println(a.balanceUSDT + " USDT");
    }
    System.out.println("\n");
  }

  public void getBalancesAfter(ArrayList<EOAccount> users) {
    System.out.println("Balances after");
    for (EOAccount a : users) {
      System.out.println(a.address);
      System.out.println(a.balanceETH + " ETH");
      System.out.println(a.balanceUSDT + " USDT");
    }
  }
}
