import java.util.List;

/**
 *
 */

public class Cocktail {

    private String drink;

    private String category;

    private String glassware;

    private List<String> ingredients;

    private String taste;

    private String preparation;

    public Cocktail(String drink) {
        this.drink = drink;
    }

    public Cocktail(String drink,
                    String category,
                    String glassware,
                    List<String> ingredients,
                    String taste,
                    String preparation) {
        this.drink = drink;
        this.category = category;
        this.glassware = glassware;
        this.ingredients = ingredients;
        this.taste = taste;
        this.preparation = preparation;
    }

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

}
