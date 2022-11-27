package core;

import actors.EOAccount;
import java.util.*;

public class SwapTx extends Transaction {
    public EOAccount exchange;
    public String tokenIn;
    public String tokenOut;
    public double amountIn;
    private Map<String, Double> exchangeRates = new HashMap<String, Double>();

    public SwapTx(
            EOAccount sender,
            EOAccount exchange,
            String tokenIn,
            String tokenOut,
            double amountIn) {
        super(sender, exchange);
        this.exchange = exchange;
        this.tokenIn = tokenIn;
        this.tokenOut = tokenOut;
        this.amountIn = amountIn;
        exchangeRates.put("ETH", 0.5);
        exchangeRates.put("USDT", 0.65);

        if (tokenIn.equals("ETH")) {
            if (sender.sendETH(amountIn, exchange.address)) {
                exchange.receiveETH(amountIn);
                returnAssetsUSDT(sender);
            }
        } else if (tokenIn.equals("USDT")) {
            if (sender.sendUSDT(amountIn, exchange.address)) {
                exchange.receiveUSDT(amountIn);
                returnAssetsETH(sender);
            }
        }
    }

    public String transactionInfo() {
        String s = "SwapTx \n";
        s += "Sender:\t\t" + super.sender().address + "\n";
        s += "Receiver:\t" + super.receiver().address + "\n";
        s += "AmountIn:\t" + amountIn + " " + tokenIn + "\n";
        s += "AmountOut:\t" + amountOut(tokenOut) + " " + tokenOut + "\n";
        return s;
    }

    public double amountOut(String asset) {
        return amountIn * exchangeRates.get(asset);
    }

    public void returnAssetsUSDT(EOAccount sender) {
        if (exchange.balanceUSDT < amountOut("USDT")) {
            System.out.println("Exchange " + exchange.address + " doesn't have enough USDT for swap!");
            exchange.sendETH(amountIn, sender.address);
            sender.receiveETH(amountIn);
            return;
        }
        System.out.println("Amount USDT out: " + amountOut("USDT"));
        exchange.sendUSDT(amountOut("USDT"), sender.address);
        sender.receiveUSDT(amountOut("USDT"));
    }

    public void returnAssetsETH(EOAccount sender) {
        if (exchange.balanceETH < amountOut("ETH")) {
            System.out.println("Exchange " + exchange.address + " doesn't have enough ETH for swap!");
            exchange.sendUSDT(amountIn, sender.address);
            sender.receiveUSDT(amountIn);
            return;
        }
        ;
        System.out.println("Amount ETH out: " + amountOut("ETH"));
        exchange.sendETH(amountOut("ETH"), sender.address);
        sender.receiveETH(amountOut("ETH"));
    }
}
