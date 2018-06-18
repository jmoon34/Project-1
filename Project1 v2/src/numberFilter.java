public class numberFilter extends Filter{
	public numberFilter(){
		filter = new String[10];
		for(int i=0;i<10;i++){
			filter[i] = Integer.toString(i);
		}
	}
}
