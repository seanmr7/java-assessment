package ProblemNineTest;
import java.util.ArrayList;
import ProblemNine.ProblemNine;

public class ProblemNineTest {
  static int totalTests = 0;
  static int failedTests = 0;
  static int passedTests = 0;
  static ProblemNine problemNine = new ProblemNine();

  // Test
  public static void dataGroupReturnsFalseWhenIncorrectNumberOfElements() {
    totalTests += 1;

    Object[] testArray = {1d, 2d, 3d};
    Object[] testArray2 = {6d, 4d, 9d};
    ProblemNine.Data testData1 = problemNine.new Data(testArray);
    ProblemNine.Data testData2 = problemNine.new Data(testArray2);
    ArrayList<ProblemNine.Data> testList = new ArrayList<ProblemNine.Data>();
    testList.add(testData1);
    testList.add(testData2);
    ProblemNine.DataGroup testDataGroup = problemNine.new DataGroup(new int[] { 1, 2 }, testList);

    if(testDataGroup.verifyNumberOfElements()) {
      System.out.println("Verify Elements test failed");
      failedTests += 1;
    } else {
      passedTests += 1;
    }
  }

  // Test
  public static void dataGroupReturnsTrueWithCorrectNumberOfElements() {
    totalTests += 1;

    Object[] testArray = {1d, 2d, 3d};
    Object[] testArray2 = {6d, 4d, 9d};

    ProblemNine.Data testData1 = problemNine.new Data(testArray);
    ProblemNine.Data testData2 = problemNine.new Data(testArray2);
    ArrayList<ProblemNine.Data> testList = new ArrayList<ProblemNine.Data>();
    testList.add(testData1);
    testList.add(testData2);

    ProblemNine.DataGroup testDataGroup = problemNine.new DataGroup(new int[] { 1, 3 }, testList);

    if(testDataGroup.verifyNumberOfElements()) {
      passedTests += 1;
    } else {
      System.out.println("Verify Elements test failed");
      failedTests += 1;
    }
  }

    // Test
    public static void dataGroupReturnsFalseWithIncorrectNumberOfRecords() {
      totalTests += 1;

      Object[] testArray = {1d, 2d, 3d};
      ProblemNine.Data testData1 = problemNine.new Data(testArray);
      ProblemNine.Data testData2 = problemNine.new Data(testArray);
      ArrayList<ProblemNine.Data> testList = new ArrayList<ProblemNine.Data>();
      testList.add(testData1);
      testList.add(testData2);
      ProblemNine.DataGroup testDataGroup = problemNine.new DataGroup(new int[] {1,2}, testList);
  
      if(testDataGroup.verifyNumberOfRecords()) {
        System.out.println("Verify Records test failed");
        failedTests += 1;
      } else {
        passedTests += 1;
      }
    }

        // Test
        public static void dataGroupReturnsTrueWithCorrectNumberOfRecords() {
          totalTests += 1;
    
          Object[] testArray = {1d, 2d, 3d};
          ProblemNine.Data testData1 = problemNine.new Data(testArray);
          ProblemNine.Data testData2 = problemNine.new Data(testArray);
          ArrayList<ProblemNine.Data> testList = new ArrayList<ProblemNine.Data>();
          testList.add(testData1);
          testList.add(testData2);
          ProblemNine.DataGroup testDataGroup = problemNine.new DataGroup(new int[] {2,2}, testList);
      
          if(testDataGroup.verifyNumberOfRecords()) {
            passedTests += 1;
          } else {
            System.out.println("Verify Records test failed");
            failedTests += 1;
          }
        }

    // Test
    public static void processDataReturnsCorrectValuesWithTwoArguments() {
      totalTests +=1;

      Object[] testArray = {5d, 3d};
      Object[] testArray2 = {8d, 13d};
      Object[] testArray3 = {3d, 11d};
  
      ProblemNine.Data testData1 = problemNine.new Data(testArray);
      ProblemNine.Data testData2 = problemNine.new Data(testArray2);
      ProblemNine.Data testData3 = problemNine.new Data(testArray3);

      testData1.processData();
      testData2.processData();
      testData3.processData();

      if((testData1.results[0].equals(4.000) && testData1.results[1].equals(206.18542417785568)) &&
      (testData2.results[0].equals(10.500) && testData2.results[1].equals(1092.0125216656029415040309046791244005610829888248685805920)) && 
      (testData3.results[0].equals(7.0) && testData3.results[1].equals(829.26284250181145719014831631492801927783549515136648517862))) {
        passedTests += 1;
      } else {
        failedTests +=1;
        System.out.println("Two Value Process Data test failed");
      }
    };


    // Test
    public static void processDataReturnsCorrectValuesWithThreeArguments() {
      totalTests +=1;

      Object[] testArray = {5d, 3d, 3.21};
      Object[] testArray2 = {12d, 7d, 4.1};
      Object[] testArray3 = {22d, 50d, 6.8};
  
      ProblemNine.Data testData1 = problemNine.new Data(testArray);
      ProblemNine.Data testData2 = problemNine.new Data(testArray2);
      ProblemNine.Data testData3 = problemNine.new Data(testArray3);

      testData1.processData();
      testData2.processData();
      testData3.processData();

      if((testData1.results[0].equals(3.736666666666667) && testData1.results[1].equals(352.42895943699545040893995231727278386626170745554408681829)) &&
      (testData2.results[0].equals(7.7) && testData2.results[1].equals(878.8825012545553)) && 
      (testData3.results[0].equals(26.266666666666666) && testData3.results[1].equals(8725.6093525359887186102046896571241572821700459892825129853))) {
        passedTests += 1;
      } else {
        failedTests +=1;
        System.out.println("Three Value Process Data test failed");
      }
    };

  public static void main(String[] args) {
    dataGroupReturnsFalseWhenIncorrectNumberOfElements();
    dataGroupReturnsTrueWithCorrectNumberOfElements();
    dataGroupReturnsFalseWithIncorrectNumberOfRecords();
    dataGroupReturnsTrueWithCorrectNumberOfRecords();

    processDataReturnsCorrectValuesWithTwoArguments();
    processDataReturnsCorrectValuesWithThreeArguments();

    System.out.println("Total Test: " + totalTests);
    System.out.println("Passed Tests: " + passedTests);
    System.out.println("Failed Tests: " + failedTests);
  }
}