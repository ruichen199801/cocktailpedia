import java.util.List;

/**
 * The class for a detailed cocktail recipe derived from the dataset.
 */

public class Cocktail {

    /**
     * drink: name of the drink, e.g. Bloody Mary
     */
    private final String drink;

    /**
     * category: type of the drink, e.g. shot
     */
    private String category;

    /**
     * glassware: glass type used for the drink, e.g. Highball glass
     */
    private String glassware;

    /**
     * ingredients: a list of ingredients of the drink, e.g. Vodka + Triple Sec + Lemon Juice
     */
    private List<String> ingredients;

    /**
     * taste: taste of the drink, e.g. sweet
     */
    private String taste;

    /**
     * category: text instructions on how to make the drink, e.g. Pour the vodka, dry vermouth and olive brine into a cocktail shaker with a handful of ice and shake well.
     */
    private String preparation;

    /**
     * price: price of the drink, e.g. 13
     */
    private int price;

    /**
     * constructor for cocktail
     */
    public Cocktail(String drink) {
        this.drink = drink;
    }

    /**
     * constructor for cocktail
     */
    public Cocktail(String drink,
                    String category,
                    String glassware,
                    List<String> ingredients,
                    String taste,
                    String preparation,
                    int price) {
        this.drink = drink;
        this.category = category;
        this.glassware = glassware;
        this.ingredients = ingredients;
        this.taste = taste;
        this.preparation = preparation;
        this.price = price;
    }

    /**
     * getter method for drink
     */
    public String getDrink() {
        return drink;
    }

    /**
     * getter method for category
     */
    public String getCategory() {
        return category;
    }

    /**
     * setter method for category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * getter method for glassware
     */
    public String getGlassware() {
        return glassware;
    }

    /**
     * getter method for ingredients
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /**
     * getter method for taste
     */
    public String getTaste() {
        return taste;
    }

    /**
     * getter method for preparation
     */
    public String getPreparation() {
        return preparation;
    }

    /**
     * getter method for price
     */
    public int getPrice() {
        return price;
    }

}
