package com.otoniel.g6pd.g6pddeficiencyapp.data.remote;

import com.otoniel.g6pd.g6pddeficiencyapp.data.AlimentsDataSource;
import com.otoniel.g6pd.g6pddeficiencyapp.data.model.Aliment;

import rx.Observable;

/**
 * Created by eltonjhony on 08/07/17.
 */

public class RemoteAlimentsDataSource implements AlimentsDataSource {

    @Override
    public Observable<Aliment> save(String photo, String foodName) {
        //TODO not have remote repository yet
        return Observable.just(new Aliment());
    }
}
