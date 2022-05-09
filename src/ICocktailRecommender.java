import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The interface for cocktail recommender.
 */

public interface ICocktailRecommender {

    /**
     * A list of classic cocktails.
     */
    List<String> CLASSIC = Arrays.asList
            ("old fashioned", "long island iced tea", "daiquiri", "martini", "whiskey sour");

    /**
     * Load the csv format cocktail dataset, and store the cocktail recipes using a map.
     *
     * @param path file path of the dataset.
     * @return a map storing cocktails recipes, where the key is the name of the drink, the value is the recipe of the cocktail.
     */
    Map<String, Cocktail> loadDataset(String path);

    /**
     * Use number of queries of the drink to represent popularity. Initialize the popularity map of all drinks with 0s.
     *
     * @return a sorted map storing cocktail popularity, where the key is the name of the drink, the value is the number of queries of the drink (0 at first).
     */
    Map<String, Integer> initializePopularity();

    /**
     * Group cocktail drinks by taste preference, and store into a new preference map.
     *
     * @return a map storing different categories of cocktail tastes, where the key is the taste, the value is the list of cocktails of that taste.
     */
    Map<String, List<Cocktail>> buildIndexByPreference();

    /**
     * Return the recipe of the drink name the user queries for, and update the query count of this drink in the popularity map.
     *
     * @param drink the name of the drink.
     * @return a list of the drink names.
     */
    List<String> queryByDrink(String drink);

    /**
     * Recommend classic cocktails to users, and return the name of the recommended drink.
     *
     * @return a list of the drink names.
     */
    List<String> recommendByClassic();

    /**
     * Recommend cocktails with top query counts in the popularity map to users, and return the name of the recommended drink.
     *
     * @return a list of the drink names.
     */
    List<String> recommendByPopularity();

    /**
     * Recommend cocktails with the users' preferred taste in the preference map to users, and return the name of the recommended drink.
     *
     * @param taste the users' preferred taste.
     * @return a list of the drink names.
     */
    List<String> recommendByPreference(String taste);

    /**
     * Recommend cocktails by using Dijkstra based on constructed graph, and return a list of vertices which represent the shortest path.
     *
     * @param source the source drink.
     * @param target the target drink.
     * @return a list of vertices which represent the shortest path, first node is source and last is target.
     */
    List<Integer> recommendByDijkstra(int source, int target);

    /**
     * Recommend cocktails by running Dijkstra, and return the price of all drinks recommended after applying the discounts.
     *
     * @param source the source drink.
     * @param target the target drink.
     * @return the price of all drinks recommended after applying the discounts.
     */
    Double priceOfDijkstra(int source, int target);

    /**
     * Return the recommended cocktail recipe to users based on users' options:
     * Option 1: Recommend by classic
     * Option 2: Recommend by popularity
     * Option 3: Recommend by preference
     * Option 4: Recommend by preference and popularity
     *
     * @param taste the users' preferred taste.
     * @param option the recommendation option the user chooses.
     * @return a list of the drink names.
     */
    List<String> recommend(String taste, int option);

    /**
     * Allow a user to create a customized cocktail recipe. The user can choose from a list of options we provide, including type of the liquor/spirit, taste, preparation style. Then we assemble the recipe based on the choices made by the user, generate a txt file under the directory named by the username, and store it into a user map.
     *
     * @param username    name of the user
     * @param drink       name of drink entered by user
     * @param ingredients ingredients of drink entered by user
     * @param style       style of drink entered by user
     * @param path        file path of storing the recipe
     * @return whether the recipe has been successfully saved.
     */
    boolean customizeRecipe(String username,
                            String drink,
                            List<String> ingredients,
                            String style,
                            String path);

    /**
     * Allow a user to order the drink.
     * @param drink name of drink entered by user
     * @return price of the drink
     */
    int order(String drink);
}