import java.util.*;
public class trimtest {
	public static void Trim(List<String> words, Filter f){
		for(int i=0;i<words.size();i++){
			String temp = Trim(words.get(i), f);
			if(temp == null){
				words.remove(i);
			} else{
				words.set(i, temp);
			}
			
		}
	}
	public static String Trim(String word, Filter f){
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
	public static void main(String[] args){
		Filter f = new alphabetFilter();
		f.print();
		ArrayList<String> a = new ArrayList<String>();
		a.add("julian2");
		a.add("]]abc;2");
		a.add("word");
		a.add("12]]]");
		System.out.println(a);
		
		Trim(a,f);
		System.out.println(a);
	}
}
