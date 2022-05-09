import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A tree data structure used to store name of drinks.
 */
public class Trie {

    /**
     * Drink name contains 26 english letters, 10 digit numbers plus blank space.
     */
    Trie[] child;

    /**
     * Mark the end of a string as true.
     */
    boolean end;

    /**
     * The string of drink name.
     */
    String drink;

    /**
     * Trie Constructor.
     */
    public Trie() {
        child = new Trie[38];
        end = false;
    }

    /**
     * Return a list of children.
     */
    public Trie[] getChildren() {
        return child;
    }

    /**
     * Set the list of children.
     */
    public void setChildren(Trie[] children) {
        this.child = children;
    }

    /**
     * Get end.
     */
    public boolean getEnd() {
        return end;
    }

    /**
     * Set end.
     */
    public void setEnd(boolean end) {
        this.end = end;
    }

    /**
     * Get drink.
     */
    public String getDrink() {
        return drink;
    }

    /**
     * Set drink.
     */
    public void setDrink(String drink) {
        this.drink = drink;
    }

    /**
     * Get index.
     */
    public int getIndex(char ch) {
        int index = 0;
        if (Character.isLetter(ch)) {
            index = ch - 'a';
        } else if(Character.isDigit(ch)) {
            index = ch - '0' + 26;
        } else if(ch == ' ') {
            index = 36;
        } else if (ch == '-') {
            index = 37;
        }
        return index;
    }

    /**
     * Add word.
     */
    public void addWord(String word) {
        if (word == null || word.length() == 0) {
            return ;
        }
        Trie p = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = getIndex(ch);
            if (p.child[index] == null) {
                p.child[index] = new Trie();
            }
            p = p.child[index];
        }
        p.setEnd(true);
        p.setDrink(word);
    }

    /**
     * Query by prefix.
     */
    public List<String> queryByPrefix(String prefix) {
        List<String> ans = new ArrayList<>();
        if (prefix == null) {
            return ans;
        }
        Trie p = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = getIndex(ch);
            if (p.child[index] == null) {
                return ans;
            }
            p = p.child[index];
        }
        LinkedList<Trie> queue = new LinkedList<>();
        queue.offerLast(p);
        while (!queue.isEmpty()) {
            p = queue.pollFirst();
            if (p.end) {
                ans.add(p.getDrink());
                if (ans.size() >= 5) {
                    return ans;
                }
            }
            for (int i = 0; i < 37; i++) {
                if (p.child[i] != null) {
                    queue.offerLast(p.child[i]);
                }
            }
        }
        return ans;
    }

}
