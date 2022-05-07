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
      System.out.println("Read " + (i + 1) + " records");
      ArrayList<Double> result = new ArrayList<Double>();
      for(int j = 0; j < dataList.get(i).data.size(); j++) {
        // Double val1 = dataList.get(i).data.get(j).val1;
        // Double val2 = dataList.get(i).data.get(j).val2;
        // Double val3 = dataList.get(i).data.get(j).val3;

        // result.add(processData(val1, val2, val3, df)[1]);
        System.out.println(' ');        
      }
      double average = result.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
      System.out.println("The resulting form average is " + df.format(average));
      System.out.println(' ');
    }
  }

  public static ArrayList<DataGroup> readData() throws Exception {
    ArrayList<DataGroup> dataList = new ArrayList<DataGroup>();  

    File file = new File("./ProblemNine/data.csv");
    Scanner scanner = new Scanner(file);
    scanner.useDelimiter(" ");
    int count = 0;
    int numberOfSamples = Integer.parseInt(scanner.nextLine());
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
        // @todo: Revise section to read lines until * is the first character
        //        then break out of the if statement. Lines with * should be processed
        //        as Data object and added to ArrayList data. Before breaking out of 
        //        if statement create DataGroup object and add it to ArrayList dataList
        for(int i = 0; i < numberOfRecords; i++) {
          String[] line = scanner.nextLine().split(" ");
          if(count == numberOfSamples) {
            currentLine = null;
            continue first;
          }
          if(line[0].charAt(0) == '*') {
            System.out.println("Data set " + count + " was rejected.");
            currentLine = String.join(" ", line);

            continue first;
          } else if(line.length != numberOfElements) {
            System.out.println("Data set " + count + " was rejected.");
            currentLine = null;
            continue first;
          } else if(line[0].charAt(0) == 'a' || line[0].charAt(0) == 'b') {
            System.out.println("Data set " + count + " was rejected.");
            continue first;
          }
          
          Double val1 = Double.parseDouble(line[0]);
          Double val2 = Double.parseDouble(line[1]);
          Double val3 = null;

          if(line.length == 3) {
            switch(line[2]) {
              case "a": val3 = 3.21;
                        break;
              case "b": val3 = 4.1;
                        break;
              case "c": val3 = 6.8;
                        break;
              default: val3 = 3.21;
                        break;
            }
          }
          
          Data currData = new ProblemNine().new Data(val1, val2, val3);
          data.add(currData);
        }
        int[] currControlRecord = new int[]{numberOfRecords, numberOfElements};
        DataGroup currDataGroup = new ProblemNine().new DataGroup(currControlRecord, data);
        dataList.add(currDataGroup);
      }
      currentLine = null;
    }
    System.out.println(' ');
    scanner.close();
    return dataList;
  }

  public static Double[] processData(Double val1, Double val2, Double val3, DecimalFormat df) {
    Double avg;
    Double form;

    // Constants
    Double exponent = 1.5;
    Double psi = 6.84845;
    Double zeta = 4.26;
    if(val3 == null) {
      avg = (val1 + val2) / 2;
      form = (3.14 * 3.14 * val1) + (psi * (2.44 + Math.pow(val2, exponent)) * 3.0);
      
      System.out.println("AVG = " + df.format(avg));
      System.out.println("FORM = " + df.format(form));
    } else {
      avg = (val1 + val2 + val3) / 3;
      form = 3.14 * 3.14 * val1 * 3.14 + psi * (2.44 + Math.pow(val2, exponent)) * 3 + (Math.pow(zeta, val3)) / val2 * Math.log(val3);
      
      System.out.println("AVG 33 = " + df.format(avg));
      System.out.println("FORM 33 = " + df.format(form));
    }
    Double[] results = new Double[] {avg, form};
    return results;
  }

  // move verification to DataGroup
  public class DataGroup {
    public int[] ctrlRecord;
    public ArrayList<Data> data;

    public DataGroup(int[] ctrlRecord, ArrayList<Data> data) {
      this.ctrlRecord = ctrlRecord;
      this.data = data;
    }

    public Boolean verifyNumberOfElements() {
      for(int i = 0; i < this.data.size(); i++) {
        if(this.data.get(i).size() != this.ctrlRecord[1]) {
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

    public Data(Object... args) {
      this.values = new ArrayList<Double>();
      for(Object arg : args) {
        Double value;
        if(arg.getClass().getName() == "String") {
          switch(arg.toString()) {
            case "a": value = 3.21;
                      break;
            case "b": value = 4.1;
                      break;
            case "c": value = 6.8;
                      break;
            default:  value = 3.21;
                      break;
          }
          this.values.add(value);
          continue;
        }
        System.out.println(this.values);
        value = Double.parseDouble(arg.toString());
        this.values.add(value);
      }
    }

    public int size() {
      return this.values.size();
    }
  }
}