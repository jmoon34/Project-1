import java.util.Arrays;
public class lowerCase extends Filter{
	public lowerCase(){
		Filter f = new alphabetFilter();
		filter = Arrays.copyOf(f.filter, 26);
	}

}
