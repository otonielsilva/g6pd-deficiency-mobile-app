package com.otoniel.g6pd.g6pddeficiencyapp.data.local;

import com.otoniel.g6pd.g6pddeficiencyapp.data.AlimentsDataSource;
import com.otoniel.g6pd.g6pddeficiencyapp.data.model.Aliment;

import io.realm.Realm;

/**
 * Created by eltonjhony on 08/07/17.
 */
public class LocalAlimentsDataSource implements AlimentsDataSource {

    @Override
    public void save(final Aliment aliment) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealm(aliment));
        realm.close();
    }
}
