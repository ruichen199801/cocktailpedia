import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
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
                                String drink, String ingredients, String style) {
        Recipe recipe = new Recipe(drink, ingredients, style);
        String path = "/Users/wrzhang/Documents/CIT594/project";
        //create the new directory of this user if it does not exist
        File dir = new File(path + "/" + username);
        if(!dir.exists()){
            dir.mkdir();
        }
        //create new file used to store user's customized recipe
        File f = new File(dir.getPath() + "/recipe.txt");
        try {
            FileWriter myWriter = new FileWriter(f.getPath(), true);
            String content = username + " designed a new recipe named " + drink +
                    " with " + ingredients + " as base ingredients, in the " +
                    style + " style.";
                    myWriter.write(content);
            myWriter.close();
        } catch (IOException e){
            e.getStackTrace();
        }
    }

}
