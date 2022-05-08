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
        children = new Trie[37];
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
        }
        return index;
    }
    public void addWord(String word){
        if(word == null || word.length() == 0){
            return ;
        }
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
    public static void main(String[] args){
        Trie root = new Trie();
        root.addWord("tr123");
        root.addWord("transform");
        root.addWord("tree");
        root.addWord("toy");
        root.addWord("ti");
        for(int i = 0; i < 37; i++){
            if(root.children[i] != null){
                System.out.println(i);
            }
        }
        Trie c1 = root.children[19];
        for(int i = 0; i < 37; i++){
            if(c1.children[i] != null){
                System.out.println(i);
            }
        }
    }
}
