package com.otoniel.g6pd.g6pddeficiencyapp.data.repository;

import com.otoniel.g6pd.g6pddeficiencyapp.data.exception.InvalidFormException;
import com.otoniel.g6pd.g6pddeficiencyapp.data.local.LocalAlimentsDataSource;
import com.otoniel.g6pd.g6pddeficiencyapp.data.model.Aliment;
import com.otoniel.g6pd.g6pddeficiencyapp.data.remote.RemoteAlimentsDataSource;
import com.otoniel.g6pd.g6pddeficiencyapp.utils.StringUtil;

import static com.otoniel.g6pd.g6pddeficiencyapp.ApplicationMessages.getFieldRequiredMessage;
import static com.otoniel.g6pd.g6pddeficiencyapp.ApplicationMessages.getUnexpectedErrorMessage;

/**
 * Created by eltonjhony on 08/07/17.
 */
public class AlimentsRepositoryImpl implements AlimentsRepository {

    private final LocalAlimentsDataSource localDataSource;
    private final RemoteAlimentsDataSource remoteDataSource; // We are not using remote dataSource yet

    public AlimentsRepositoryImpl(LocalAlimentsDataSource localDataSource, RemoteAlimentsDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void save(String photo, String foodName, final SaveAlimentsCallback callback) {

        try {

            this.validateRequiredFields(foodName);
            this.localDataSource.save(new Aliment(photo, foodName));
            callback.onSuccess();

        } catch (InvalidFormException e) {
            callback.onError(e.getMessage());
        } catch (Exception e) {
            callback.onError(getUnexpectedErrorMessage());
        }

    }

    private void validateRequiredFields(String foodName) throws InvalidFormException {
        if (StringUtil.stringIsNullOrEmpty(foodName)) {
            throw new InvalidFormException(getFieldRequiredMessage("food name"));
        }
    }
}
