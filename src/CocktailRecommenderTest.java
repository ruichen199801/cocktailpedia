import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * The test class for cocktail recommender.
 */

public class CocktailRecommenderTest {

    private CocktailRecommender cr;
    private String path;

    @Before
    public void init() {
        cr = new CocktailRecommender();
        path = "/Users/wrzhang/Documents/CIT594/project";
    }

    @Test
    public void testLoadDataset() {
        // TODO: implement
    }

    @Test
    public void testInitializePopularity() {
        // TODO: implement
    }

    @Test
    public void testBuildIndexByPreference() {
        // TODO: implement
    }

    @Test
    public void testQueryByDrink() {
        // TODO: implement
    }

    @Test
    public void testRecommendByClassic() {
        // TODO: implement
    }

    @Test
    public void testRecommendByPopularity() {
        // TODO: implement
    }

    @Test
    public void testRecommendByPreference() {
        // TODO: implement
    }

    @Test
    public void testRecommend() {
        // TODO: implement
    }

    @Test
    public void testCustomizeRecipe() {
        //get user customized recipe
        Recipe r1 = new Recipe("rainbow", "brandy", "shake");
        String username1 = "Tom";
        //create new directory with recipe text file
        cr.customizeRecipe("Tom", r1.getDrink(), r1.getIngredients(), r1.getStyle(), path);
        File users = new File(path);
        Set<String> files = new HashSet<>(Arrays.asList(users.list()));
        //test whether the directory exist
        assertTrue(files.contains(username1));
        String recipePath = path + "/" + username1 + "/recipe.txt";
        //test recipe file exist
        File f = new File(recipePath);
        assertTrue(f.exists());
    }

}
