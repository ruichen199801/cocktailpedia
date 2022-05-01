import java.util.List;

/**
 *
 */

public class User {

    private String username;

    private String preference;

    private List<Recipe> recipes;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String preference, List<Recipe> recipes) {
        this.username = username;
        this.preference = preference;
        this.recipes = recipes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

}
