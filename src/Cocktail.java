import java.util.List;

/**
 * The class for a detailed cocktail recipe derived from the dataset.
 */

public class Cocktail {

    /**
     * drink: name of the drink
     * e.g. Bloody Mary
     */
    private String drink;

    /**
     * category: type of the drink
     * e.g. shot
     */
    private String category;

    /**
     * glassware: glass type used for the drink
     * e.g. Highball glass
     */
    private String glassware;

    /**
     * ingredients: a list of ingredients of the drink
     * e.g. Vodka + Triple Sec + Lemon Juice
     */
    private List<String> ingredients;

    /**
     * taste: taste of the drink
     * e.g. sweet
     */
    private String taste;

    /**
     * category: text instructions on how to make the drink
     * e.g. Pour the vodka, dry vermouth and olive brine into a cocktail shaker with a handful of ice and shake well.
     */
    private String preparation;

    /**
     * price: price of the drink
     * e.g. 13
     */
    private int price;


    /**
     * constructor
     */

    public Cocktail(String drink) {
        this.drink = drink;
    }

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
     * getters and setters
     */

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGlassware() {
        return glassware;
    }

    public void setGlassware(String glassware) {
        this.glassware = glassware;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
