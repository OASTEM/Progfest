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
        //Reader: This takes everyline from a .txt and puts them as a separate string in an ArrayList
        ArrayList<String> fileIn = new ArrayList<>();   //file input
        ArrayList<String> tempLine = new ArrayList<>(); //To split line into individual words
        BufferedReader br = new BufferedReader(new FileReader("input.txt")); //the string is the directory
        String line; //This is where the br.readLine() will be stored
        while ((line = br.readLine()) != null){ //while the next line is still a thing
            fileIn.add(line);
        }
        
        //String.split(regex);
        String currLine = fileIn.get(0); //takes the first line from the file
        for (String item: currLine.split(" ")){ //Everytime there is a space, it gets split into a separate String
            tempLine.add(item);                 //Does not have to be only a space, can be commas
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
