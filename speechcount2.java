/* Goal: Make a word frequency count of the words used in the speech that is posted on
 * the professor's beachboard (Obama2014SoTUA.txt). List the 40 most frequently used
 * words with the number of occurrences of each. Your program must use a balanced tree
 * data structure to store the words. Words are defined the same way as above in Project 1.
 *
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class speechcount2.java {
	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner file = new Scanner(new File("")).useDelimiter("[^a-zA-Z]+");
		   TreeMap<String, Integer> map = new TreeMap<>();

		   while (file.hasNext()){
		        String key = file.next().toLowerCase();
		        if (map.containsKey(key)) {
		            map.put(key, map.get(key) + 1);
		        } else {
		            map.put(key, 0);
		        }
		    }

		    ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
		    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

		        @Override
		        public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
		            return a.getValue().compareTo(b.getValue());
		        }
		    });
		    long startTime = System.nanoTime();
		    for(int i = 0; i < 40; i++){
		        System.out.println(entries.get(entries.size() - i - 1).getKey() + "(" + (map.get(entries.get(entries.size() - i - 1).getKey()) + 1) + ")");
		    }
		    long endTime = System.nanoTime();
		    System.out.println("Runtime: "+(endTime - startTime) + " nano-seconds");
		    System.out.println("map size = " + map.size());
		    System.out.println("entires size = " + entries.size());
	}
}
