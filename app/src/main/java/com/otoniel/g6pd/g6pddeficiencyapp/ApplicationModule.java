package com.otoniel.g6pd.g6pddeficiencyapp;

import com.otoniel.g6pd.g6pddeficiencyapp.data.repository.AlimentsRepositoryImpl;
import com.otoniel.g6pd.g6pddeficiencyapp.data.local.LocalAlimentsDataSource;
import com.otoniel.g6pd.g6pddeficiencyapp.data.remote.RemoteAlimentsDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eltonjhony on 08/07/17.
 */
@Module
public class ApplicationModule {

    @Provides
    AlimentsRepositoryImpl provideRepository() {
        return new AlimentsRepositoryImpl(new LocalAlimentsDataSource(), new RemoteAlimentsDataSource());
    }
}
