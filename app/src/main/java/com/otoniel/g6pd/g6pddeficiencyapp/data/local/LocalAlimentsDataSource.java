package com.otoniel.g6pd.g6pddeficiencyapp.data.local;

import com.otoniel.g6pd.g6pddeficiencyapp.data.AlimentsDataSource;
import com.otoniel.g6pd.g6pddeficiencyapp.data.local.entity.AlimentEntity;
import com.otoniel.g6pd.g6pddeficiencyapp.data.model.Aliment;

import io.realm.Realm;
import rx.Observable;

import static com.otoniel.g6pd.g6pddeficiencyapp.utils.ActivityUtil.getGlobalContext;

/**
 * Created by eltonjhony on 08/07/17.
 */
public class LocalAlimentsDataSource implements AlimentsDataSource {

    @Override
    public Observable<Aliment> save(String photo, String foodName) {
        Realm realm = Realm.getInstance(getGlobalContext());
        realm.beginTransaction();
        AlimentEntity aliment = realm.createObject(AlimentEntity.class);
        aliment.setPhotoUri(photo);
        aliment.setFoodName(foodName);
        realm.commitTransaction();

        return Observable.just(new Aliment(aliment));
    }
}
