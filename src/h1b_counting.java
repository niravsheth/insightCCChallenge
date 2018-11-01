import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class solution {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br  = new BufferedReader(new FileReader("./input/h1b_input.csv")); //input file
		BufferedWriter bw  = new BufferedWriter(new FileWriter("./output/top_10_occupations.txt"));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter("./output/top_10_states.txt"));
		
		Map<String,Integer> map = new TreeMap<>(); // to store the certified application according to occupation
		Map<String,Integer> statemap = new TreeMap<>();//to store the certified application according to states
		
		String current;
		int totalCertified = 0;
		while ((current = br.readLine()) != null) {
			String status   = current.split(";")[2];
			if(status.equals("CERTIFIED")) {				//checking condition for certified applications
				String soc_code = current.split(";")[14];
				//System.out.println(soc_code);
				if (map.containsKey(soc_code)) {
					int i = map.get(soc_code);
					map.put(soc_code, ++i);               //updates the existing map's for that occupations
				} 
				else {
					map.put(soc_code, 1);				// creates a new key for a particular occupation if not found
				}
				String state = current.split(";")[11];

				if (statemap.containsKey(state)) {
					statemap.put(state, statemap.get(state) + 1); //updates the existing map's key for that states
				} 
				else {
					statemap.put(state, 1);				// creates a new key for a particular state if not found
				}
				totalCertified++ ;
			}
		}
		SortedSet sortedMap = entriesSortedByValues(map); // sorts the map according to the values in descending order
		
		Iterator it = sortedMap.iterator();
		int i = 0;
		bw.write("TOP_OCCUPATIONS;NUMBER_CERTIFIED_APPLICATIONS;PERCENTAGE" + "\n");
		while (it.hasNext() && i<11) {
			Map.Entry pair = (Map.Entry) it.next();
			int value = (int)pair.getValue();
			float percent = (float)value/totalCertified * 100;
			String formatPercent = String.format("%.2f",percent);
			bw.write(pair.getKey() + ";" + pair.getValue()+";"+formatPercent+"%" +"\n"); // writes the sorted top 10 occupations key value and percentage
			it.remove();
			i++;
		}
		bw.close();
		
		SortedSet sortedStateMap = entriesSortedByValues(statemap);
		Iterator it2 = sortedStateMap.iterator();
		bw2.write("TOP_STATES;NUMBER_CERTIFIED_APPLICATIONS;PERCENTAGE" + "\n");
		int p = 0;
		while (it2.hasNext() && p<11) {
			Map.Entry pairState = (Map.Entry) it2.next();
			int value = (int)pairState.getValue();
			float percent = (float)value/totalCertified * 100;
			String formatPercent = String.format("%.2f",percent);
			bw2.write(pairState.getKey() + ";" + pairState.getValue()+";"+formatPercent+"%" +"\n");// writes the sorted top 10 states key value and percentage
			it2.remove();
			p++;
		}
		
		br.close();
		bw2.close();
	
	}
	static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    int res = e1.getValue().compareTo(e2.getValue());
                    if (res == 1)
                    		return -1;
                    else if (res == -1) 
                    		return 1;
                    else 
                    	return 0;
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}

