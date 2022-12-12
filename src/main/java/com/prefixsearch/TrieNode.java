package com.prefixsearch;

import java.util.ArrayList;
import java.util.List;

/*
 * This class implements a trie data structure for usage of prefix string
 * searching of a word dictionary.
 */
public class TrieNode {

    private static final int ALPHABET_SIZE = 26;
    private static final int BUFFER_SIZE = 1024;
    private TrieNode[] children;
    private TrieNode parent; // parent allows to backtrack nodes to form the word
    private char character; // while looping through parent get the char to form the word
    private boolean isWord;
    private boolean leaf;

    public TrieNode() {
        this.setChildren(new TrieNode[ALPHABET_SIZE]);
        this.setWord(false);
        // no character here as such leaf = true
        // this is used for checking while looping through the parent
        // to form the word, any leaf set as true is the root and is
        // the end of the looping point, there is no character in
        // the root node
        this.setLeaf(true);
    }

    public void addWord(String word) {
        int limit = word.length();
        TrieNode t = this;
        int letter, index;

        word = word.toLowerCase();
        for (int i = 0; i < limit; i++) {
            letter = word.charAt(i);
            index = letter - 'a';

            if (!isNotLetter(letter))
                continue;

            if (t.children[index] == null) {
                t.children[index] = new TrieNode();
                t.children[index].character = (char) letter;
            }
            t.children[index].setLeaf(false); // character is here as such leaf = false
            t.children[index].parent = t;
            t = t.children[index];
        }
        t.setWord(true);
    }

    private boolean isNotLetter(int letter) {
        return Character.isAlphabetic(letter);
    }

    public List<String> FindAndRetrieveWords() {
        List<String> words = new ArrayList<String>();
        TrieNode t = this;

        if (t.isWord())
            words.add(t.getWord());

        if (!t.isLeaf()) {
            for (int i = 0; i < t.children.length; i++) {
                if (t.children[i] != null) {
                    words.addAll(children[i].FindAndRetrieveWords());
                }
            }
        }

        return words;
    }

    private String getWord() {
        TrieNode t = this;
        char[] buffer = new char[BUFFER_SIZE];
        StringBuilder result = new StringBuilder();
        String value;

        int i = 0;
        while (!t.isLeaf()) {
            buffer[i] = t.character;
            t = t.parent;
            i++;
        }

        result.append(buffer);
        value = result.reverse().toString().trim();

        return value;
    }

    public boolean isWord(String s) {
        TrieNode t = this;
        int limit = s.length();

        for (int i = 0; i < limit; i++) {
            int letter = s.charAt(i);
            int index = letter - 'a';

            if (t.children[index] == null)
                return false;
            t = t.children[index];
        }

        return t.isWord();
    }

    public boolean isEmpty(TrieNode t) {
        if (t.children == null) {
            return true;
        }
        return false;
    }

    public TrieNode getNode(int index) {
        TrieNode t = this;

        if (t.children[index] != null) {
            return t.children[index];
        } else {
            return null;
        }
    }

    /*
     * When this method is called, the "this" Node is pointing to the last character
     * of the prefix string. From this Node, look at all its children nodes (array)
     * for branches of other patterns that complete the word with the prefix and
     * return a list of those words.
     */
    public List<String> getWords() {
        TrieNode t = this;
        List<String> words = new ArrayList<String>();

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (t.children[i] != null) {
                words.addAll(t.getChildren()[i].FindAndRetrieveWords());
            }
        }

        return words;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public void setChildren(TrieNode[] children) {
        this.children = children;
    }

    public TrieNode getParent() {
        return parent;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean isWord) {
        this.isWord = isWord;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public void addWords(String[] words) {
        for (String s : words) {
            addWord(s);
        }
    }

}
