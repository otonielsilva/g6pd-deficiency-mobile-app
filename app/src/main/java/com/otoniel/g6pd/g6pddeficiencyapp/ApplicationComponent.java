package com.otoniel.g6pd.g6pddeficiencyapp;

import com.otoniel.g6pd.g6pddeficiencyapp.newRestriction.NewRestrictionFragment;

import dagger.Component;

/**
 * Created by eltonjhony on 08/07/17.
 */
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(NewRestrictionFragment fragment);
}
