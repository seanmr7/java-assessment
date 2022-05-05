package ProblemFive;
import java.util.*;

public class ProblemFive {
  public static void retainPositiveNumbers(int[] arr) {
    ArrayList<Integer> postitiveArray = new ArrayList<>();

    for(int i = 0; i < arr.length; i++) {

      if(arr[i] > 0) {
        postitiveArray.add(arr[i]) ;
      }
    }
    Collections.sort(postitiveArray);

    System.out.println(postitiveArray);
  }

  public static void main(String args[]) {
    int[] testArr = new int[]{100, 1, -5, 10, -2, 6};

    retainPositiveNumbers(testArr);
  }
}