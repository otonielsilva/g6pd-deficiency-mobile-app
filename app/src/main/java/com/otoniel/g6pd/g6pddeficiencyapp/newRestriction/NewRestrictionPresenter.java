package com.otoniel.g6pd.g6pddeficiencyapp.newRestriction;

import com.otoniel.g6pd.g6pddeficiencyapp.common.BasePresenter;
import com.otoniel.g6pd.g6pddeficiencyapp.data.exception.InvalidFormException;
import com.otoniel.g6pd.g6pddeficiencyapp.data.repository.AlimentsRepository.SaveAlimentsCallback;
import com.otoniel.g6pd.g6pddeficiencyapp.data.repository.AlimentsRepositoryImpl;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eltonjhony on 08/07/17.
 */
public class NewRestrictionPresenter extends BasePresenter<NewRestrictionContract.View>
    implements NewRestrictionContract.Presenter {

    private AlimentsRepositoryImpl mRepository;

    private CompositeSubscription mSubscriptions;

    @Inject
    public NewRestrictionPresenter(AlimentsRepositoryImpl repository) {
        this.mRepository = repository;
        this.mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void save(String photo, String name) {

        getView().setLoading(true);

        try {
            Subscription subscription = mRepository.save(photo, name, new SaveAlimentsCallback() {
                @Override
                public void onSuccess() {
                    getViewOrThrow().setLoading(false);
                    getViewOrThrow().navigateToMainListing();
                }

                @Override
                public void onError(String message) {
                    getViewOrThrow().setLoading(false);
                    getViewOrThrow().showError(message);
                }
            });
            mSubscriptions.add(subscription);
        } catch (InvalidFormException e) {
            getViewOrThrow().setLoading(false);
            getViewOrThrow().showError(e.getMessage());
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscriptions != null) {
            mSubscriptions.unsubscribe();
        }
    }
}
