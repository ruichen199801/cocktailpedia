import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RecipeTest {

    private Recipe recipe;

    @Before
    public void init() {
        List<String> ingredients = Arrays.asList
                ("gin", "dry vermouth", "olive");
        recipe = new Recipe("RuichenTini", ingredients, "stir");
    }

    @Test
    public void testGetStyle() {
        assertEquals("stir", recipe.getStyle());
    }

}
