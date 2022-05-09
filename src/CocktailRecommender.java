import javafx.util.Pair;

import java.io.*;
import java.util.*;


/**
 * The implementation class for cocktail recommender.
 */

public class CocktailRecommender implements ICocktailRecommender {
    public static void main(String[] args) {
        String path = "./datasets/cocktail_df_cleaned.txt";
        CocktailRecommender cocktailRecommender = new CocktailRecommender();
        Map<String, Cocktail> map = cocktailRecommender.loadDataset(path);
    }
    // fields
    private Map<String, Cocktail> recipeMap;
    private Map<String, Integer> popularityMap;
    private Map<String, List<Cocktail>> preferenceMap;
    private final GraphM graphM;
    private final Trie root;
    private Cocktail[] cocktails;


    public CocktailRecommender() {
        recipeMap = new HashMap<>();
        popularityMap = new HashMap<>();
        preferenceMap = new HashMap<>();
        root = new Trie();
        graphM = new GraphM(10);
        // 0 1
        graphM.addEdge(0, 1, 0.82);
        graphM.addEdge(1, 0, 0.82);
        // 1 2
        graphM.addEdge(1, 2, 0.57);
        graphM.addEdge(2, 1, 0.57);
        // 1 4
        graphM.addEdge(1, 4, 0.89);
        graphM.addEdge(4, 1, 0.89);
        // 0 3
        graphM.addEdge(0, 3, 0.75);
        graphM.addEdge(3, 0, 0.75);
        // 3 4
        graphM.addEdge(3, 4, 0.96);
        graphM.addEdge(4, 3, 0.96);
        // 3 8
        graphM.addEdge(3, 8, 0.52);
        graphM.addEdge(8, 3, 0.52);
        // 0 5
        graphM.addEdge(0, 5, 0.66);
        graphM.addEdge(5, 0, 0.66);
        // 5 6
        graphM.addEdge(5, 6, 0.98);
        graphM.addEdge(6, 5, 0.98);
        // 4 9
        graphM.addEdge(4, 9, 0.65);
        graphM.addEdge(9, 4, 0.65);
        // 2 8
        graphM.addEdge(2, 8, 0.73);
        graphM.addEdge(8, 2, 0.73);
        // 5 7
        graphM.addEdge(5, 7, 0.72);
        graphM.addEdge(7, 5, 0.72);
        // 5 9
        graphM.addEdge(5, 9, 0.68);
        graphM.addEdge(9, 5, 0.68);
    }

