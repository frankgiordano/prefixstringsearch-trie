package com.prefixsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Search for words from a word dictionary with prefix characters. This mimics a type ahead feature
 * you commonly see in UI applications and in word processors (when showing results for spell checks
 * errors).
 *
 * This program implements a traditional trie data structure for prefix search.
 *
 * author Frank Giordano
 */
public class SearchDictionary {

    private static TriePreFix dictionary;
    private static final String fileName = "words.txt";

    private static String readDictionary() {
        String line;
        StringBuilder words = new StringBuilder();

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(fileName));
            while ((line = bReader.readLine()) != null) {
                words.append(line);
                words.append(" ");
            }

        } catch (IOException e) {
            System.out.print("Error reading given input. Error message = " + e.getMessage());
            System.exit(-1);
        }

        return words.toString();
    }

    private static void buildDictionary() {
        dictionary = new TriePreFix(readDictionary().split(" "));
    }

    public static String search(String prefix) {
        if (dictionary == null) {
            buildDictionary();
        }
        return dictionary.getWords(prefix).toString();

    }

    public static void main(String[] args) throws IOException {
        String prefix;
        byte[] input;

        do {
            input = new byte[80];
            System.out.println("Enter a Prefix string to search dictionary (press enter to quick)");
            System.out.print("> ");
            System.in.read(input);
            prefix = (new String(input, 0, input.length)).trim();
            if (prefix.length() > 0) {
                System.out.println(SearchDictionary.search(prefix));
            }
        } while (prefix.length() > 0);

        System.exit(0);
    }

}
