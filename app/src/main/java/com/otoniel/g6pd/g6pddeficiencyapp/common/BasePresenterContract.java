package com.otoniel.g6pd.g6pddeficiencyapp.common;

import android.support.annotation.NonNull;

/**
 * Created by eltonjhony on 02/05/17.
 */

public interface BasePresenterContract<V> {

    void attachView(@NonNull V view);

    V getViewOrThrow();

    V getView();

    void detachView();

    boolean isViewAttached();
}
