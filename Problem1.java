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
import java.util.Collections;

public class Problem1{

    final static int TITLE = 0;
    // Will be asked to sort on one of the three criteries
    final static int GENRE = 1;
    final static int AUDIENCE_RATING = 2;
    final static int CRITIC_RATING = 3;

    public static void main(String[] args) throws Exception{
        // File read
        BufferedReader br = new BufferedReader(new FileReader("P1Input.txt"));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null){
            lines.add(line);
        }

        // Reads first line for logistics
        String[] line1 = lines.get(0).split(",");
        int state;

        // Splits each movie stuffs into an array and puts all of them into an ArrayList
        ArrayList<String[]> movies = new ArrayList<>();
        int startLine = 1;
        int amountOfMovies = Integer.parseInt(line1[0]);
        for (int movieNum = 0; movieNum < amountOfMovies; movieNum++){
            String[] tempMovie = new String[4];
            // Reads 4 lines and considers that one "movie"
            for (int i = 0; i < 4; i++){
                tempMovie[i] = lines.get(startLine);
                startLine++;
            }
            movies.add(tempMovie);
        }

        // Sorting time
        state = AUDIENCE_RATING;
        ArrayList<String[]> sortaSorted = new ArrayList<>();
        ArrayList<String[]> subGroup = new ArrayList<>();
        ArrayList<String[]> anotherSubGroup = new ArrayList<>();
        int highestAudienceRating = 5;
        int highestCriticRating = 5;

        // There are no break statements because this is one pass that goes one after another
        // Once one sorting state is completed, it goes to the next iteration of sorting
        // Sorta like writing individual methods but without input/outputs
        switch (state){
            case AUDIENCE_RATING: {
                // Groups by audience rating
                while (highestAudienceRating >= 0){
                    for (int i = 0; i < movies.size(); i++){
                        if (Integer.parseInt(movies.get(i)[AUDIENCE_RATING]) == highestAudienceRating){
                            sortaSorted.add(movies.get(i));
                            movies.remove(i);
                            i--; //To compensate for the removed movie
                        }
                    }
                    highestAudienceRating--;
                }
                if (movies.isEmpty()){
                    state = CRITIC_RATING;
                    System.out.println("Audience rating sorting successful.");
                }
                else{
                    System.out.println("Audience rating sorting failed.");
                    for (String[] movie : movies){
                        System.out.println(movie[TITLE]);
                    }
                }
                for (String[] movie : sortaSorted){
                    // So the list is now updated
                    movies.add(movie);
                }
                // So we can reuse this on the next sorting pass
                sortaSorted.clear();
            }
            case CRITIC_RATING: {
                // First determine subgroups by looking for groups of the previous criteria
                highestAudienceRating = 5;
                while (highestAudienceRating >= 0){
                    highestCriticRating = 5;
                    for (int i = 0; i < movies.size(); i++){
                        if (Integer.parseInt(movies.get(i)[AUDIENCE_RATING]) == highestAudienceRating){
                            subGroup.add(movies.get(i));
                            movies.remove(i);
                            i--;
                        }
                    }
                    highestAudienceRating--;
                    // After the subgroup is determined, it sorts in the same manner as the A_Rating
                    while (highestCriticRating >= 0){
                        for (int i = 0; i < subGroup.size(); i++){
                            if (Integer.parseInt(subGroup.get(i)[CRITIC_RATING]) == highestCriticRating){
                                sortaSorted.add(subGroup.get(i));
                                subGroup.remove(i);
                                i--;
                            }
                        }
                        highestCriticRating--;
                    }
                }
                if (movies.isEmpty()){
                    state = GENRE;
                    System.out.println("Critic rating sorting successful.");
                }
                else{
                    System.out.println("Critic rating sorting failed.");
                    for (String[] movie : movies){
                        System.out.println(movie[TITLE]);
                    }
                }
                // Just like last time, move the sorted movies into the main movies array
                for (String[] movie : sortaSorted){
                    movies.add(movie);
                }
                sortaSorted.clear();
            }
            case GENRE: {
                // Group by C_Rating
                highestCriticRating = 5;
                while (highestCriticRating >= 0){
                    for (int i = 0; i < movies.size(); i++){
                        if (Integer.parseInt(movies.get(i)[CRITIC_RATING]) == highestCriticRating){
                            subGroup.add(movies.get(i));
                            movies.remove(i);
                            i--;
                        }
                    }
                    for (int i = 0; i < subGroup.size(); i++){
                        if (!subGroup.get(i)[GENRE].contains(",")){
                            anotherSubGroup.add(subGroup.get(i));
                            subGroup.remove(i);
                            i--;
                        }
                    }
                    if (anotherSubGroup.size() > 0){
                        sortaSorted.addAll(sortByGenre(anotherSubGroup));
                        anotherSubGroup.clear();
                    }
                    for (int i = 0; i < subGroup.size(); i++){
                        if (subGroup.get(i)[GENRE].contains(",")){
                            anotherSubGroup.add(subGroup.get(i));
                            subGroup.remove(i);
                            i--;
                        }
                        else{
                            System.out.println("anotherSubGroup splitting malfunction");
                        }
                    }
                    highestCriticRating--;
                    if (anotherSubGroup.size() > 0){
                        sortaSorted.addAll(sortByTitle(anotherSubGroup));
                        anotherSubGroup.clear();
                    }
                }
                if (movies.isEmpty()){
                    state++;
                    System.out.println("Genre sorting successful.");
                }
                else{
                    System.out.println("Genre sorting failed.");
                    for (String[] movie : movies){
                        System.out.println(movie[TITLE]);
                    }
                }
                for (String[] movie : sortaSorted){
                    movies.add(movie);
                }
                sortaSorted.clear();
            }
            default:
                break;
        }

