import java.util.*;
import java.io.*;

//NOTE: In order for this test to work, you have to modify the sources for the text files!!
public class test {
	public static void main(String[] args) 
	throws FileNotFoundException{
		//Instantiate Scanner and Printstream
		Scanner textscanner = new Scanner(new File("C:\\Users\\Julian\\Desktop\\Coding\\Java\\Project 1 Final\\Project1 v2\\Liszt.txt"));
		PrintStream p = new PrintStream(new File("C:\\Users\\Julian\\Desktop\\Coding\\Java\\Project 1 Final\\Project1 v2\\LisztAnalysis.txt"));
		Text Liszt = new Text(textscanner);
		Filter f = new alphabetFilter();
		Liszt.Trim(f);
		Liszt.SetMap();
		Liszt.printToFile(p);
		
		textscanner = new Scanner(new File("C:\\Users\\Julian\\Desktop\\Coding\\Java\\Project 1 Final\\Project1 v2\\Chopin.txt"));
		Text A = new Text(textscanner);
		textscanner = new Scanner(new File("C:\\Users\\Julian\\Desktop\\Coding\\Java\\Project 1 Final\\Project1 v2\\Tchaikovsky.txt"));
		Text B = new Text(textscanner);
		p = new PrintStream(new File("C:\\Users\\Julian\\Desktop\\Coding\\Java\\Project 1 Final\\Project1 v2\\Comparison.txt"));
		A.compareText(B, p);		
	}
}
