import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class Text {
	private ArrayList<String> text = new ArrayList<String>();
	private int textCount;
	private Map<String, Integer> textMap = new TreeMap<String, Integer>();;
	private Set<String> textSet = new HashSet<String>();
	
	//Creates Text object from scanner by reading in the text into an ArrayList
	public Text(Scanner s){
		while(s.hasNextLine()){
			String line = s.nextLine();
			Scanner linescanner = new Scanner(line);
			while(linescanner.hasNext()){
				String token = linescanner.next();
				text.add(token);
				textCount++;
			}
			linescanner.close();
		}
		s.close();
	}
	
	public boolean contains(String s){
		boolean t = false;
		for(Entry<String,Integer> entry : textMap.entrySet()){
			if(entry.getKey().equals(s)) t = true;
		}
		return t;
	}
	public void compareText(Text t2, PrintStream p){
		DecimalFormat df = new DecimalFormat("##.00");
		double totalOverlap = 0.0;
		this.removeCommon();
		t2.removeCommon();
		this.SetMap();
		t2.SetMap();
		p.println("Overlapping word\tFrequency");
		for(Entry<String,Integer> entry : textMap.entrySet()){
			if(t2.contains(entry.getKey())){
				int overlap = Math.min(t2.textMap.get(entry.getKey()), entry.getValue());
				if(entry.getKey().length() >= 8){
					p.println(entry.getKey() + "\t\t" + overlap);
				} else{
					p.println(entry.getKey() + "\t\t\t" + overlap);
				}
				totalOverlap += overlap;
			}
		}
		p.println("Total words in text 1: " + textCount);
		p.println("Total words in text 2: " + t2.textCount);
		p.println("Total unique words in text 1: " + getUCount());
		p.println("Total unique words in text 2: " + t2.getUCount());
		p.println("Total overlapping words: " + totalOverlap);
		p.println("Percentage of overlapping words from text 1: " + df.format(totalOverlap*100/textCount) + "%");
		p.println("Percentage of overlapping words from text 2: " + df.format(totalOverlap*100/t2.textCount) + "%");
	}
	
	//removes all instances of the parameter within the text
	public void remove(String s){
		Iterator<String> itr = text.iterator();
		while(itr.hasNext()){
			String temp = itr.next();
			if(temp.equals(s)){
				itr.remove();
				textCount--;
			}
		}
	}
	
	//remove articles and other common words used in English
	public void removeCommon(){
		String[] common = {"the", "be", "to", "of", "and", "a", "in",
				"that", "have", "I", "it", "for", "he", "she", "his",
				"her", "you", "as"};
		for(String s : common){
			remove(s);
		}
	}
	
	//Eliminates words based on the filter parameter
	public void Trim(Filter f){
		for(int i=0;i<text.size();i++){
			String temp = Trim(text.get(i), f);
			if(temp == null){
				text.remove(i);
				i--;
			} else{
				text.set(i, temp);
			}
		}
		textCount = text.size();
	}
	
	//Overloading Trim method with additional String parameter
	public String Trim(String word, Filter f){
		boolean frontSense = false;
		boolean backSense = false;
		if(word.length() == 0) return null;
		for(int i=0;i<f.filter.length;i++){
			if(word.substring(0,1).equals(f.filter[i])){
				frontSense = true;
			}
			if(word.substring(word.length()-1,word.length()).equals(f.filter[i])){
				backSense = true;
			}
		}
		if(frontSense && backSense) return word;
		else if(!frontSense) return Trim(word.substring(1,word.length()), f);
		else return Trim(word.substring(0,word.length()-1),f);
	}
	
	//Creates a HashSet and a TreeMap that is sorted by decreasing value
	public void SetMap(){
		Iterator<String> itr = text.iterator();
		while(itr.hasNext()){
			String word = itr.next();
			textSet.add(word);
			if(!textMap.containsKey(word)){
				textMap.put(word, 1);
			} else{
				textMap.put(word, textMap.get(word)+1);
			}
		}
		textMap = sortByValues(textMap);
	}
	
	//Generic method that modifies a TreeMap so that it is sorted by decreasing value
	public <K extends Comparable<K>, V extends Comparable<V>> Map<K, V>
	sortByValues(final Map<K, V> map){
	Comparator<K> valueComparator = new Comparator<K>(){
		public int compare(K k1, K k2){
			int compare = -map.get(k1).compareTo(map.get(k2));
			if(compare == 0) return k1.compareTo(k2);
			else return compare;
		}
	};
	Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
	sortedByValues.putAll(map);
	return sortedByValues;
	}
	
	//Prints words and corresponding frequencies from TreeMap
	public void printToFile(PrintStream p){
		Set<Entry<String, Integer>> entrySet = textMap.entrySet();
		Iterator<Entry<String, Integer>> itr = entrySet.iterator();
		p.println("Word\t\t\tFrequency");
		while(itr.hasNext()){
			Entry<String, Integer> e = itr.next();
			if(e.getKey().length() >= 8){
				p.println(e.getKey() + "\t\t" + e.getValue());
			} else{
				p.println(e.getKey() + "\t\t\t" + e.getValue());
			}
		}
	}
	
	//Returns total word count
	public int getCount(){
		return textCount;
	}
	
	//Returns total number of unique words
	public int getUCount(){
		return textSet.size();
	}
	
	public void print(){
		for(String s : text) System.out.println(s);
	}
}