        // Read the first line to find out what Jack wants
        switch (line1[1]){
            case "Audience Rating":
                state = AUDIENCE_RATING;
                break;
            case "Critic Rating":
                state = CRITIC_RATING;
                break;
            case "Genre(s)":
                state = GENRE;
                break;
            default:
                state = -1;
                System.out.println("The input sorting type is jacked up");
                break;
        }

        // Determins desc or asc
        boolean isDecending = false;
        if (line1[2].equals("DESC")){
            isDecending = true;
        }
        writeMovies(movies, state, isDecending, line1);
    }

    public static ArrayList<String[]> sortByTitle(ArrayList<String[]> inList){
        ArrayList<String[]> temp = new ArrayList<>();
        int index;
        String[] highestAlphaNum = inList.get(0);
        while (inList.size() > 0){
            index = 0;
            for (int i = 0; i < inList.size(); i++){
                if (inList.get(i)[TITLE].compareTo(highestAlphaNum[TITLE]) > 0){
                    highestAlphaNum = inList.get(i);
                    index = i;
                }
            }
            temp.add(inList.get(index));
            inList.remove(index);
        }
        return temp;
    }

    public static ArrayList<String[]> sortByGenre(ArrayList<String[]> inList){
        ArrayList<String[]> temp = new ArrayList<>();
        int index;
        String[] highestAlphaNum = inList.get(0);
        while (inList.size() > 0){
            index = 0;
            for (int i = 0; i < inList.size(); i++){
                if (inList.get(i)[GENRE].compareTo(highestAlphaNum[GENRE]) > 0){
                    highestAlphaNum = inList.get(i);
                    index = i;
                }
            }
            temp.add(inList.get(index));
            inList.remove(index);
        }
        return temp;
    }

    public static void printMovie(String[] inArray){
        System.out.println("Title: " + inArray[TITLE]);
        System.out.println("Genre(s): " + inArray[GENRE]);
        System.out.println("Audience Rating: " + inArray[AUDIENCE_RATING]);
        System.out.println("Critic Rating: " + inArray[CRITIC_RATING]);
        System.out.println("-");
    }

    public static void writeMovies(ArrayList<String[]> inMovie, int inState, boolean isDesc, String[] firstLine) throws IOException{
        ArrayList<String[]> writeOrder = new ArrayList<>();
        BufferedWriter bw = new BufferedWriter(new FileWriter("P1Output.txt"));
        bw.write("Grouped By: " + firstLine[1] + ", " + firstLine[2]);
        bw.newLine();
        // Put movies in the order they will be printed in
        int currVal = 5;
        if (inState == AUDIENCE_RATING){
            while (currVal >= 0){
                for (String[] movie : inMovie){
                    if (Integer.parseInt(movie[AUDIENCE_RATING]) == currVal){
                        writeOrder.add(movie);
                    }
                }
                currVal--;
            }
        }
        else if (inState == CRITIC_RATING){
            while (currVal >= 0){
                for (String[] movie : inMovie){
                    if (Integer.parseInt(movie[CRITIC_RATING]) == currVal){
                        writeOrder.add(movie);
                    }
                }
                currVal--;
            }
        }
        else if (inState == GENRE){
            
        }
        else{
            System.out.println("This is the part where you cri evertim");
        }
        // Reverse order if necessary
        if (!isDesc){
            Collections.reverse(writeOrder);
        }
        // The problem asks for the output genres to be in lowercase
        for (String[] movie : writeOrder){
            movie[GENRE] = movie[GENRE].toLowerCase();
        }
        // Format and write accordingly
        String currStr;
        if (inState == AUDIENCE_RATING){
            currStr = writeOrder.get(0)[AUDIENCE_RATING];
            bw.write(currStr + ":");
            bw.newLine();
            for (int i = 0; i < writeOrder.size(); i++){
                if (!writeOrder.get(i)[AUDIENCE_RATING].equals(currStr)){
                    currStr = writeOrder.get(i)[AUDIENCE_RATING];
                    bw.write(currStr + ":");
                    bw.newLine();
                }
                bw.write("  Title: " + writeOrder.get(i)[TITLE]);
                bw.newLine();
                bw.write("  Genre(s): " + writeOrder.get(i)[GENRE]);
                bw.newLine();
                bw.write("  Audience Rating: " + writeOrder.get(i)[AUDIENCE_RATING]);
                bw.newLine();
                bw.write("  Critic Rating: " + writeOrder.get(i)[CRITIC_RATING]);
                bw.newLine();
                if ((i < writeOrder.size() - 1) && (writeOrder.get(i)[AUDIENCE_RATING].equals(writeOrder.get(i + 1)[AUDIENCE_RATING]))){
                    bw.write("  -");
                    bw.newLine();
                }
            }

        }
        else if (inState == CRITIC_RATING){
            currVal = 5;
            boolean hasVal = false;
            while (currVal >= 0){
                for (String[] movie : writeOrder){
                    if (Integer.parseInt(movie[CRITIC_RATING]) == currVal){
                        currStr = movie[CRITIC_RATING];
                        bw.write(currStr + ":");
                        bw.newLine();
                        hasVal = true;
                        break;
                    }
                }
                if (!hasVal){
                    currVal--;
                }
                else{
                    for (int i = 0; i < writeOrder.size(); i++){
                        if (Integer.parseInt(writeOrder.get(i)[CRITIC_RATING]) == currVal){
                            bw.write("  Title: " + writeOrder.get(i)[TITLE]);
                            bw.newLine();
                            bw.write("  Genre(s): " + writeOrder.get(i)[GENRE]);
                            bw.newLine();
                            bw.write("  Audience Rating: " + writeOrder.get(i)[AUDIENCE_RATING]);
                            bw.newLine();
                            bw.write("  Critic Rating: " + writeOrder.get(i)[CRITIC_RATING]);
                            bw.newLine();
                            if ((i < writeOrder.size() - 1) && (writeOrder.get(i)[CRITIC_RATING].equals(writeOrder.get(i + 1)[CRITIC_RATING]))){
                                bw.write("  -");
                                bw.newLine();
                            }
                        }
                    }
                    currVal--;
                }
            }
        }
        else if (inState == GENRE){
            
        }
        else{
            System.out.println("Well I guess your code break or something");
        }
        bw.close();
    }
}