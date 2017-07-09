package com.otoniel.g6pd.g6pddeficiencyapp.data;

import com.otoniel.g6pd.g6pddeficiencyapp.data.model.Aliment;

import rx.Observable;

/**
 * Created by eltonjhony on 08/07/17.
 */

public interface AlimentsDataSource {

    Observable<Aliment> save(String photo, String foodName);
}
