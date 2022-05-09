import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * The test class for cocktail recommender.
 */

public class CocktailRecommenderTest {

    private CocktailRecommender cr;
    private final String path = "./datasets/cocktail_df_cleaned.txt";
    private final String storePath = "./users";
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
        assertTrue(map.containsKey("Zizi Coin-coin".toLowerCase()));
        Cocktail cocktail = map.get("Zizi Coin-coin".toLowerCase());
        assertEquals("Punch / Party Drink".toLowerCase(), cocktail.getCategory());
        assertEquals("Margarita/Coupette glass".toLowerCase(), cocktail.getGlassware());
        assertEquals("Lemon Juice".toLowerCase(), cocktail.getIngredients().get(0));
        assertEquals("sour", cocktail.getTaste());
        assertEquals(12, cocktail.getPrice());
    }

    @Test
    public void testInitializePopularity() {
        CocktailRecommender cocktailRecommender = new CocktailRecommender();
        Map<String, Cocktail> map = cocktailRecommender.loadDataset(path);
        Map<String, Integer> popularity = cocktailRecommender.getPopularityMap();
        assertTrue(popularity.containsKey("Tequila Sour".toLowerCase()));
        assertEquals(0, popularity.get("Tequila Sour".toLowerCase()));
    }

    @Test
    public void testBuildIndexByPreference() {
        CocktailRecommender cocktailRecommender = new CocktailRecommender();
        Map<String, Cocktail> map = cocktailRecommender.loadDataset(path);
        Map<String, List<Cocktail>> preference = cocktailRecommender.buildIndexByPreference();
        assertTrue(preference.containsKey("sour"));
        assertTrue(preference.containsKey("spicy"));
        assertTrue(preference.containsKey("sweet"));
    }

    @Test
    public void testQueryByDrink() {
        CocktailRecommender cocktailRecommender = new CocktailRecommender();
        Map<String, Cocktail> receipe = cocktailRecommender.loadDataset(path);
        List<String> cocktail = cocktailRecommender.queryByDrink("Vodka".toLowerCase());
        assertTrue(cocktail.contains("Vodka And Tonic".toLowerCase()));
        assertTrue(cocktail.contains("Vodka Fizz".toLowerCase()));
        assertTrue(cocktail.contains("Vodka Martini".toLowerCase()));
        assertTrue(cocktail.contains("Vodka Russian".toLowerCase()));
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
    public void testRecommendByDijkstra() {
        List<Integer> cur = cr.recommendByDijkstra(0, 9);
        assertEquals(0, cur.get(0));
        assertEquals(5, cur.get(1));
        assertEquals(9, cur.get(2));
    }

    @Test
    public void testPriceOfDijkstra() {
        double res = cr.priceOfDijkstra(0, 9);
        assertEquals(24.12, res, 0.01);
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
        // get user customized recipe
        List<String> ingredients = new ArrayList<>();
        ingredients.add("brandy");
        Recipe r1 = new Recipe("rainbow", ingredients, "shake");
        String username1 = "Tom";

        // create new directory with recipe text file
        cr.customizeRecipe("Tom", r1.getDrink(), r1.getIngredients(), r1.getStyle(), storePath);
        File users = new File(storePath);
        Set<String> files = new HashSet<>(Arrays.asList(users.list()));

        // test whether the directory exist
        assertTrue(files.contains(username1));
        String recipePath = storePath + "/" + username1 + "/recipe.txt";

        // test recipe file exist
        File f = new File(recipePath);
        assertTrue(f.exists());
    }

}
