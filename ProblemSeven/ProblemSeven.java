package ProblemSeven;
import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class ProblemSeven {
  // Data type to store time and price as object.
  public class StockData {
    public String time;
    public Double price;

    public StockData(String time, Double price) {
      this.time = time;
      this.price = price;
    }
  }

  // Method to read data from the table of times and stock prices and create StockData objects.
  public static ArrayList<StockData> readData() throws Exception {
    String line = "";
    ArrayList<StockData> stockDataList = new ArrayList<StockData>();

    File file = new File("./ProblemSeven/stockData.csv");
    Scanner scanner = new Scanner(file);
    scanner.useDelimiter(",");

    while(scanner.hasNext()) {
      line = scanner.nextLine();
      String[] currentLine = line.split(",");

      String time = currentLine[3] + " " + currentLine[4];
      Double price = NumberFormat.getInstance().parse(currentLine[5]).doubleValue();
      StockData stockData = new ProblemSeven().new StockData(time, price);

      stockDataList.add(stockData);
    }

    scanner.close();     
    return stockDataList;
  }

  public static StockData[] pickStocks(ArrayList<StockData> stockData) {
    int n1 = 0;
    int n2 = 1;
    Double maxProfit = 0.0;
    Double currentProfit = 0.0;
    StockData[] buyAndSell = new StockData[2];

    for(int i = 0; i < stockData.size(); i++ ) {
      n1 = i;
      currentProfit = stockData.get(n1).price - stockData.get(n2).price;
      if(currentProfit > maxProfit) {
        maxProfit = currentProfit;

        // Replace the first element in the array for when to buy
        buyAndSell[0] = stockData.get(n2);

        // Replace the second element in the array for when to sell
        buyAndSell[1] = stockData.get(n1);
      }
      if(stockData.get(n1).price < stockData.get(n2).price) {
        n2 = n1;
      }
    }

    System.out.println("Buy at " + buyAndSell[0].time + " for $" + buyAndSell[0].price);
    System.out.println("Sell at " + buyAndSell[1].time + " for $" + buyAndSell[1].price);
    return buyAndSell;
  }


  public static void main(String[] args) throws Exception {
    ArrayList<StockData> stockDataList = new ArrayList<StockData>();
    stockDataList = readData();

    pickStocks(stockDataList);    
  }
}



