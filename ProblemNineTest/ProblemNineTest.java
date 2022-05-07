package ProblemNineTest;
import java.util.ArrayList;
import ProblemNine.ProblemNine;
import java.text.DecimalFormat;

public class ProblemNineTest {
  static int totalTests = 0;
  static int failedTests = 0;
  static int passedTests = 0;
  static ProblemNine problemNine = new ProblemNine();

  // Test
  public static void dataGroupReturnsFalseWhenIncorrectNumberOfElements() {
    totalTests += 1;

    ProblemNine.Data testData1 = problemNine.new Data(1d, 2d, 3d);
    ProblemNine.Data testData2 = problemNine.new Data(1d, 2d, 3d);
    ArrayList<ProblemNine.Data> testList = new ArrayList<ProblemNine.Data>();
    testList.add(testData1);
    testList.add(testData2);
    ProblemNine.DataGroup testDataGroup = problemNine.new DataGroup(new int[] {1,2}, testList);

    if(testDataGroup.verifyNumberOfElements()) {
      System.out.println("Verify Elements test failed");
      failedTests += 1;
    } else {
      passedTests += 1;
    }
  }

  // Test
  public static void dataGroupReturnsTrueWhenCorrectNumberOfElements() {
    totalTests += 1;

    ProblemNine.Data testData1 = problemNine.new Data(1d, 2d, 3d);
    ProblemNine.Data testData2 = problemNine.new Data(1d, 2d, 3d);
    ArrayList<ProblemNine.Data> testList = new ArrayList<ProblemNine.Data>();
    testList.add(testData1);
    testList.add(testData2);

    ProblemNine.DataGroup testDataGroup = problemNine.new DataGroup(new int[] { 1 , 3 }, testList);

    if(testDataGroup.verifyNumberOfElements()) {
      passedTests += 1;
    } else {
      System.out.println("Verify Elements test failed");
      failedTests += 1;
    }
  }

    // Test
    public static void dataGroupReturnsFalseWhenIncorrectNumberOfRecords() {
      totalTests += 1;

      ProblemNine.Data testData1 = problemNine.new Data(1d, 2d, 3d);
      ProblemNine.Data testData2 = problemNine.new Data(1d, 2d, 3d);
      ArrayList<ProblemNine.Data> testList = new ArrayList<ProblemNine.Data>();
      testList.add(testData1);
      testList.add(testData2);
      ProblemNine.DataGroup testDataGroup = problemNine.new DataGroup(new int[] {1,2}, testList);
  
      if(testDataGroup.verifyNumberOfElements()) {
        System.out.println("Verify Elements test failed");
        failedTests += 1;
      } else {
        passedTests += 1;
      }
    }

    // Test
    public static void processDataReturnsCorrectValueWithTwoArguments() {
      totalTests +=1;
      DecimalFormat df = new DecimalFormat("0.000");

      if((ProblemNine.processData(5d, 3d, null, df)[0].equals(4.000) && ProblemNine.processData(5d, 3d, null, df)[1].equals(206.18542417785568)) &&
      (ProblemNine.processData(8d, 13d, null, df)[0].equals(10.500) && ProblemNine.processData(8d, 13d, null, df)[1].equals(1092.0125216656029415040309046791244005610829888248685805920)) && 
      (ProblemNine.processData(72d, 54d, null, df)[0].equals(63.000) && ProblemNine.processData(72d, 54d, null, df)[1].equals(8912.7729560762396386472063990099070850082678096169418549575))) {
        passedTests += 1;
      } else {
        failedTests +=1;
        System.out.println("Process Data test failed");
      }
    };

      // Test
      public static void processDataReturnsCorrectValueWithThreeArguments() {
        totalTests +=1;
        DecimalFormat df = new DecimalFormat("0.000");

        if((ProblemNine.processData(5d, 3d, 3.21, df)[0].equals(3.736666666666667) && ProblemNine.processData(5d, 3d, 3.21, df)[1].equals(352.42895943699545040893995231727278386626170745554408681829)) &&
        (ProblemNine.processData(12d, 7d, 4.1, df)[0].equals(7.7) && ProblemNine.processData(12d, 7d, 4.1, df)[1].equals(878.8825012545553)) && 
        (ProblemNine.processData(22d, 50d, 6.8, df)[0].equals(26.266666666666666) && ProblemNine.processData(22d, 50d, 6.8, df)[1].equals(8725.6093525359887186102046896571241572821700459892825129853))) {
          passedTests += 1;
        } else {
          failedTests +=1;
          System.out.println("Process Data test failed");
        }
      };
    

  public static void main(String[] args) {
    dataGroupReturnsFalseWhenIncorrectNumberOfElements();
    dataGroupReturnsTrueWhenCorrectNumberOfElements();
    dataGroupReturnsFalseWhenIncorrectNumberOfRecords();
    processDataReturnsCorrectValueWithTwoArguments();
    processDataReturnsCorrectValueWithThreeArguments();

    System.out.println("Total Test: " + totalTests);
    System.out.println("Passed Tests: " + passedTests);
    System.out.println("Failed Tests: " + failedTests);
  }
}