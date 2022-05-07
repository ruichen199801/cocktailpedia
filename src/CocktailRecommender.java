import java.io.*;
import java.util.*;

/**
 * The implementation class for cocktail recommender.
 */

public class CocktailRecommender implements ICocktailRecommender {
    public static void main(String[] args) {
        String file = "./datasets/cocktail_df_cleaned.txt";
        Map<String, Cocktail> map = new CocktailRecommender().loadDataset(file);
    }
    // fields
    Map<String, Cocktail> _recipeMap;
    Map<String, Integer> _popularityMap;

    Map<String, List<Cocktail>> _preferenceMap;

    @Override
    public Map<String, Cocktail> loadDataset(String path) {
        Map<String, Cocktail> recipeMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String line = null;
            while ((line = br.readLine()) != null){
                int index1 = line.indexOf(';');
                String drink = line.substring(0, index1);
                int index2 = line.indexOf(';', index1 + 1);
                String category = line.substring(index1 + 1, index2);
                int index3 = line.indexOf(';', index2 + 1);
                String glassware = line.substring(index2 + 1, index3);
                int index5 = line.lastIndexOf(';');
                String preparation = line.substring(index5 + 1);
                int index4 = line.lastIndexOf(';', index5 - 1);
                String taste = line.substring(index4 + 1, index5);
                List<String> ingredients = Arrays.asList(line.substring(index3 + 1, index4).split(","));
                Cocktail cocktail = new Cocktail(drink, category, glassware, ingredients, taste, preparation);
                recipeMap.put(drink, cocktail);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // update filed
        _recipeMap = recipeMap;

        return recipeMap;
    }

    @Override
    public Map<String, Integer> initializePopularity(Map<String, Cocktail> recipeMap) {
        Map<String, Integer> popularityMap = new TreeMap<>();
        for (String s: recipeMap.keySet()){
            if (!popularityMap.containsKey(s)){
                popularityMap.put(s, 0);
            } else {
                popularityMap.put(s, popularityMap.get(s) + 1);
            }
        }

        // update filed
        _popularityMap = popularityMap;

        return popularityMap;
    }

    @Override
    public Map<String, List<Cocktail>> buildIndexByPreference(Map<String, Cocktail> recipeMap) {
        Map<String, List<Cocktail>> preferenceMap = new HashMap<>();
        for (Cocktail cocktail: recipeMap.values()){
            String taste = cocktail.getTaste();
            if (!preferenceMap.containsKey(taste)){
                preferenceMap.put(taste, new ArrayList<>());
            }
            preferenceMap.get(taste).add(cocktail);
        }

        // update preferenceMap
        _preferenceMap = preferenceMap;

        return preferenceMap;
    }

    @Override
    public Cocktail queryByDrink(String drink, Map<String, Cocktail> recipeMap,
                                 Map<String, Integer> popularityMap) {
        if (!recipeMap.containsKey(drink)){
            System.out.printf("We are sorry %s is not available currently, please try other drinks.", drink);
        }
        Cocktail cocktail = recipeMap.get(drink);
        popularityMap.put(drink, popularityMap.get(drink) + 1);
        return cocktail;
    }

    @Override
    public String recommendByClassic() {
        // directly return a class recommendation randomly in CLASSIC
        int index = (int) (Math.random() * 5);
        return CLASSIC.get(index);
    }

    @Override
    // First, convert the popularity map (general map) to a sorted set(sort by value) e.g. SortedSet<Pair<String, Integer>>
    // Then choose top xx drinks
    public String recommendByPopularity(Map<String, Integer> popularityMap) {
        // iterate to find the key with maximum value
        int max = Integer.MIN_VALUE;
        String res = "";
        for (Map.Entry<String, Integer> e : popularityMap.entrySet()) {
            if (max < e.getValue()) {
                max = e.getValue();
                res = e.getKey();
            }
        }
        // and return the key string
        return res;
    }

    @Override
    public String recommendByPreference(String taste,
                                        Map<String, List<Cocktail>> preferenceMap) {
        // get the preference list of cocktail, find the most popular one and return
        List<Cocktail> list = preferenceMap.get(taste);

        // corner case
        if (list == null || list.size() == 0) {
            return recommendByClassic();
        }

        int index = (int) (Math.random() * list.size());

        // randomly return one in the preference list
        String res = list.get(index).getDrink();


        // directly return the drink name
        return res;
    }

    @Override
    public Cocktail recommend(String taste,
                              Map<String, List<Cocktail>> preferenceMap,
                              Map<String, Integer> popularityMap,
                              Map<String, Cocktail> recipeMap,
                              int option) {
        /*
         * return different cocktail by the option:
         * return by classic when option is 1
         * return by popularity when option is 2
         * return by preference when option is 3
         * return by preference and popularity when option is 4
         * default return by classic when option is invalid
         */

        String drink = recommendByClassic();
        if (option == 2) {
            // directly get drink
            drink = recommendByPopularity(popularityMap);
        } else if (option == 3) {
            // directly get drink
            drink = recommendByPreference(taste, preferenceMap);
        } else if (option == 4) {
            // first get preference map
            List<Cocktail> list = preferenceMap.get(taste);
            if (list != null && list.size() != 0) {
                // then find the most popular one
                int max = Integer.MIN_VALUE;
                for (Cocktail cocktail : list) {
                    int curPopularity = popularityMap.get(cocktail.getDrink());
                    if (curPopularity > max) {
                        max = curPopularity;
                        drink = cocktail.getDrink();
                    }
                }
            }
        }

        return _recipeMap.get(drink);
    }

    @Override
    public void customizeRecipe(String username,
                                Map<String, Cocktail> recipeMap) {
        // TODO: implement
    }

}
