/* Goal: Make a word frequency count of the words used in the speech that is posted on the
 * professor's beachboard (Obama2014SoTUA.txt). List the 40 most frequently used words with
 * the number of occurrences of each. Your program must use a splay tree data structure to
 * store the words. Words are defined the same way as above in Project 1.
 *
 *
 */
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
import java.util.ArrayList;
public class speechcount3<Key extends Comparable<Key>, Value> {

    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        public Node(Key key, Value val) {
            this.key   = key;
            this.val = val;
        }
    }

    public boolean contains(Key key) {
        return (get(key) != null);
    }

    public Value get(Key key) {
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return root.val;
        else          return null;
    }

    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val);
            return;
        }
        int compare = key.compareTo(root.key);

        if (compare < 0) {
            Node n = new Node(key, val);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }

        else if (compare > 0) {
            Node n = new Node(key, val);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }

        else if (compare == 0) {
            root.val = val;
        }

    }

    public void remove(Key key) {
        if (root == null) return;
        int compare = key.compareTo(root.key);

        if (compare == 0) {
            if (root.left == null) {
                root = root.right;
            }
            else {
                Node x = root.right;
                root = root.left;

                root.right = x;
            }
        }

    }

    public int height() {
    	return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }


    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return (1 + size(x.left) + size(x.right));
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		TreeMap<String, Integer> map = new TreeMap<>();
		int counter = 0;
		Scanner file = new Scanner(new File("/Users/Kaymuda/Documents/workspace/328/src/Obama2014SoTUA.txt")).useDelimiter("[^a-zA-Z]+");
		   LAB3<Integer, String> st = new LAB3<Integer, String>();
		   while (file.hasNext()){
		        String word = file.next().toLowerCase();
		        if (map.containsKey(word)) {
		            map.put(word, map.get(word) + 1);
		        } else {
		            map.put(word, 0);
		        }
		    }
		   ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

		    for (int i = 0; i < entries.size(); i++) {
	    		st.put(map.get(entries.get(entries.size() - i - 1).getKey()), entries.get(entries.size() - i - 1).getKey());
	    }
		    long startTime = System.nanoTime();
		    	for (int i = entries.size(); i > 1;) {
		    		if (st.get(i) != null) {
		    			System.out.println(st.get(i) + "(" + (i + 1) + ")");
		    			counter++;
		    			if(counter == 40) {
		    				break;
		    			}
		    			i--;
		    		} else {
		    			i--;
		    		}
		    }

		    long endTime = System.nanoTime();
		    System.out.println("Runtime: "+(endTime - startTime) + " ns");
		    System.out.println("Map size = " + map.size());
		    System.out.println("Entries size = " + entries.size());
		    System.out.println("Size = " + st.size());
		    System.out.println("Height = " + st.height());
	}
}
