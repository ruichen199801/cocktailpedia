import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The application class to boot the cocktail recommender, accept user input, and output txt files.
 */

public class RecommenderApplication {
    public static void printOptions(){
        System.out.println("1 - Recommend by classic");
        System.out.println("2 - Recommend by popularity");
        System.out.println("3 - Recommend by preference");
        System.out.println("4 - Customize your own");
    }
    public static void main(String[] args) {

        // Load the cocktail data into recipeMap
        String path = "/Users/wrzhang/hw-8---project-team-algoholic/users";
        CocktailRecommender cr = new CocktailRecommender();
        Map<String, Cocktail> recipeMap = cr.loadDataset(path);
        //initialize popularity map and preference map
        Map<String, List<Cocktail>> prefMap = cr.buildIndexByPreference();

        //start running the program until user quits
        while(true){
            System.out.println("Welcome to our cocktail recommendation program! May I have you username?");
            Scanner sc = new Scanner(System.in);
            String username;
            username = sc.next();
            System.out.println("Welcome, " + username + "!");
            System.out.println("How may I help you today? Get the best cocktail for you " +
                    "by entering the corresponding number.");
            String userInput;
            printOptions();
            while((userInput = sc.next()) != null){
                try{
                    int k = Integer.parseInt(userInput);
                    List<String> selectedCocktail = null;
                    switch(k) {
                        case 1:
                            selectedCocktail = cr.recommendByClassic();
                            break;
                        case 2:
                            selectedCocktail = cr.recommendByPopularity();
                            break;
                        case 3:
                            System.out.println("What kind of taste would you like?");
                            String taste = sc.next();
                            while(!prefMap.containsKey(taste)){
                                System.out.println("Sorry, we don't offer that taste. Please choose another one.");
                                taste = sc.next();
                            }
                            selectedCocktail = cr.recommendByPreference(taste);
                            break;
                        case 4:
                            System.out.println("Please define the drink of the cocktail.");
                            String drink = sc.next();
                            System.out.println("Please define the ingredients of the cocktail. Separate by comma.");
                            String ingredients = sc.next();
                            System.out.println("Please define the style of the cocktail.");
                            String style = sc.next();
                            boolean saved = cr.customizeRecipe(username, drink, ingredients, style, path);
                            if(saved){
                                System.out.println("Your recipe has been successfully saved!");
                            } else {
                                System.out.println("Sorry, some errors occurred while saving your recipe.");
                            }
                            break;
                        default:
                            System.out.println("Please enter a number between 1 and 4");
                    }
                    if(selectedCocktail != null) {
                        System.out.printf("We reccomend %s for you.", selectedCocktail);
                    }
                    //ask the user whether to select another option
                    System.out.println("Would you like to have another one? 1 for Yes and 0 for no.");
                    String continued = sc.next();
                    while(!continued.equals("1") && !continued.equals("0")){
                        System.out.println("Please enter 1 for continue and 0 for exit");
                        continued = sc.next();
                    }
                    //exit the program
                    if(continued.equals("0")){
                        System.out.println("Hope we meet your expectations. Enjoy your day!");
                        System.exit(0);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number");
                } finally{
                    printOptions();
                }
            }
        }
    }

}
