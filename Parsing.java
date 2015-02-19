package progfest;

/**
 *
 * @author Peter
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Parsing{
    // Set forth will be the story of how a text file with dimensions of a 2D grid
    // and the actual grid gets transformed into a String[][] array and even
    // an int[][] array through the power of file reading and parsing!
    public static void main(String[] args) throws IOException{
        ArrayList<String> lines = readFile("input.txt");
        int arrayRows, arrayCols;
        // Let's say the the first line is going to be two digits, separated with
        // a space, denoting the dimensions of a 2D array

        // Generally, you should be using a try/catch when parsing, but if you are 100%
        // That a given line will be a number, you can ignore it
        arrayRows = Integer.parseInt(lines.get(0).split(" ")[0]);
        // How does it work?
        // Integer.parseInt(String) takes a string and converts that to an int
        // lines.get(0) gets the first line
        // .split(" ") returns an array of each string which is separated by a space
        // [0] takes the array returned by .split and grabs the first value
        arrayCols = Integer.parseInt(lines.get(0).split(" ")[1]);
        String[][] grid = new String[arrayRows][arrayCols];
        
        // Nested for loop
        for (int r = 0; r < arrayRows; r++){
            for (int c = 0; c < arrayCols; c++){
                // The grid at r, c will take the line of index r + 1 as index 0 were the dimensions
                // of the grid.
                // We use charAt(c) to take the character at the location r, c from left to right
                // + "" converts the char to a String as when you try to concatenate a String,
                // in this case an empty one, the char gets converted to a String and you don't get
                // an incompatible type error!
                grid[r][c] = lines.get(r + 1).charAt(c) + "";
            }
        }
        
        // Now let's say you wanted to convert this String grid to an int grid
        int[][] intGrid = new int[grid.length][grid[0].length];
        // Look for the method code down below
        intGrid = stringToIntGrid(grid);
        
        // But what about writing the grid? Never fear!
        writeIntArray(intGrid);
        }

    private static ArrayList<String> readFile(String filePath) throws IOException{
        // Creates a local arrayList to hold the input
        ArrayList<String> stuff = new ArrayList<>();
        // Reads a file based on the filePath provided
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        // A string is set to the next line in the file
        // As long as the next line in the file is not null, the provided string
        // will be added to the local arrayList
        while ((line = br.readLine()) != null){
            stuff.add(line);
        }
        return stuff;
    }
    
    private static int[][] stringToIntGrid(String[][] inGrid){
        // Create a local 2D array for this method
        int[][] outGrid = new int[inGrid.length][inGrid[0].length];
        // Nested for loops for row and col
        for (int r = 0; r < inGrid.length; r++){
            for (int c = 0; c < inGrid[0].length; c++){
                // Try parsing, which it shouldn't under a controlled precondition
                // If failed, tell the coder something went wrong
                try{
                    outGrid[r][c] = Integer.parseInt(inGrid[r][c]);
                }
                catch (NumberFormatException e){
                    System.out.println("stringToIntGrid failed");
                }
            }
        }
        // Return the filled grid
        return outGrid;
        
    }
    
    private static void writeIntArray(int[][] inGrid) throws IOException{
        // Creates a local writer
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        // Nested for loop
        for (int r = 0; r < inGrid.length; r++){
            for (int c = 0; c < inGrid[0].length; c++){
                // Unlike System.out.println(), .write() only takes in Strings
                // so we have String.valueOf(int) get a String back from the array
                bw.write(String.valueOf(inGrid[r][c]));
            }
            // We don't want a new line at the end of the file
            if (r < inGrid.length - 1)
                bw.newLine();
        }
        // Remember!
        bw.close();
    }
}
