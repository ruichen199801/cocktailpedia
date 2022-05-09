import java.util.List;

/**
 * The class for a user-customized cocktail recipe.
 */

public class Recipe {

    /**
     * drink: name of the drink, e.g. Ruichen Fashioned
     */
    private final String drink;

    /**
     * ingredients: a list of ingredients of the drink, e.g. Rum + Cranberry Juice + Pineapple Juice
     */
    private final List<String> ingredients;

    /**
     * style: preparation style of the drink, e.g.  shake
     */
    private final String style;

    /**
     * constructor for recipe
     */
    public Recipe(String drink, List<String> ingredients, String style) {
        this.drink = drink;
        this.ingredients = ingredients;
        this.style = style;
    }

    /**
     * getter method for drink
     */
    public String getDrink() {
        return drink;
    }

    /**
     * getter method for ingredients
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /**
     * getter method for style
     */
    public String getStyle() {
        return style;
    }

}
