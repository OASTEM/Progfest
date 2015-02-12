package progfest;
/**
 *
 * @author Peter Yang
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileInputs{
    public static void main (String [] args) throws Exception{
        // Reader: This takes every line from a file and places each line into a separate element
        // in the ArrayList.
        
        // This ArrayList will store each line from our file.
        ArrayList<String> fileIn = new ArrayList<>();
        
        // We'll be storing each word in each line in this ArrayList.
        ArrayList<String> tempLine = new ArrayList<>();
        
        // This is what reads the file and breaks it up into separate lines.
        // "input.txt" is the file we're trying to read in.
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        // This will store the next line that will be read.
        String line;
        
        // This condition checks if we still have any more lines to read - and 
        // if we do, then put it into the line variable.
        while ((line = br.readLine()) != null){
            // Add the line into our array of lines.
            fileIn.add(line);
        }
        
        // This will take the first line from our file.
        String currLine = fileIn.get(0);
        
        // We're going to take the line and split it into separate words. 
        // In other words, we're going to cut the line apart wherever there's a space.
        for (String item : currLine.split(" ")){
            tempLine.add(item);
        }
        
        //Parse String to int
        int[] arrayOfNums = new int[tempLine.size()]; //Makes an array a size of the amount of strings in this line
        int currIndex = 0; //So it knows where in the array to add the new item
        for (String word: tempLine){//For each word in this line
            try{                    //Try converting it to an int
                arrayOfNums[currIndex] = Integer.parseInt(word);
                currIndex++;        //If it works, then you increase your index number so you don't overwrite
            }
            catch (Exception e){    //If it doesn't work, it's not an int and you move on with life
                //That ain't an int
            }
        }
        
        //Writer: Similar to System.out.print() except this is to be written to a file with bw.write()
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt")); //creates a new output
        bw.write("This is a line");
        bw.newLine();
        bw.write("This is another line");
        bw.close(); //remember this or else your output file will not have anything in it
    }
}
