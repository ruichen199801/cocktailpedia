import java.util.List;
import java.util.Map;

/**
 *
 */

public class CocktailRecommender implements ICocktailRecommender {

    @Override
    public Map<String, Cocktail> loadDataset(String path) {
        return null;
    }

    @Override
    public Map<String, Integer> initializePopularity(Map<String, Cocktail> recipeMap) {
        return null;
    }

    @Override
    public Map<String, String> buildIndexByPreference(String taste, Map<String, Cocktail> recipeMap) {
        return null;
    }

    @Override
    public Cocktail queryByDrink(String drink, Map<String, Cocktail> recipeMap, Map<String, Integer> popularityMap) {
        return null;
    }

    @Override
    public String recommendByClassic() {
        return null;
    }

    @Override
    public String recommendByPopularity(Map<String, Integer> popularityMap) {
        return null;
    }

    @Override
    public String recommendByPreference(String preference, Map<String, String> preferenceMap) {
        return null;
    }

    @Override
    public Cocktail recommend(Map<String, Cocktail> recipeMap) {
        return null;
    }

    @Override
    public Recipe customizeRecipe(String taste, Map<String, String> preferenceMap, Map<String, Cocktail> recipeMap) {
        return null;
    }

    @Override
    public User saveRecipe(String username, String preference, Recipe recipe) {
        return null;
    }

    @Override
    public List<Recipe> getRecipesByUser(String username) {
        return null;
    }
}
