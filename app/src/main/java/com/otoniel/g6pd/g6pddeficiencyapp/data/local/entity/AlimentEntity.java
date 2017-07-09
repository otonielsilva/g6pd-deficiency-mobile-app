package com.otoniel.g6pd.g6pddeficiencyapp.data.local.entity;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by eltonjhony on 08/07/17.
 */

public class AlimentEntity extends RealmObject {

    @Index
    private String id;

    private String photoUri;

    private String foodName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
