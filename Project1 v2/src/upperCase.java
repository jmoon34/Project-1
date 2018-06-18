public class upperCase extends alphabetFilter{
	public upperCase(){
		Filter f = new alphabetFilter();
		filter = new String[26];
		for(int i=26;i<52;i++){
			filter[i-26] = f.filter[i];
		}
	}
}
