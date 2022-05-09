import java.util.*;

/**
 * The application class to boot the cocktail recommender, accept user input, and output txt files.
 */

public class RecommenderApplication {

    /**
     * Scanner class to accept user input
     */
    static Scanner sc;

    /**
     * CocktailRecommender instance
     */
    static CocktailRecommender cr;

    /**
     * List 4 options the program offer
     */
    public static void printOptions(){
        System.out.println("1 - Check cocktail recipe");
        System.out.println("2 - Recommend for you");
        System.out.println("3 - Discount combo");
        System.out.println("4 - Customize your own");
    }

    /**
     * Get detailed recipe based on name of the drink
     */
    public static void printCocktailInfo(Cocktail selectedCocktail){
        System.out.println("Name : " + selectedCocktail.getDrink());
        System.out.println("Category : " + selectedCocktail.getCategory());
        System.out.println("Glassware : " + selectedCocktail.getGlassware());
        System.out.print("Ingredients : ");
        List<String> ingredients = selectedCocktail.getIngredients();
        for(int i = 0; i < ingredients.size(); i++){
            System.out.print(ingredients.get(i));
            if(i != ingredients.size() - 1){
                System.out.print(" ,");
            } else {
                System.out.println();
            }
        }
        System.out.println("Taste : " + selectedCocktail.getTaste());
        System.out.println("Price : " + selectedCocktail.getPrice());
        System.out.println("Preparation Instructions : " + selectedCocktail.getPreparation());
        System.out.println("-------------------------------------------------");
    }

    /**
     * Print the result of recommendation
     */
    public static void printResult(List<String> recommend){
        if(recommend == null){
            System.out.println("Sorry, we have no such cocktails in our system.");
            return ;
        }
        StringBuilder sb = new StringBuilder();
        System.out.println("We recommend these recipes for you based on your preference.");
        for(int i = 0; i < recommend.size(); i++){
            sb.append("(" + (i + 1) + ") " + recommend.get(i) + "; ");
        }
        System.out.println(sb);
    }

    public static int selectOne(List<String> recommend){
        if(recommend == null || recommend.size() == 0){
            return 0;
        }
        printResult(recommend);
        int k = getValidInt(1, recommend.size());
        String selected = recommend.get(k - 1);
        Cocktail selectedCocktail = cr.lookup(selected);
        printCocktailInfo(selectedCocktail);
        int price = cr.order(selected);
        return price;
    }

    public static int getValidInt(int min, int max){
        if(min != max){
            System.out.println("Please select a number between " + min + " and " + max);
        } else {
            System.out.println("Please enter number" + min);
        }
        String input = sc.next();
        while(true){
            try{
                int k = Integer.parseInt(input);
                if(k >= min && k <= max){
                    return k;
                }
            } catch(NumberFormatException e){

            }
            System.out.println("Please enter a valid number between " + min + " and " + max);
            input = sc.next();
        }
    }

    /**
     * Main class to interact with users.
     */
    public static void main(String[] args) {
        // Load the cocktail data into recipeMap
        String dataPath = "./datasets/cocktail_df_cleaned.txt";
        String storePath = "./users";
        cr = new CocktailRecommender();
        Map<String, Cocktail> recipeMap = cr.loadDataset(dataPath);
        // initialize popularity map and preference map
        Map<String, List<Cocktail>> prefMap = cr.buildIndexByPreference();

        sc = new Scanner(System.in);
        // start running the program until user quits
        double totalPrice = 0;
        System.out.println("Welcome to CocktailPedia, an intelligent cocktail recommender! May I have your username please?");
        Scanner sc = new Scanner(System.in);
        String username;
        username = sc.next();
        System.out.println("Welcome, " + username + "!");
        System.out.println("How may I help you today? Get the best cocktail for you " +
                "by entering one of the numbers below.");

        while (true) {
            String drink = "";
            String taste = "";
            String ingredients = "";
            String style = "";
            printOptions();
            int option = getValidInt(1, 4);
            List<String> recommendedCocktail = new ArrayList<>();

            switch(option) {
                case 1:
                    System.out.println("Which cocktail recipe would you like to know?");
                    drink = sc.next();
                    recommendedCocktail = cr.queryByDrink(drink);
                    totalPrice += selectOne(recommendedCocktail);
                    break;

                case 2:
                    System.out.println("Based on which principle, would you like us to recommend some for you?");
                    System.out.println("1 - get the most classic ones!");
                    System.out.println("2 - get the most popular ones!");
                    System.out.println("3 - recommend by your preference");
                    System.out.println("4 - recommend by preference and popularity");
                    int option2 = getValidInt(1, 4);
                    taste = "";
                    if (option2 == 3 || option2 == 4){
                        System.out.println("Which taste would you like? e.g., sweet, sour, bitter, cream, etc.");
                        List<String> tastes = Arrays.asList
                                ("sweet", "sour", "cream", "bitter", "water", "spicy", "egg", "salty", "mint");
                        taste = sc.next();
                        if (!tastes.contains(taste)) {
                            System.out.println("Please enter a valid taste!");
                            break;
                        }
                    }
                    recommendedCocktail = cr.recommend(taste, option2);
                    totalPrice += selectOne(recommendedCocktail);
                    break;

                case 4:
                    System.out.println("Please design a name of the cocktail.");
                    while(drink.equals("")) drink = sc.nextLine();
                    System.out.println("Please design the ingredients of the cocktail. Separate by comma.");
                    while(ingredients.equals("")) ingredients = sc.nextLine();
                    System.out.println("Please design the style of the cocktail (shake or stir).");
                    while(style.equals("")) style = sc.nextLine();
                    List<String> ingredientsList = Arrays.asList(ingredients.toLowerCase().split(","));
                    boolean saved = cr.customizeRecipe(username, drink, ingredientsList, style, storePath);
                    if(saved){
                        System.out.println("Your recipe has been successfully saved!");
                    } else {
                        System.out.println("Sorry, some errors occurred while saving your recipe.");
                    }
                    break;

                default:
                    System.out.println("We have a special offer for you. Select two drinks from the provided drink list and we " +
                            "will provide you with the best discount.");
                    Cocktail[] cocktails = cr.getCocktails();
                    for(int i = 0; i < cocktails.length; i++){
                        System.out.print((i + 1) + " : " + cocktails[i].getDrink() + "; ");
                    }
                    System.out.println();
                    System.out.print("Enter the index of your first choice :");
                    int num1 = getValidInt(1, cocktails.length) - 1;
                    System.out.print("Enter the index of your second choice :");
                    int num2 = getValidInt(1, cocktails.length) - 1;
                    List<Integer> indexOf = cr.recommendByDijkstra(num1, num2);
                    System.out.println("You can have a best discount by this combination.");
                    for(Integer k : indexOf){
                        printCocktailInfo(cocktails[k]);
                    }
                    double combinationPrice = ((int) Math.round(cr.priceOfDijkstra(num1, num2) * 100)) / 100;
                    System.out.println("The total price for this discount combo set is " + combinationPrice);
                    totalPrice += combinationPrice;
            }
            // ask the user whether to select another option
            System.out.println("Would you like to have another one? 1 for Yes and 0 for No.");
            int exit = getValidInt(0, 1);
            // exit the program
            if (exit == 0) {
                totalPrice = ((int) Math.round(totalPrice * 100)) / 100;
                System.out.println("The total cost of your order today is " + totalPrice);
                System.out.println("Hope we meet your expectations. Enjoy your cocktail!");
                System.exit(0);
            }
        }
    }

}
