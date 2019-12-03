package DGU.OSSP.fall2019.PersonalTrainer.Classes;
import org.json.JSONException;
import org.json.JSONObject;

public class Ingredient {

    private JSONObject value;

    public Ingredient (JSONObject in) {
        value = in;
    }

    public String text () {
        String s;
        try {
            s = value.getString("text");
        } catch (JSONException e){
            s = "";
        }
        return s;
    }

    public double quantity () { return value.optDouble("quantity"); }

    public String measure () {
        String s;
        try {
            s = value.getString("measure");
        } catch (JSONException e){
            s = "";
        }
        return s;
    }

    public String food () {
        String s;
        try {
            s = value.getString("food");
        } catch (JSONException e){
            s = "";
        }
        return s;
    }

    public double weight () { return value.optDouble("weight"); }

}
