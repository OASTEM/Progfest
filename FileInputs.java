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
        
        // Now we're going to take the list of strings inside tempLine and convert
        // each string into a number.
        
        // First, we need to make an array the same size as tempLine to hold all
        // our numbers.
        int[] arrayOfNums = new int[tempLine.size()];
        
        // We need to know where in the array we're going to be storing each number.
        // In other words, this is our counter variable, much like in a for loop.
        int currIndex = 0;
        
        // We'll be going through each item in the tempLine list.
        for (String word: tempLine){
            // We might run into an error trying to convert the string into an integer,
            // so we'll try it.
            try{
                // Set the array at the current index to the number.
                arrayOfNums[currIndex] = Integer.parseInt(word);
                
                // We've successfully converted the number - now, we move on to the next
                // index so that we can convert the next item.
                currIndex++;
            }
            catch (Exception e){
                // We ended up running into an error converting the string into a number,
                // so we end up here. 
            }
        }
        
        // Writer: Essentially the counterpart to BufferedReader above - instead of reading
        // from a file, we're now going to write to it.
        
        // Like BufferedReader above, this is how we write to the file, where "output.txt"
        // is the file we want to write to.
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        
        // We can now write data to the file.
        bw.write("This is a line");
        
        // This moves the data to a new line.
        bw.newLine();
        
        bw.write("This is another line");
        
        // After you complete all your writing, make sure to close the file writer
        // so that the program knows you're done. If you don't do this, the program
        // will not end up actually writing anything.
        bw.close(); 
    }
}
