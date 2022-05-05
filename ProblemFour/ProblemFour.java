package ProblemFour;
public class ProblemFour {
  public static void main(String args[]) {
    int[] testArr;
    testArr = new int[]{1, 6, 12, 3, 63};

    System.out.println(largestVal(testArr));
  }

  public static int largestVal(int[] array) {
    int largest = array[0];

    for(int i = 0; i < array.length; i++) {
      if(array[i] > largest) {
        largest = array[i];
      }
    }

    return largest;
  }
}