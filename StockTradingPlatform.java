import java.util.*;

class Stock {
    String symbol;
    String name;
    double price;
    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }
    public void display() {
        System.out.println(symbol + " - " + name + " : $" + price);
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance = 10000;
    void buyStock(Stock stock, int qty) {
        double cost = stock.price * qty;
        if (cost <= balance) {
            balance -= cost;
            holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + qty);
            System.out.println("Bought " + qty + " shares of " + stock.symbol);
        } else {
            System.out.println("Insufficient balance!");
        }
    }
    void sellStock(Stock stock, int qty) {
        if (holdings.getOrDefault(stock.symbol, 0) >= qty) {
            balance += stock.price * qty;
            holdings.put(stock.symbol, holdings.get(stock.symbol) - qty);
            System.out.println("Sold " + qty + " shares of " + stock.symbol);
        } else {
            System.out.println("You don't have enough shares!");
        }
    }
    void showPortfolio() {
        System.out.println("\n--- Your Portfolio ---");
        System.out.println("Balance: $" + balance);
        System.out.println("Holdings: " + holdings);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Stock> market = new ArrayList<>();
        market.add(new Stock("AAPL", "Apple Inc", 180.5));
        market.add(new Stock("GOOGL", "Google", 2800.0));
        market.add(new Stock("TCS", "TCS India", 3500.0));
        market.add(new Stock("RELIANCE", "Reliance", 2500.0));
        Portfolio myPortfolio = new Portfolio();
        int choice;
        do {
            System.out.println("\n1. View Market\n2. Buy Stock\n3. Sell Stock\n4. My Portfolio\n5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("\n--- Market Stocks ---");
                for (Stock s : market) {
                    s.display();
                }
            } else if (choice == 2) {
                System.out.print("Enter Stock Symbol to Buy: ");
                String sym = sc.next().toUpperCase();
                System.out.print("Quantity: ");
                int qty = sc.nextInt();
                for (Stock s : market) {
                    if (s.symbol.equals(sym)) {
                        myPortfolio.buyStock(s, qty);
                    }
                }
            } else if (choice == 3) {
                System.out.print("Enter Stock Symbol to sell: ");
                String sym = sc.next().toUpperCase();
                System.out.print("Quantity: ");
                int qty = sc.nextInt();
                for (Stock s : market) {
                    if (s.symbol.equals(sym)) {
                        myPortfolio.sellStock(s, qty);
                    }
                }
            } else if (choice == 4) {
                myPortfolio.showPortfolio();
            }
        } while (choice != 5);
        System.out.println("Thank You for Trading!");
    }
}
