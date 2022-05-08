import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A tree data structure used to store name of drinks
 */
public class Trie {

    /**
     * drink name contains 26 english letters, 10 digit numbers plus blank space.
     */
    Trie[] children;

    /**
     * Marks the end of a string as true.
     */
    boolean end;

    /**
     * The string of drink name.
     */
    String drink;

    public Trie(){
        children = new Trie[38];
        end = false;
    }
    public Trie[] getChildren(){
        return children;
    }
    public void setChildren(Trie[] children){
        this.children = children;
    }
    public boolean getEnd(){
        return end;
    }
    public void setEnd(boolean end){
        this.end = end;
    }
    public String getDrink(){
        return drink;
    }
    public void setDrink(String drink){
        this.drink = drink;
    }
    public int getIndex(char ch){
        int index = 0;
        if(Character.isLetter(ch)){
            index = ch - 'a';
        } else if(Character.isDigit(ch)){
            index = ch - '0' + 26;
        } else if(ch == ' '){
            index = 36;
        } else if (ch == '-'){
            index = 37;
        }
        return index;
    }
    public String parse(String word){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                sb.append(Character.toLowerCase(ch));
            } else if(ch == ' '){
                sb.append(ch);
            } else if (ch == '-'){
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    public void addWord(String word){
        if(word == null || word.length() == 0){
            return ;
        }
        word = parse(word);
        Trie p = this;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            int index = getIndex(ch);
            if(p.children[index] == null) {
                p.children[index] = new Trie();
            }
            p = p.children[index];
        }
        p.setEnd(true);
        p.setDrink(word);
    }

    public List<String> queryByPrefix(String prefix){
        List<String> ans = new ArrayList<>();
        if(prefix == null){
            return ans;
        }
        Trie p = this;
        for(int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);
            int index = getIndex(ch);
            if(p.children[index] == null){
                return ans;
            }
            p = p.children[index];
        }
        LinkedList<Trie> queue = new LinkedList<>();
        queue.offerLast(p);
        while(!queue.isEmpty()){
            p = queue.pollFirst();
            if(p.end){
                ans.add(p.getDrink());
            }
            for(int i = 0; i < 37; i++){
                if(p.children[i] != null){
                    queue.offerLast(p.children[i]);
                }
            }
        }
        return ans;
    }
}
