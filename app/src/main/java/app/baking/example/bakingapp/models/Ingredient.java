
package app.baking.example.bakingapp.models;

import java.io.Serializable;

public class Ingredient implements Serializable
{
    private float quantity;
    private String measure;
    private String ingredient;
    private final static long serialVersionUID = -792708413248188285L;

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

}
