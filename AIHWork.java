import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class AIHWork {

	public static void main(String[] args) {
		
		int count = 0;
		TreeSet<String> dict = new TreeSet<String>();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		
		for (int i = 1; i <= 363; i++ ) {
			String num = i + "";
			if (i < 10) {
				num = "00" + num;
			} else if (i < 100) {
				num = "0" + num;
			}
			try {
				Scanner in = new Scanner(new File("WARC201709/" + num + ".txt"));
				while (in.hasNext()) {
					String next = in.next();
					dict.add(next);
					if (map.containsKey(next)) {
						map.put(next, map.get(next) + 1);
					} else {
						map.put(next, 1);
					}
					count++;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Count: " + count);
		System.out.println("Unique: " + dict.size());
		
		Map sortedMap = TreeMapDemo.sortByValues(map);
		 
	    // Get a set of the entries on the sorted map
	    Set set = sortedMap.entrySet();
	 
	    // Get an iterator
	    Iterator i = set.iterator();
	 
	    // Display elements
	    int j = 0;
	    while(i.hasNext() && j < 20) {
	      Map.Entry me = (Map.Entry)i.next();
	      System.out.print(me.getKey() + ": ");
	      System.out.println(me.getValue());
	      j++;
	    }
	}
	
	

}

class TreeMapDemo {
	  //Method for sorting the TreeMap based on values
	  public static <K, V extends Comparable<V>> Map<K, V> 
	    sortByValues(final Map<K, V> map) {
	    Comparator<K> valueComparator = 
	             new Comparator<K>() {
	      public int compare(K k1, K k2) {
	        int compare = 
	              map.get(k1).compareTo(map.get(k2));
	        if (compare == 0) 
	          return 1;
	        else 
	          return compare;
	      }
	    };
	 
	    Map<K, V> sortedByValues = 
	      new TreeMap<K, V>(valueComparator);
	    sortedByValues.putAll(map);
	    return sortedByValues;
	  }
	  public static void main(String args[]) {
	 
	    TreeMap<String, String> treemap = new TreeMap<String, String>();

	    // Put elements to the map
	    treemap.put("Key1", "Jack");
	    treemap.put("Key2", "Rick");
	    treemap.put("Key3", "Kate");
	    treemap.put("Key4", "Tom");
	    treemap.put("Key5", "Steve");
	 
	    // Calling the method sortByvalues
	    Map sortedMap = sortByValues(treemap);
	 
	    // Get a set of the entries on the sorted map
	    Set set = sortedMap.entrySet();
	 
	    // Get an iterator
	    Iterator i = set.iterator();
	 
	    // Display elements
	    while(i.hasNext()) {
	      Map.Entry me = (Map.Entry)i.next();
	      System.out.print(me.getKey() + ": ");
	      System.out.println(me.getValue());
	    }
	  }
	}


