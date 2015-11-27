import java.io.IOException;

/*
 * Using Trie data structure for prefix search
 * 
 * implemented a traditional trie structure 
 * for the following test, add all words into a trie (dictionary) and specify 
 * a prefix string to search for all the words containing the
 * prefix. 
 * 
 */
public class TrieTest 
{

	public static void main(String[] args) throws IOException 
	{
		String outString;
		TriePreFix trie;
		byte[] input;
		
		input = new byte[80];
		System.out.print("Enter a set of words to define our word dictionary (separate by space only): ");
		System.in.read(input);
		outString = (new String(input, 0, input.length)).trim();
		trie = new TriePreFix(outString.split(" "));
		
		do {
		     input = new byte[80];
		     System.out.print("Enter a Prefix string to search dictionary (press enter to quick): ");
		     System.in.read(input);
		     outString = (new String(input, 0, input.length)).trim();
		     if (outString.length() > 0)
		     {
		    	 System.out.println(trie.getWords(outString).toString());
		     }
		 } while (outString.length() > 0);
		
		System.out.println("EXITED");
	}

}
