/* Goal: Make a word frequency count of the words used in the speech that is posted
 * on the professor's beachboard (Obama2014SoTUA.txt). List ONLY the 10 most frequently
 * used words with the number of occurrences of each. All punctuation marks should be
 * ignored and treated as white space when deciding what is and isn't a word. For example,
 * if your program encounters ,and, it should recognize the word and and ignore the comma before it.
 * The word it's will actually be counted as two words, it and s.
 *
 *
 */

import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class speechcount1 {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner file = new Scanner(new File("")).useDelimiter("[^a-zA-Z]+");
		   HashMap<String, Integer> map = new HashMap<>();

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
		    for(int i = 0; i < 10; i++){
		        System.out.println(entries.get(entries.size() - i - 1).getKey() + "(" + (map.get(entries.get(entries.size() - i - 1).getKey()) + 1) + ")");
		    }
		    long endTime = System.nanoTime();
		    System.out.println("Runtime: "+(endTime - startTime) + " nano-seconds.");
	}
}
