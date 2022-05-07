import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * The implementation class for cocktail recommender.
 */

public class CocktailRecommender implements ICocktailRecommender {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./datasets/cocktail_df_cleaned.csv")));
            String line = br.readLine();
            line = br.readLine();
            int index1 = line.indexOf(',');
            System.out.println(line.substring(0, index1));
            int index2 = line.indexOf(',', index1 + 1);
            System.out.println(line.substring(index1 + 1, index2));
            int index3 = line.indexOf(',', index2 + 1);
            System.out.println(line.substring(index2 + 1, index3));
            int index5 = line.lastIndexOf('\"');
            System.out.println(line.substring(index5 + 1));
            int index4 = line.lastIndexOf(',', index5 - 1);
            System.out.println(line.substring(index4 + 1, index5));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Cocktail> loadDataset(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./datasets/cocktail_df_cleaned.csv")));
            String line = null;
            while ((line = br.readLine()) != null){
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
