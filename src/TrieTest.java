import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TrieTest {
    Trie root;
    @Before
    public void setUp(){
        root = new Trie();
    }
    @Test
    public void testAddWord(){
        root.addWord("tr123");
        root.addWord("transform");
        root.addWord("tree");
        root.addWord("toy");
        root.addWord("ti");
        assertFalse(root.getEnd());
        for(int i = 0; i < 37; i++){
            if(i == 19){
                assertNotNull(root.getChildren()[i]);
            } else {
                assertNull(root.getChildren()[i]);
            }
        }
        Trie child1 = root.child[19];
        assertFalse(child1.getEnd());
        for(int i = 0; i < 37; i++){
            if(i == 8 || i == 14 || i == 17){
                assertNotNull(child1.getChildren()[i]);
            } else {
                assertNull(child1.getChildren()[i]);
            }
        }
        Trie child11 = child1.getChildren()[8];
        assertTrue(child11.getEnd());
        assertEquals("ti", child11.getDrink());
    }
    @Test
    public void testQueryByPrefix(){
        root.addWord("child");
        root.addWord("chilly");
        root.addWord("children group");
        root.addWord("kind");
        root.addWord("church");
        root.addWord("cat");
        Set<String> set1 = new HashSet<>(root.queryByPrefix("ch"));
        assertEquals(4, set1.size());
        assertTrue(set1.contains("child"));
        assertTrue(set1.contains("church"));
        assertTrue(set1.contains("children group"));
        assertTrue(set1.contains("chilly"));
        Set<String> set2 = new HashSet<>(root.queryByPrefix("chi"));
        assertEquals(3, set2.size());
        assertTrue(set2.contains("child"));
        assertFalse(set2.contains("church"));
        assertTrue(set2.contains("children group"));
        assertTrue(set2.contains("chilly"));
        Set<String> set3 = new HashSet<>(root.queryByPrefix("child"));
        assertEquals(2, set3.size());
        assertTrue(set3.contains("child"));
        assertTrue(set3.contains("children group"));
    }
}
