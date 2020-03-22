import java.io.IOException;

/*
 * Find a word after providing a list of words to search from. Word search is performed by providing
 * a set of prefix characters.
 *
 * This program implements a traditional trie data structure for prefix search.
 * 
 * author Frank Giordano
 */
public class FindWord {

    public static void main(String[] args) throws IOException {
        String words;
        String prefix;
        TriePreFix trie;
        byte[] input;

        input = new byte[80];
        System.out.println("Enter a set of words to define our word dictionary (separate by space only)");
        System.out.print("> ");
        try {
            System.in.read(input);
        } catch (IOException e) {
            System.out.print("Error reading given input. Error message = " + e.getMessage());
            System.exit(-1);
        }
        words = (new String(input, 0, input.length)).trim();
        if (words.isEmpty())
            System.exit(0);
        trie = new TriePreFix(words.split(" "));

        do {
            input = new byte[80];
            System.out.print("Enter a Prefix string to search dictionary (press enter to quick): ");
            System.in.read(input);
            prefix = (new String(input, 0, input.length)).trim();
            if (prefix.length() > 0) {
                System.out.println(trie.getWords(prefix).toString());
            }
        } while (prefix.length() > 0);

        System.exit(0);
    }

}
