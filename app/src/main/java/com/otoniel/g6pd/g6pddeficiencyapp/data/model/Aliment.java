package com.otoniel.g6pd.g6pddeficiencyapp.data.model;

import com.google.gson.annotations.SerializedName;
import com.otoniel.g6pd.g6pddeficiencyapp.data.local.entity.AlimentEntity;

/**
 * Created by eltonjhony on 08/07/17.
 */

public class Aliment {

    @SerializedName("photoUri")
    private String photoUri;

    @SerializedName("foodName")
    private String foodName;

    public Aliment() {
    }

    public Aliment(AlimentEntity aliment) {
        this.photoUri = aliment.getPhotoUri();
        this.foodName = aliment.getFoodName();
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
