package com.otoniel.g6pd.g6pddeficiencyapp;

import com.otoniel.g6pd.g6pddeficiencyapp.data.repository.AlimentsRepository;
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
    AlimentsRepository provideRepository(LocalAlimentsDataSource localDataSource, RemoteAlimentsDataSource remoteDataSource) {
        return new AlimentsRepositoryImpl(localDataSource, remoteDataSource);
    }

    @Provides
    LocalAlimentsDataSource provideLocalAlimentsDataSource() {
        return new LocalAlimentsDataSource();
    }

    @Provides
    RemoteAlimentsDataSource provideRemoteAlimentsDataSource() {
        return new RemoteAlimentsDataSource();
    }
}
