public class alphabetFilter extends Filter{
	public alphabetFilter(){
		int index = 0;
		filter = new String[52];
		for(char i='a';i<= 'z';i++){
			filter[index] = Character.toString(i);
			index++;
		}
		for(char i='A';i<='Z';i++){
			filter[index] = Character.toString(i);
			index++;
		}
	}
	public void print(){
		super.print();
	}
}