    @Override
    public Map<String, Cocktail> loadDataset(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String line = null;
            while ((line = br.readLine()) != null) {
                int index1 = line.indexOf(';');
                String drink = convertDrinkName(line.substring(0, index1)).toLowerCase();
                root.addWord(drink);

                int index2 = line.indexOf(';', index1 + 1);
                String category = line.substring(index1 + 1, index2).toLowerCase();

                int index3 = line.indexOf(';', index2 + 1);
                String glassware = line.substring(index2 + 1, index3).toLowerCase();

                int index4 = line.indexOf(';', index3 + 1);
                List<String> ingredients = Arrays.asList(line.substring(index3 + 1, index4).toLowerCase().split(","));

                int index5 = line.indexOf(';', index4 + 1);
                String taste = line.substring(index4 + 1, index5).toLowerCase();

                int index6 = line.lastIndexOf(';');
                String preparation = line.substring(index5 + 1, index6);

                int price = Integer.parseInt(line.substring(index6 + 1));

                Cocktail cocktail = new Cocktail(drink, category, glassware, ingredients, taste, preparation, price);
                recipeMap.put(drink, cocktail);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializePopularity();
        // update cocktail array
        cocktails = new Cocktail[10];
        int i = 0;
        cocktails[i++] = recipeMap.get("old fashioned".toLowerCase());
        cocktails[i++] = recipeMap.get("Long Island Iced Tea".toLowerCase());
        cocktails[i++] = recipeMap.get("daiquiri".toLowerCase());
        cocktails[i++] = recipeMap.get("martini".toLowerCase());
        cocktails[i++] = recipeMap.get("whiskey sour".toLowerCase());
        cocktails[i++] = recipeMap.get("1-900-FUK-MEUP".toLowerCase());
        cocktails[i++] = recipeMap.get("69 Special".toLowerCase());
        cocktails[i++] = recipeMap.get("A Piece of Ass".toLowerCase());
        cocktails[i++] = recipeMap.get("Vodka And Tonic".toLowerCase());
        cocktails[i++] = recipeMap.get("Tequila Fizz".toLowerCase());

        return recipeMap;
    }

    private String convertDrinkName(String word){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(Character.isAlphabetic(ch)){
                ch = Character.toLowerCase(ch);
                if (ch - 'a' < 26 && ch - 'a' >= 0){
                    sb.append(ch);
                }
            } else if (Character.isDigit(ch)){
                sb.append(ch);
            } else if(ch == ' '){
                sb.append(ch);
            } else if (ch == '-'){
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private Map<String, Integer> initializePopularity() {
        for (String s : recipeMap.keySet()) {
            if (!popularityMap.containsKey(s)) {
                popularityMap.put(s, 0);
            } else {
                popularityMap.put(s, popularityMap.get(s) + 1);
            }
        }
        return popularityMap;
    }

    @Override
    public Map<String, List<Cocktail>> buildIndexByPreference() {

        for (Cocktail cocktail : recipeMap.values()) {
            String taste = cocktail.getTaste();
            if (!preferenceMap.containsKey(taste)) {
                preferenceMap.put(taste, new ArrayList<>());
            }
            preferenceMap.get(taste).add(cocktail);
        }
        return preferenceMap;
    }

    @Override
    public List<String> queryByDrink(String drink) {
        List<String> query = root.queryByPrefix(drink);
        if (query.size() == 0) {
            System.out.printf("We are sorry %s is not available currently, please try other drinks.", drink);
        }
        return query;
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
        SortedSet<Pair<String, Integer>> set = new TreeSet<>(createComparator());
        for (Map.Entry<String, Integer> entry : popularityMap.entrySet()) {
            set.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        List<String> recommendation = new ArrayList<>();
        for (Pair<String, Integer> pair : set) {
            if (recommendation.size() <= 5) {
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
        SortedSet<Pair<String, Integer>> set = new TreeSet<>(createComparator());
        for (Cocktail cocktail : tastes) {
            set.add(new Pair<>(cocktail.getDrink(), popularityMap.get(cocktail.getDrink())));
        }
        List<String> recommendation = new ArrayList<>();
        if (set.size() > 5) {
            for (Pair<String, Integer> pair : set) {
                if (recommendation.size() <= 5) {
                    recommendation.add(pair.getKey());
                } else {
                    break;
                }
            }
        } else {
            for (Pair<String, Integer> pair : set) {
                recommendation.add(pair.getKey());
            }
        }
        return recommendation;
    }

    @Override
    public List<Integer>  recommendByDijkstra(int source, int target) {
        // run Dijkstra

        // get number of nodes
        int n = graphM.nodesCount();

        // create shortest array and pred array
        double[] shortest = new double[n];
        int[] pred = new int[n];

        // initialize
        Arrays.fill(shortest, Integer.MAX_VALUE);
        Arrays.fill(pred, -1);

        // update shortest[source]
        shortest[source] = 0;

        // create a set to maintain the node who are not expanded
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(i);
        }

        // iterate to expanded node
        while (!set.isEmpty()) {
            Integer curNode = Collections.min(set);
            for (Integer integer : set) {
                if (shortest[integer] < shortest[curNode]) {
                    curNode = integer;
                }
            }
            if (shortest[curNode] == Integer.MAX_VALUE) {
                break;
            }
            int[] neighbors = graphM.neighbors(curNode);
            for (int neighbor : neighbors) {
                if (shortest[neighbor] > shortest[curNode] + graphM.getEdge(curNode, neighbor)) {
                    shortest[neighbor] = shortest[curNode] + graphM.getEdge(curNode, neighbor);
                    pred[neighbor] = curNode;
                }
            }
            set.remove(curNode);
        }

        // return value
        if (pred[target] == -1) {
            return new ArrayList<>();
        }

        // find the shortest path
        List<Integer> res = new ArrayList<>();
        res.add(target);

        while (target != source) {
            res.add(pred[target]);
            target = pred[target];
        }
        Collections.reverse(res);
        return res;
    }

    public Double prizeOfDijkstra(int source, int target) {
        // get path at first
        List<Integer> path = recommendByDijkstra(source, target);

        // corner case
        if (path == null || path.size() <= 1) {
            return 0.0;
        }

        // get average discount
        double averageEdge = 0;
        for (int i = 1; i < path.size(); i++) {
            averageEdge += graphM.getEdge(path.get(i - 1), path.get(i));
        }
        averageEdge /= path.size();

        // get the sum of all drink
        int sumPrize = 0;
        for (Integer integer : path) {
            sumPrize += cocktails[integer].getPrice();
        }

        return sumPrize / averageEdge;
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
                for (String s : pops) {
                    String taste1 = recipeMap.get(s).getTaste();
                    if (taste1.equals(taste)) {
                        if (recommendation.size() <= 5) {
                            recommendation.add(s);
                        } else {
                            break;
                        }
                    }
                }
                return recommendation;
            default:
                return recommendByClassic();
        }
    }

    @Override
    public boolean customizeRecipe(String username,
                                   String drink, List<String> ingredients, String style,
                                   String path) {
        Recipe recipe = new Recipe(drink, ingredients, style);
        //create the new directory of this user if it does not exist
        File dir = new File(path + "/" + username);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //create new file used to store user's customized recipe
        File f = new File(dir.getPath() + "/recipe.txt");
        boolean saved = false;
        String s = "";
        for (int i = 0; i < ingredients.size(); i++){
            if (i == ingredients.size() - 1){
                s += ingredients.get(i);
            } else {
                s += ingredients.get(i) + ", ";
            }
        }
        try {
            FileWriter myWriter = new FileWriter(f.getPath(), true);
            String content = username + " designed a new recipe named " + drink +
                    " with " + ingredients + " as base ingredients, in the " +
                    style + " style.\n";
            myWriter.write(content);
            myWriter.close();
            saved = true;
        } catch (IOException e) {
            e.getStackTrace();
        }
        return saved;
    }

    @Override
    public int order (String drink){
        drink = convertDrinkName(drink);
        Cocktail cocktail = recipeMap.get(drink);
        popularityMap.put(drink, popularityMap.get(drink) + 1);
        return cocktail.getPrice();
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

    public Cocktail[] getCocktails(){
        return cocktails;
    }
    public Cocktail lookup(String drink){
        return recipeMap.getOrDefault(drink, null);
    }
    private Comparator<Pair<String, Integer>> createComparator() {
        return new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        };
    }
}
