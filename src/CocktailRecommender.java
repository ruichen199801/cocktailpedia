import java.util.List;
import java.util.Map;

/**
 * The implementation class for cocktail recommender.
 */

public class CocktailRecommender implements ICocktailRecommender {


    @Override
    public Map<String, Cocktail> loadDataset(String path) {
        // TODO: implement
        return null;
    }

    @Override
    public Map<String, Integer> initializePopularity(Map<String, Cocktail> recipeMap) {
        // TODO: implement
        return null;
    }

    @Override
    public Map<String, String> buildIndexByPreference(Map<String, Cocktail> recipeMap) {
        // TODO: implement
        return null;
    }

    @Override
    public Cocktail queryByDrink(String drink, Map<String, Cocktail> recipeMap,
                                 Map<String, Integer> popularityMap) {
        // TODO: implement
        return null;
    }

    @Override
    public String recommendByClassic() {
        // TODO: implement
        return null;
    }

    @Override
    public String recommendByPopularity(Map<String, Integer> popularityMap) {
        // TODO: implement
        return null;
    }

    @Override
    public String recommendByPreference(String taste,
                                        Map<String, String> preferenceMap) {
        // TODO: implement
        return null;
    }

    @Override
    public Cocktail recommend(String taste,
                              Map<String, String> preferenceMap,
                              Map<String, Integer> popularityMap,
                              Map<String, Cocktail> recipeMap) {
        // TODO: implement
        return null;
    }

    @Override
    public void customizeRecipe(String username,
                                Map<String, Cocktail> recipeMap) {
        // TODO: implement
    }

}
