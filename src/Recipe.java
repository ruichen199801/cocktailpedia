import java.util.List;

/**
 * The class for a user-customized cocktail recipe.
 */

public class Recipe {

    /**
     * drink: name of the drink
     * e.g. Ruichen Fashioned
     */
    private String drink;

    /**
     * ingredients: a list of ingredients of the drink
     * e.g. Rum + Cranberry Juice + Pineapple Juice
     */
    private List<String> ingredients;

    /**
     * style: preparation style of the drink
     * e.g.  shake
     */
    private String style;

    /**
     * constructor
     */

    public Recipe(String drink, List<String> ingredients, String style) {
        this.drink = drink;
        this.ingredients = ingredients;
        this.style = style;
    }

    /**
     * getters and setters
     */

    public String getDrink() {
        return drink;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getStyle() {
        return style;
    }

}
