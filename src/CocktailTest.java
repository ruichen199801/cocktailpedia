import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * The test class for cocktail.
 */

public class CocktailTest {

    private Cocktail cocktail;

    @Before
    public void init() {
        List<String> ingredients = Arrays.asList
                ("gin", "dry vermouth", "olive");
        cocktail = new Cocktail("martini", "cocktail", "Highball glass",
                ingredients, "bitter", "stir", 18);
    }

    @Test
    public void testGetDrink() {
        assertEquals("martini", cocktail.getDrink());
    }

    @Test
    public void testGetTaste() {
        assertEquals("bitter", cocktail.getTaste());
    }

    @Test
    public void testGetCategory() {
        assertEquals("cocktail", cocktail.getCategory());
    }

    @Test
    public void testSetCategory() {
        cocktail.setCategory("shake");
        assertEquals("shake", cocktail.getCategory());
    }

    @Test
    public void testGetGlassware() {
        assertEquals("Highball glass", cocktail.getGlassware());
    }

    @Test
    public void testGetPreparation() {
        assertEquals("stir", cocktail.getPreparation());
    }

    @Test
    public void testGetIngredients() {
        List<String> expected = Arrays.asList
                ("gin", "dry vermouth", "olive");
        assertEquals(expected, cocktail.getIngredients());
    }

    @Test
    public void testGetPrice() {
        assertEquals(18, cocktail.getPrice());
    }

}