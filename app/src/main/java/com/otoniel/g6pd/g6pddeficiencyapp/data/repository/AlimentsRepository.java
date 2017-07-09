package com.otoniel.g6pd.g6pddeficiencyapp.data.repository;

import com.otoniel.g6pd.g6pddeficiencyapp.data.exception.InvalidFormException;

import rx.Subscription;

/**
 * Created by eltonjhony on 09/07/17.
 */

public interface AlimentsRepository {

    interface SaveAlimentsCallback {
        void onSuccess();

        void onError(String message);
    }

    Subscription save(String photo, String foodName, final AlimentsRepositoryImpl.SaveAlimentsCallback callback) throws InvalidFormException;
}
