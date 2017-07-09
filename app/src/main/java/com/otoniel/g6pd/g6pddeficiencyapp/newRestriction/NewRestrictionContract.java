package com.otoniel.g6pd.g6pddeficiencyapp.newRestriction;

import android.content.Intent;

/**
 * Created by eltonjhony on 08/07/17.
 */

public interface NewRestrictionContract {

    interface View {

        void navigateToMainListing();

        void openGallery();

        void dispatchTakePictureIntent();

        void setSelectedImage(Intent data, boolean fromGallery);

        void clearPhotoSelection();

        void showError(String message);

        void setLoading(boolean enable);
    }

    interface Presenter {

        void save(String photo, String foodName);
    }
}
