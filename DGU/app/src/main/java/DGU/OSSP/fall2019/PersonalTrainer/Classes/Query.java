package DGU.OSSP.fall2019.PersonalTrainer.Classes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Query {

    private JSONArray value;

    public Query (JSONArray resultsInput) {
        value = resultsInput;
    }

    public Query () {
        this.value = null;
    }

    public Recipe getRecipeAtIndex(int index) {
        JSONObject recipe;
        try {
            recipe = value.getJSONObject(index).getJSONObject("recipe");
        } catch (JSONException e) {
            recipe = null;
        }
        Recipe recipeToReturn = new Recipe(recipe);
        return recipeToReturn;
    }

    public JSONArray getValue() {
        return this.value;
    }
}
