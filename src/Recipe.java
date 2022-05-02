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
    private String ingredients;

    /**
     * style: preparation style of the drink
     * e.g. shake
     */
    private String style;

    /**
     * constructor
     */

    public Recipe(String drink) {
        this.drink = drink;
    }

    public Recipe(String drink, String ingredients, String style) {
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

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}
