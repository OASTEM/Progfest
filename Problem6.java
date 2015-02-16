/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfest;

/**
 *
 * @author Peter
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.text.DecimalFormat;

class Problem6 {

    public static void main(String[] args) throws Exception {
        /**
         * Reading in the file
         */
        BufferedReader br = new BufferedReader(new FileReader("P6Input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("P6Output.txt"));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        // Each line is "defined" as a person which is just an array of length 3
        ArrayList<String[]> people = new ArrayList<>();
        String[] temp = new String[3];
        // Start at int = 1 becase we don't need to know how many people there are thanks
        // to the power of ArrayList<>
        for (int i = 1; i < lines.size(); i++) {
            temp = lines.get(i).split(",");
            people.add(temp);
        }

        // Focuses on calculating prices with the last item in the person array
        ArrayList<String> prices = new ArrayList<>();
        String[] tempStringPrices;
        // Could be an ArrayList<Double> but oh well
        double[] tempDoublePrices = new double[100];
        int itemsPurchased;
        double subtotal, discountedTotal;
        int calculationsMade = 0;
        for (String[] person : people) {
            subtotal = 0;
            discountedTotal = 0;
            /**
             * Gets the total before discount
             */
            tempStringPrices = person[2].split(" ");
            // Converts the string prices to double prices
            try {
                for (int i = 0; i < tempStringPrices.length; i++) {
                    tempDoublePrices[i] = Double.parseDouble(tempStringPrices[i]);
                }
            } 
            // If something goes wrong, it'll print out what was attempted to be parsed, but failed
            catch (NumberFormatException e) {
                for (int i = 0; i < tempStringPrices.length; i++) {
                    System.out.println(tempStringPrices[i]);
                }
            }
            // Adds everything together
            for (double item : tempDoublePrices) {
                subtotal += item;
            }
            /**
             * Gets the total after discount
             */
            // Starting point of discounted
            discountedTotal = subtotal;
            // Sees how many things the person purchased
            itemsPurchased = person[2].split(" ").length;
            // If the person is a programmer, he gets discounts
            if (person[0].equalsIgnoreCase("yes")) {
                // Because no one likes COBOL
                if ((!person[1].equals("COBOL"))) {
                    if (itemsPurchased >= 2) {
                        discountedTotal -= discountedTotal * .15;
                    }
                    // The discount stacks on the previous discount
                    if (itemsPurchased >= 5) {
                        discountedTotal -= discountedTotal * .10;
                    }
                    // Just not being COBOL in general gets you more discounts
                    discountedTotal -= discountedTotal * .10;
                }
            }
            /**
             * Writes stuff to file
             */
            // Rounds the double to only two decimal places, as seen by the #.## template
            DecimalFormat df = new DecimalFormat("#.##");
            bw.write(df.format(subtotal));
            bw.write(",");
            bw.write(df.format(discountedTotal));
            // Resets everything in the tempDoublePrices array to 0 so it can be reused
            for (int i = 0; i < tempDoublePrices.length; i++){
                tempDoublePrices[i] = 0;
            }
            // Without this code, there would be an extra new line at the end of the last item
            calculationsMade++;
            if (calculationsMade < people.size())
                bw.newLine();
        }
        // Dont forget!
        bw.close();
    }
}
