/**
 *
 */

public class Recipe {

    private String drink;

    private String ingredients;

    private String style;

    public Recipe(String drink) {
        this.drink = drink;
    }

    public Recipe(String drink, String ingredients, String style) {
        this.drink = drink;
        this.ingredients = ingredients;
        this.style = style;
    }

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
