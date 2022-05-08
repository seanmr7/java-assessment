package ProblemNine;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

public class ProblemNine {
  public static void main(String[] args) throws Exception {
    ArrayList<DataGroup> dataList = new ArrayList<DataGroup>();
    DecimalFormat df = new DecimalFormat("0.000");

    dataList = readData();
    for(int i = 0; i < dataList.size(); i++) {
      System.out.println("Reading Sample: " + (i + 1));
      System.out.println("*" + dataList.get(i).ctrlRecord[0] + " " + dataList.get(i).ctrlRecord[1]);
      for(int j = 0; j < dataList.get(i).data.size(); j++) {
        System.out.println(dataList.get(i).data.get(j).values);
      }

      if(!dataList.get(i).verifyNumberOfElements() || !dataList.get(i).verifyNumberOfRecords()) {
        System.out.println("Sample rejected due to discrepancies in data counts.");
      } else {
        ArrayList<Double> runningResults = new ArrayList<Double>();
        for(int j = 0; j < dataList.get(i).data.size(); j++) {
          System.out.println();
          System.out.println(dataList.get(i).data.get(j).values);

          dataList.get(i).data.get(j).processData();
          dataList.get(i).data.get(j).printResults(df);

          if(!dataList.get(i).data.get(j).results[1].isNaN()) {
            runningResults.add(dataList.get(i).data.get(j).results[1]);
          }
        }
        double average = runningResults.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        System.out.println();
        System.out.println("The resulting average of the form value is " + df.format(average));
      }
      System.out.println();
    }
  }

  public static ArrayList<DataGroup> readData() throws Exception {
    ArrayList<DataGroup> dataList = new ArrayList<DataGroup>();  

    File file = new File("./ProblemNine/data.csv");
    Scanner scanner = new Scanner(file);
    scanner.useDelimiter(" ");
    int count = 0;
    int numberOfSamples = Integer.parseInt(scanner.nextLine().split(" ")[0]);
    String currentLine = null;

    first:
    while(scanner.hasNextLine()) {
      if(currentLine == null) {
        currentLine = scanner.nextLine();
      }

      if(currentLine.charAt(0) == '*') {
        count++;

        ArrayList<Data> data = new ArrayList<Data>();
        int numberOfRecords = Integer.parseInt("0" + currentLine.charAt(1));
        int numberOfElements = Integer.parseInt("0" + currentLine.charAt(3));
        String[] line = scanner.nextLine().split(" ");

        while(line[0].charAt(0) != '*') {
          Object[] lineData = new Object[line.length];

          for(int i = 0; i < line.length; i++) {
            Object val = line[i];
            lineData[i] = val;
          }
          Data currData = new ProblemNine().new Data(lineData);
          data.add(currData);

          if(scanner.hasNextLine()) {
            line = scanner.nextLine().split(" ");
          } else {
            break;
          }
        }
        currentLine = String.join(" ", line);
        int[] currControlRecord = new int[]{numberOfRecords, numberOfElements};
        DataGroup currDataGroup = new ProblemNine().new DataGroup(currControlRecord, data);
        dataList.add(currDataGroup);

        if(count == numberOfSamples) {
          currentLine = null;
          continue first;
        }
      }
    }
    scanner.close();
    return dataList;
  }

  public class DataGroup {
    public int[] ctrlRecord;
    public ArrayList<Data> data;

    public DataGroup(int[] ctrlRecord, ArrayList<Data> data) {
      this.ctrlRecord = ctrlRecord;
      this.data = data;
    }

    public Boolean verifyNumberOfElements() {
      for(int i = 0; i < this.data.size(); i++) {
        if(this.data.get(i).values.size() != this.ctrlRecord[1]) {
          return false;
        }
      }
      return true;
    }

    public Boolean verifyNumberOfRecords() {
      if(this.data.size() != ctrlRecord[0]) {
        return false;
      } else { return true; }
    }
  }

  public class Data {
    public ArrayList<Double> values;
    public Double[] results = new Double[2];

    // Constants
    private Double exponent = 1.5;
    private Double psi = 6.84845;
    private Double zeta = 4.26;

    public Data(Object[] args) {
      this.values = new ArrayList<Double>();
      for(int i = 0; i < args.length; i++) {
        Double value = null;
        try {
          value = Double.parseDouble(args[i].toString());
        } catch (NumberFormatException e) {
          if(args[i].getClass().getName().equals("java.lang.String")) {
            switch(args[i].toString()) {
              case "a": value = 3.21;
                        break;
              case "b": value = 4.1;
                        break;
              case "c": value = 6.8;
                        break;
              default:  value = 3.21;
                        break;
            }
          }
        }
        this.values.add(value);
      }
    }

    public void processData() {
      Double avg;
      Double form;
      if(this.values.size() == 2) {
        avg = (this.values.get(0) + this.values.get(1)) / 2;
        form = (3.14 * 3.14 * this.values.get(0)) + (psi * (2.44 + Math.pow(this.values.get(1), exponent)) * 3.0);

      } else {
        avg = (this.values.get(0) + this.values.get(1) + this.values.get(2)) / 3;
        form = 3.14 * 3.14 * this.values.get(0) * 3.14 + psi * (2.44 + Math.pow(this.values.get(1), exponent)) * 3 + (Math.pow(zeta, this.values.get(2))) / this.values.get(1) * Math.log(this.values.get(2));
      }
      this.results[0] = avg;
      this.results[1] = form;
    }

    public void printResults(DecimalFormat df) {
      if(this.values.size() == 2) {
        System.out.println("AVG = " + df.format(this.results[0]));
        System.out.println("FORM = " + df.format(this.results[1]));
      } else {
        System.out.println("AVG 33 = " + df.format(this.results[0]));
        System.out.println("FORM 33 = " + df.format(this.results[1]));
      }
    }
  }
}