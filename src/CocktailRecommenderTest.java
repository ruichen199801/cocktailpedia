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
        cr.loadDataset("./datasets/cocktail_df_cleaned.txt");
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
                ("Old Fashioned", "Long Island Iced Tea", "Daiquiri", "Martini", "Whiskey Sour");
        assertTrue(CLASSIC.contains(curDrink));
    }

    @Test
    public void testRecommendByPopularity() {
        String curDrink = cr.recommendByPopularity(cr._popularityMap);
        assertEquals("1-900-FUK-MEUP", curDrink);
    }

    @Test
    public void testRecommendByPreference() {
        String curDrink = cr.recommendByPreference("1", cr._preferenceMap);
        List<String> CLASSIC = Arrays.asList
                ("Old Fashioned", "Long Island Iced Tea", "Daiquiri", "Martini", "Whiskey Sour");
        assertTrue(CLASSIC.contains(curDrink));
    }

    @Test
    public void testRecommend() {
        List<String> CLASSIC = Arrays.asList
                ("Old Fashioned", "Long Island Iced Tea", "Daiquiri", "Martini", "Whiskey Sour");
        Cocktail curCocktail =
                cr.recommend("1", cr._preferenceMap, cr._popularityMap, cr._recipeMap, 1);
        assertTrue(CLASSIC.contains(curCocktail.getDrink()));
        curCocktail =
                cr.recommend("1", cr._preferenceMap, cr._popularityMap, cr._recipeMap, 2);
        assertEquals("1-900-FUK-MEUP", curCocktail.getDrink());
        curCocktail =
                cr.recommend("1", cr._preferenceMap, cr._popularityMap, cr._recipeMap, 3);
        assertTrue(CLASSIC.contains(curCocktail.getDrink()));
        curCocktail =
                cr.recommend("1", cr._preferenceMap, cr._popularityMap, cr._recipeMap, 4);
        assertTrue(CLASSIC.contains(curCocktail.getDrink()));
    }

    @Test
    public void testCustomizeRecipe() {
        // TODO: implement
    }

}
