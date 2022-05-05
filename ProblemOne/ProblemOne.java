package ProblemOne;
public class ProblemOne {
  public static void main(String[] args) {
    char[][] pic = new char[6][6];

    for(int i = 0; i < 6; i++) {
      for(int j = 0; j < 6; j++) {
        if(i == j || i == 0 || i == 5) {
          pic[i][j] = '*';
        } else {
          pic[i][j] = '.';
        }
      }
    }

    for(int x = 0; x < 6; x++) {
      for(int y = 0; y < 6; y++) {
        System.out.print(pic[x][y]);
      }
      System.out.println();
    }
  }
}