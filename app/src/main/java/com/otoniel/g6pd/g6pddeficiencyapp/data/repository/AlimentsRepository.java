package com.otoniel.g6pd.g6pddeficiencyapp.data.repository;

/**
 * Created by eltonjhony on 09/07/17.
 */

public interface AlimentsRepository {

    interface SaveAlimentsCallback {
        void onSuccess();

        void onError(String message);
    }

    void save(String photo, String foodName, final AlimentsRepositoryImpl.SaveAlimentsCallback callback);
}
