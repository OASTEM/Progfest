package progfest;

/**
 *
 * @author Peter
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SortingTheLazyWay{

    public static void main(String[] args){
        /**
         * There exists a class named Collections which has a very useful
         * .sort() method It will sort things alphabetically and numbers
         * numerically. However, it only applies to ArrayLists. The first
         * example will be shown using an ArrayList and the second example will
         * convert a normal array into an ArrayList so Collections.sort() can be
         * used on it.
         */
        exampleOne();
        exampleTwo();
        exampleThree();
    }

    private static void exampleOne(){
        ArrayList<String> names = new ArrayList<>();
        names.add("Kobe");
        names.add("Celine");
        names.add("Dennis");
        names.add("Michael");
        names.add("Spring");
        names.add("Moses");
        names.add("Joy");
        names.add("Joon");
        names.add("Peter");

        System.out.println("Unsorted List\n");
        for (String name : names){
            System.out.println(name);
        }
        System.out.println();

        // Here is your magic
        Collections.sort(names);

        System.out.println("Sorted List\n");
        for (String name : names){
            System.out.println(name);
        }
        System.out.println();

        // This is pretty neat!
        Collections.reverse(names);
        System.out.println("Reversed List\n");
        for (String name : names){
            System.out.println(name);
        }
        System.out.println();
    }

    private static void exampleTwo(){
        String[] names = new String[9];
        names[0] = "Kobe";
        names[1] = "Celine";
        names[2] = "Dennis";
        names[3] = "Michael";
        names[4] = "Spring";
        names[5] = "Moses";
        names[6] = "Joy";
        names[7] = "Joon";
        names[8] = "Peter";

        System.out.println("Unsorted List\n");
        for (String name : names){
            System.out.println(name);
        }
        System.out.println();

        // Look at the syntax over here
        ArrayList<String> listFromArray = new ArrayList<>(Arrays.asList(names));
        
        Collections.sort(listFromArray);

        System.out.println("Sorted List\n");
        for (String name : listFromArray){
            System.out.println(name);
        }
        System.out.println();
    }

    private static void exampleThree(){
        System.out.println("compareTo() examples\n");
        /**
         * On the topic of sorting, knowing what compareTo() does may come in handy
         * Though most objects require a compareTo() method to be written to work,
         * there already exists code for Strings.
         * 
         * When you call a.compareTo(b), the two are compared "lexicographically" which takes
         * the unicode value of the Strings to compare and returns the difference. b is 
         * 2 before d so "b".compareTo("d") returns -2
         * 
         * Take note in this set of numerical values, capital and lowercase letters are
         * listed independently of one another. If you want to compensate for the gap between
         * different cases, use compareToIgnoreCase()
         */
        
        // potato is 13 in front of carrot (prints 13)
        System.out.println("potato".compareTo("carrot"));
        // potato is 3 behind squash (prints -3)
        System.out.println("potato".compareTo("squash"));
        // potato is 32 in front of Potato (prints 32)
        System.out.println("potato".compareTo("Potato"));
        // case is ignored so the difference is 0 (prints 0)
        System.out.println("potato".compareToIgnoreCase("Potato"));
    }
}
