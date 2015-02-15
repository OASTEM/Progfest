package progfest;
/**
 *
 * @author Peter
 */
public class ArraysOnAWholeNewLevel {
    public static void main(String[] args) {
        // Creates a new one-dimensional array that holds 10 items, from index 0 to 9
        int[] oneD = new int[10];
        // Creates a two-dimensional array with 10 rows and 5 columns
        // Think of it as a table.
        int[][] twoD = new int[10][5];
        
        // Fills the 1D array from 0-9
        for (int i = 0; i < oneD.length; i++) {
            oneD[i] = i;
        }
        
        // Fills the 2D array with the product of the row and column index
        for (int row = 0; row < twoD.length; row++) {
            // twoD[0].length gets the length of the 1st col (with index 0), which is the
            // same size as any other column as this is a rectangular array
            for (int col = 0; col < twoD[0].length; col++) {
                twoD[row][col] = row * col;
            }
        }
        
        // Prints out each item in the 2D array, creating a new line every
        // time a row is completed
        for (int row = 0; row < twoD.length; row++) {
            for (int col = 0; col < twoD[0].length; col++) {
                System.out.print(twoD[row][col]);
            }
            System.out.println();
        }
    }
}
