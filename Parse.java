import java.util.*;
import java.io.*;
import java.lang.*;
class ParserTest {
	public static void main(String[] args) {
		String tester = "Roses are red, Violets are blue, I have five fingers, The middle is for you.";
		//splits string by characters
		String[] parsed = tester.split(" ");
		for(String a: parsed) {
			System.out.println(a);
		}
		//splits each character
		for (char ch : tester.toCharArray()){
		    System.out.println(ch);
		}
		//replaced characters and strings
		System.out.println(tester.replace('a', '@'));
		System.out.println(tester.replace("are", "aren't"));
		//takes out whitespaces before and after string
		System.out.println(" roses ".trim());
		//returns true or false if it contains a particular string
		System.out.println(tester.contains("are"));
		//returns true or false if it ends/starts in a particular string
		System.out.println(tester.endsWith("."));
		System.out.println(tester.startsWith("."));
		//find index of char/string
		System.out.println(tester.indexOf("a"));
		System.out.println(tester.indexOf('a', 7));
		System.out.println(tester.lastIndexOf("o"));
		//sort array alphabetically 
		String[] parsedcopy = parsed.clone();
		for(int i = 0; i < parsedcopy.length;i++) {
			parsedcopy[i] = parsedcopy[i].toLowerCase();
		}
		Arrays.sort(parsedcopy);
		for(String s:parsedcopy) {
			System.out.print(s + " ");
		}
		System.out.println("");
		//sort arraylist alphabetically
		ArrayList<String> parsedlist = new ArrayList<String>(Arrays.asList(parsed));
		Collections.sort(parsedlist);
		//wtf
		while(parsedlist.contains(",")) {
			parsedlist.remove(",");
		}
		for(String s: parsedlist) {
			System.out.print(s + " ");
		}
		System.out.println("");
		//sort arraylist incrementally
		ArrayList<Integer> intarr = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			intarr.add((int)(Math.random()*100));
		}
		Collections.sort(intarr);
		for(int i: intarr) {
			System.out.print(i + " ");
		}
		//reverse
		for(int i = intarr.size()-1 ; i >=0; i--) {
			System.out.print(intarr.get(i) + " ");
		}
		
	}
}