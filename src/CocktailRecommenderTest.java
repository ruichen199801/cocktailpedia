import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * The test class for cocktail recommender.
 */

public class CocktailRecommenderTest {

    private CocktailRecommender cr;
    private String path = "./datasets/cocktail_df_cleaned.txt";

    @Before
    public void init() {
        cr = new CocktailRecommender();
        cr.loadDataset(path);
        cr.buildIndexByPreference();
    }

    @Test
    public void testLoadDataset() {
        CocktailRecommender cocktailRecommender = new CocktailRecommender();
        Map<String, Cocktail> map = cocktailRecommender.loadDataset(path);
        assertTrue(map.keySet().contains("Zizi Coin-coin".toLowerCase()));
        Cocktail cocktail = map.get("Zizi Coin-coin".toLowerCase());
        assertEquals("Punch / Party Drink".toLowerCase(), cocktail.getCategory());
        assertEquals("Margarita/Coupette glass".toLowerCase(), cocktail.getGlassware());
        assertEquals("Lemon Juice".toLowerCase(), cocktail.getIngredients().get(0));
        assertEquals("sour", cocktail.getTaste());
    }

    @Test
    public void testInitializePopularity() {
        CocktailRecommender cocktailRecommender = new CocktailRecommender();
        Map<String, Cocktail> map = cocktailRecommender.loadDataset(path);
        Map<String, Integer> popularity = cocktailRecommender.getPopularityMap();
        assertTrue(popularity.keySet().contains("Tequila Sour".toLowerCase()));
        assertEquals(0, popularity.get("Tequila Sour".toLowerCase()));
    }

    @Test
    public void testBuildIndexByPreference() {
        CocktailRecommender cocktailRecommender = new CocktailRecommender();
        Map<String, Cocktail> map = cocktailRecommender.loadDataset(path);
        Map<String, List<Cocktail>> preference = cocktailRecommender.buildIndexByPreference();
        assertTrue(preference.keySet().contains("sour"));
        assertTrue(preference.keySet().contains("spicy"));
        assertTrue(preference.keySet().contains("sweet"));
    }

    @Test
    public void testQueryByDrink() {
        CocktailRecommender cocktailRecommender = new CocktailRecommender();
        Map<String, Cocktail> receipe = cocktailRecommender.loadDataset(path);
        Cocktail cocktail = cocktailRecommender.queryByDrink("White Lady".toLowerCase());
        assertEquals("Ordinary Drink".toLowerCase().toLowerCase(), cocktail.getCategory());
        assertEquals("Cocktail glass".toLowerCase(), cocktail.getGlassware());
        assertEquals("sour", cocktail.getTaste());
    }

    @Test
    public void testRecommendByClassic() {
        List<String> cur = cr.recommendByClassic();
        assertTrue(cur.contains("old fashioned"));
    }

    @Test
    public void testRecommendByPopularity() {
        List<String> cur = cr.recommendByPopularity();
        assertTrue(cur.contains("1-900-fuk-meup"));
    }

    @Test
    public void testRecommendByPreference() {
        List<String> cur = cr.recommendByPreference("sour");
        assertTrue(cur.contains("3-mile long island iced tea"));
    }

    @Test
    public void testRecommend() {
        List<String> cur = cr.recommend("sour", 1);
        assertTrue(cur.contains("old fashioned"));

        cur = cr.recommend("sour", 2);
        assertTrue(cur.contains("1-900-fuk-meup"));
        assertTrue(cur.contains("151 florida bushwacker"));

        cur = cr.recommend("sour", 3);
        assertTrue(cur.contains("69 special"));
        assertTrue(cur.contains("a furlong too late"));
        assertTrue(cur.contains("a piece of ass"));
        assertTrue(cur.contains("a splash of nash"));

        cur = cr.recommend("sour", 4);
        assertTrue(cur.contains("3-mile long island iced tea"));
    }

    @Test
    public void testCustomizeRecipe() {
        // TODO: implement
    }

}
