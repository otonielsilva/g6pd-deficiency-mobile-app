package com.otoniel.g6pd.g6pddeficiencyapp.newRestriction;

import com.otoniel.g6pd.g6pddeficiencyapp.common.BasePresenter;
import com.otoniel.g6pd.g6pddeficiencyapp.data.repository.AlimentsRepository;
import com.otoniel.g6pd.g6pddeficiencyapp.data.repository.AlimentsRepository.SaveAlimentsCallback;

import javax.inject.Inject;

/**
 * Created by eltonjhony on 08/07/17.
 */
public class NewRestrictionPresenter extends BasePresenter<NewRestrictionContract.View>
    implements NewRestrictionContract.Presenter {

    private AlimentsRepository mRepository;

    @Inject
    public NewRestrictionPresenter(AlimentsRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void save(String photo, String name) {

        getView().setLoading(true);

        mRepository.save(photo, name, new SaveAlimentsCallback() {
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

    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
