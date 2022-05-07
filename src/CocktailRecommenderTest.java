import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * The test class for cocktail recommender.
 */

public class CocktailRecommenderTest {

    private CocktailRecommender cr;

    @Before
    public void init() {
        cr = new CocktailRecommender();
        cr.loadDataset("");
        cr.initializePopularity(cr._recipeMap);
        cr.buildIndexByPreference(cr._recipeMap);
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
        String curDrink = cr.recommendByClassic();
        List<String> CLASSIC = Arrays.asList
                ("Old Fashioned", "Negroni", "Daiquiri", "Dry Martini", "Whiskey Sour");
        assertTrue(CLASSIC.contains(curDrink));
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
        // TODO: implement
    }

}
