import java.util.List;
import java.util.Map;

/**
 *
 */

public interface ICocktailRecommender {

    /**
     *
     */
    public Map<String, Cocktail> loadDataset(String path);

    /**
     *
     */
    public Map<String, Integer> initializePopularity(Map<String, Cocktail> recipeMap);

    /**
     *
     */
    public Map<String, String> buildIndexByPreference(String taste,
                                                      Map<String, Cocktail> recipeMap);

    /**
     *
     */
    public Cocktail queryByDrink(String drink,
                                 Map<String, Cocktail> recipeMap,
                                 Map<String, Integer> popularityMap);

    /**
     *
     */
    public String recommendByClassic();

    /**
     *
     */
    public String recommendByPopularity(Map<String, Integer> popularityMap);

    /**
     *
     */
    public String recommendByPreference(String preference,
                                        Map<String, String> preferenceMap);

    /**
     *
     */
    public Cocktail recommend(Map<String, Cocktail> recipeMap);

    /**
     *
     */
    public Recipe customizeRecipe(String taste,
                                  Map<String, String> preferenceMap,
                                  Map<String, Cocktail> recipeMap);

    /**
     *
     */
    public User saveRecipe(String username,
                           String preference,
                           Recipe recipe);

    /**
     *
     */
    public List<Recipe> getRecipesByUser(String username);

    // TODO: remove user class and refactor to hashmap

}
