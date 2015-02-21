import java.io.*;
import java.util.*;

class Soundex {
	public static void main(String[] args) throws Exception {
	ArrayList<String> fileIn = new ArrayList<String>();
	BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
	String line;
	while ((line = br.readLine()) != null){
		fileIn.add(line);
	}
	String[] lol = fileIn.get(0).split(" ");
	fileIn = new ArrayList<String>(Arrays.asList(lol));
	Algorithm al = new Algorithm();
	ArrayList<String> finalans = new ArrayList<String>();
	String reversee = "";
	String replace = "";
	String groupiee = "";
	String newsies = "";
	String nodash = "";
	String addh = "";
	for(int i = 0; i < fileIn.size(); i++) {
		addh = fileIn.get(i);
		if(fileIn.get(i).endsWith("a") || fileIn.get(i).endsWith("e") || fileIn.get(i).endsWith("i") || fileIn.get(i).endsWith("o") || fileIn.get(i).endsWith("u") || fileIn.get(i).endsWith("w") || fileIn.get(i).endsWith("h") || fileIn.get(i).endsWith("y")) {
			addh += "h";
		}
		reversee =al.reverse(addh);
		replace = al.replaceDash(reversee);
		groupiee = al.groups(replace);
		newsies = al.replaceGroup(groupiee);
		nodash = newsies.replace("-", "");
		//System.out.println(fileIn.get(i));
		//System.out.println(reversee);
		//System.out.println(replace);
		//System.out.println(groupiee);
		//System.out.println(newsies);
		//System.out.println(nodash);
		if(nodash.length() < 3) {
			nodash = nodash + "0";
		}
		//System.out.println("Final answer: " + nodash + "\n");
		finalans.add(nodash);
	}
	String start = "";
	ArrayList<String> omg = new ArrayList<String>();
	for(int i = 0; i < finalans.size(); i++) {
		start = finalans.get(i).substring(0, 2);
		omg.add(fileIn.get(i));
		for(int j = i+1; j < finalans.size(); j++) {
			if(finalans.get(j).startsWith(start)) {
				omg.add(fileIn.get(j));
				finalans.remove(j);
				fileIn.remove(j);
			}
		}
		Collections.sort(omg);
		System.out.print(start + ":" + omg.get(0));
		bw.write(start + ":" + omg.get(0));
		for(int h = 1; h < omg.size(); h++) {
			System.out.print("," + omg.get(h));
			bw.write("," + omg.get(h));
		}
		System.out.println("");
		bw.newLine();
		omg.clear();
	}
	
    bw.close();  
	}
}

class Algorithm {
	
	public String reverse (String str) {
		String ans = "";
		for(int i = str.length()-1; i >=0; i--) {
			ans+= str.charAt(i);
		}
		return ans;
	}
	
	public String replaceDash (String str) {
		char[] watchout = {'a', 'e', 'i', 'o', 'u', 'h', 'w', 'y'};
		boolean check = false;
		for(int i = 1; i < str.length(); i++) {
			for(int j = 0; j < watchout.length; j++) {
				if(str.charAt(i) == watchout[j]) {
					check = true;
				}
			}
			if(!check) {
				str = str.substring(0, i) + '-' + str.substring(i+1, str.length());
			}
			check = false;
		}
		String ans = "";
		for(int i = 0; i < str.length()-1; i++) {
			if(!(str.charAt(i) == str.charAt(i+1) && str.charAt(i)=='-')) {
				ans += str.charAt(i);
			}
		}
		return ans;
	}
	
	public String groups (String str) {
		String[] groupies = str.split("-");
		String answer = "";
		ArrayList<String> ans = new ArrayList<String>(Arrays.asList(groupies));
		String temp = ans.get(0).substring(0,1);
		ans.set(0, ans.get(0).substring(1, ans.get(0).length()));
		for(String s: ans) {
			if(s.length() > 1) {
				answer+= s.substring(0, 2);
			} else {
				answer+= s;
			}
			answer+= "-";
		}
		answer = temp + answer;
		answer = answer.substring(0, answer.length()-1);
		return answer;
	}
	
	public String replaceGroup (String str) {
		String[] one = {"a", "aa", "ea", "ha", "ia", "ya", "ye", "yh", "ei", "yu"};
		String[] two = {"e", "ae", "ee", "he", "ie", "hi", "y"};
		String[] three = {"i", "ai", "hi", "ii", "oi", "ui", "yi"};
		String[] four = {"oa", "ua", "wa", "oe", "o", "ao", "eo", "ho", "io", "wo", "yo", "iu"};
		String[] five = {"ue", "we", "wi", "oo", "uo", "u", "au", "eu", "hu", "ou", "uu"};
		String[] groupies = str.split("-");
		String answer = "";
		boolean check = false;
		ArrayList<String> ans = new ArrayList<String>(Arrays.asList(groupies));
		String temp = ans.get(0).substring(0,1);
		ans.set(0, ans.get(0).substring(1, ans.get(0).length()));
		for(String s: ans) {
			for(int i = 0; i < one.length; i++) {
				if(s.equalsIgnoreCase(one[i])) {
					answer += "1";
					check = true;
				}
			}
			for(int i = 0; i < two.length; i++) {
				if(s.equalsIgnoreCase(two[i])) {
					answer += "2";
					check = true;
				}
			}
			for(int i = 0; i < three.length; i++) {
				if(s.equalsIgnoreCase(three[i])) {
					answer += "3";
					check = true;
				}
			}
			for(int i = 0; i < four.length; i++) {
				if(s.equalsIgnoreCase(four[i])) {
					answer += "4";
					check = true;
				}
			}
			for(int i = 0; i < five.length; i++) {
				if(s.equalsIgnoreCase(five[i])) {
					answer += "5";
					check = true;
				}
			}
			if(!check) {
				answer+="6";
			}
			answer+="-";
			check = false;
		}
		answer = temp + answer;
		answer = answer.substring(0, answer.length()-1);
		return answer;
	}
}