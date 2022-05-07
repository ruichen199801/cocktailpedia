import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * The implementation class for cocktail recommender.
 */

public class CocktailRecommender implements ICocktailRecommender {
    // fields
    private Map<String, Cocktail> recipeMap;
    private Map<String, Integer> popularityMap;
    private Map<String, List<Cocktail>> preferenceMap;

    public CocktailRecommender(){
        recipeMap = new HashMap<>();
        popularityMap = new HashMap<>();
        preferenceMap = new HashMap<>();
    }

    @Override
    public Map<String, Cocktail> loadDataset(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String line = null;
            while ((line = br.readLine()) != null){
                int index1 = line.indexOf(';');
                String drink = line.substring(0, index1).toLowerCase();
                int index2 = line.indexOf(';', index1 + 1);
                String category = line.substring(index1 + 1, index2).toLowerCase();
                int index3 = line.indexOf(';', index2 + 1);
                String glassware = line.substring(index2 + 1, index3).toLowerCase();
                int index5 = line.lastIndexOf(';');
                String preparation = line.substring(index5 + 1);
                int index4 = line.lastIndexOf(';', index5 - 1);
                String taste = line.substring(index4 + 1, index5).toLowerCase();
                List<String> ingredients = Arrays.asList(line.substring(index3 + 1, index4).toLowerCase().split(","));
                Cocktail cocktail = new Cocktail(drink, category, glassware, ingredients, taste, preparation);
                recipeMap.put(drink, cocktail);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializePopularity(recipeMap);
        return recipeMap;
    }

    private Map<String, Integer> initializePopularity(Map<String, Cocktail> recipeMap) {
        for (String s: recipeMap.keySet()){
            if (!popularityMap.containsKey(s)){
                popularityMap.put(s, 0);
            } else {
                popularityMap.put(s, popularityMap.get(s) + 1);
            }
        }
        return popularityMap;
    }

    @Override
    public Map<String, List<Cocktail>> buildIndexByPreference(Map<String, Cocktail> recipeMap) {

        for (Cocktail cocktail: recipeMap.values()){
            String taste = cocktail.getTaste();
            if (!preferenceMap.containsKey(taste)){
                preferenceMap.put(taste, new ArrayList<>());
            }
            preferenceMap.get(taste).add(cocktail);
        }
        return preferenceMap;
    }

    @Override
    public Cocktail queryByDrink(String drink, Map<String, Cocktail> recipeMap) {
        if (!recipeMap.containsKey(drink)){
            System.out.printf("We are sorry %s is not available currently, please try other drinks.", drink);
        }
        Cocktail cocktail = recipeMap.get(drink.toLowerCase());
        popularityMap.put(drink, popularityMap.get(drink) + 1);
        return cocktail;
    }

    @Override
    public List<String> recommendByClassic() {
        // directly return CLASSIC
        return CLASSIC;
    }

    @Override
    // First, convert the popularity map (general map) to a sorted set(sort by value) e.g. SortedSet<Pair<String, Integer>>
    // Then choose top xx drinks
    public List<String> recommendByPopularity() {
        SortedSet<Pair<String, Integer>>  set = new TreeSet<>(createComparator());
        for (Map.Entry<String, Integer> entry: popularityMap.entrySet()){
            set.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        List<String> recommendation = new ArrayList<>();
        for (Pair<String, Integer> pair: set){
            if (recommendation.size() <= 5){
                recommendation.add(pair.getKey());
            } else {
                break;
            }
        }
        return recommendation;
    }

    @Override
    public List<String> recommendByPreference(String taste) {
        List<Cocktail> tastes = preferenceMap.get(taste);
        SortedSet<Pair<String, Integer>>  set = new TreeSet<>(createComparator());
        for (Cocktail cocktail: tastes){
            set.add(new Pair<>(cocktail.getDrink(), popularityMap.get(cocktail.getDrink())));
        }
        List<String> recommendation = new ArrayList<>();
        if (set.size() > 5){
            for (Pair<String, Integer> pair: set){
                if (recommendation.size() <= 5){
                    recommendation.add(pair.getKey());
                } else {
                    break;
                }
            }
        } else {
            for (Pair<String, Integer> pair: set){
                recommendation.add(pair.getKey());
            }
        }
        return recommendation;
    }

    @Override
    public List<String> recommend(String taste, int option) {
        /*
         * return different cocktail by the option:
         * return by classic when option is 1
         * return by popularity when option is 2
         * return by preference when option is 3
         * return by preference and popularity when option is 4
         * default return by classic when option is invalid
         */
        switch (option) {
            case 1:
                return recommendByClassic();
            case 2:
                return recommendByPopularity();
            case 3:
                return recommendByPreference(taste);
            case 4:
                List<String> pops = recommendByPopularity();
                List<String> recommendation = new ArrayList<>();
                for (String s: pops){
                    String taste1 = recipeMap.get(s).getTaste();
                    if (taste1.equals(taste)){
                        if (recommendation.size() <= 5){
                            recommendation.add(s);
                        } else {
                            break;
                        }
                    }
                }
                return  recommendation;
            default:
                return  recommendByClassic();
        }
    }

    @Override
    public void customizeRecipe(String username,
                                Map<String, Cocktail> recipeMap) {
        // TODO: implement
    }

    public Map<String, Cocktail> getRecipeMap() {
        return recipeMap;
    }

    public void setRecipeMap(Map<String, Cocktail> recipeMap) {
        this.recipeMap = recipeMap;
    }

    public Map<String, Integer> getPopularityMap() {
        return popularityMap;
    }

    public void setPopularityMap(Map<String, Integer> popularityMap) {
        this.popularityMap = popularityMap;
    }

    public Map<String, List<Cocktail>> getPreferenceMap() {
        return preferenceMap;
    }

    public void setPreferenceMap(Map<String, List<Cocktail>> preferenceMap) {
        this.preferenceMap = preferenceMap;
    }

    private Comparator<Pair<String, Integer>> createComparator() {

        return new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())){
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o1.getValue().compareTo(o2.getValue());
                }
            }
        };
    }
}
