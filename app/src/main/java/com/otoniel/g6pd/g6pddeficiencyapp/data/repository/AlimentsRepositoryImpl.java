package com.otoniel.g6pd.g6pddeficiencyapp.data.repository;

import com.otoniel.g6pd.g6pddeficiencyapp.data.exception.InvalidFormException;
import com.otoniel.g6pd.g6pddeficiencyapp.data.local.LocalAlimentsDataSource;
import com.otoniel.g6pd.g6pddeficiencyapp.data.model.Aliment;
import com.otoniel.g6pd.g6pddeficiencyapp.data.remote.RemoteAlimentsDataSource;
import com.otoniel.g6pd.g6pddeficiencyapp.utils.StringUtil;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.otoniel.g6pd.g6pddeficiencyapp.ApplicationMessages.getFieldRequiredMessage;

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
    public Subscription save(String photo, String foodName, final SaveAlimentsCallback callback)
            throws InvalidFormException {

        validateRequiredFields(photo, foodName);

        return localDataSource.save(photo, foodName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Aliment>>() {
                    @Override
                    public Observable<? extends Aliment> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Observer<Aliment>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Aliment aliment) {
                        callback.onSuccess();
                    }
                });
    }

    private void validateRequiredFields(String photo, String foodName) throws InvalidFormException {
        if (StringUtil.stringIsNullOrEmpty(photo)) {
            throw new InvalidFormException(getFieldRequiredMessage("food photo"));
        }

        if (StringUtil.stringIsNullOrEmpty(foodName)) {
            throw new InvalidFormException(getFieldRequiredMessage("food name"));
        }
    }
}
