package com.otoniel.g6pd.g6pddeficiencyapp.newRestriction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.otoniel.g6pd.g6pddeficiencyapp.Manifest;
import com.otoniel.g6pd.g6pddeficiencyapp.MyApplication;
import com.otoniel.g6pd.g6pddeficiencyapp.R;
import com.otoniel.g6pd.g6pddeficiencyapp.common.provider.GenericFileProvider;
import com.otoniel.g6pd.g6pddeficiencyapp.mainListing.MainListingActivity;
import com.otoniel.g6pd.g6pddeficiencyapp.utils.PermissionsUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.app.Activity.RESULT_OK;
import static com.otoniel.g6pd.g6pddeficiencyapp.utils.PermissionsUtil.PERMISSIONS_GRANTED;

/**
 * Created by eltonjhony on 08/07/17.
 */

public class NewRestrictionFragment extends Fragment implements NewRestrictionContract.View {

    private static final int PICK_PHOTO_RESULT_CODE = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 201;

    @InjectView(R.id.aliment_text) EditText alimentTxt;
    @InjectView(R.id.new_photo_image) ImageView newPhotoImg;
    @InjectView(R.id.gallery_image) ImageView galleryImg;
    @InjectView(R.id.save_button) Button saveBtn;
    @InjectView(R.id.progressBar) ProgressBar progress;
    @InjectView(R.id.selectedImage) ImageView selectedImg;

    @Inject NewRestrictionPresenter mPresenter;

    private Uri photoUri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);
        this.mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_new_restriction, container, false);
        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void navigateToMainListing() {
        Intent mainListingIntent = new Intent(getActivity(), MainListingActivity.class);
        getActivity().startActivity(mainListingIntent);
    }

    @Override
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_RESULT_CODE);
    }

    @Override
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoUri = GenericFileProvider.createUriFromFile(getActivity());
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void setSelectedImage(Intent data, boolean fromGallery) {
        if (fromGallery) {
            photoUri = data.getData();
        }
        selectedImg.setImageURI(photoUri);
        selectedImg.setVisibility(View.VISIBLE);

        galleryImg.setEnabled(false);
        newPhotoImg.setEnabled(false);
        galleryImg.setImageResource(R.drawable.ic_crop_original_disabled_96dp);
        newPhotoImg.setImageResource(R.drawable.ic_add_a_photo_disabled_96dp);
    }

    @Override
    public void clearPhotoSelection() {
        selectedImg.setImageURI(null);
        selectedImg.setVisibility(View.GONE);
        photoUri = null;
        galleryImg.setEnabled(true);
        newPhotoImg.setEnabled(true);
        galleryImg.setImageResource(R.drawable.ic_crop_original_white_96dp);
        newPhotoImg.setImageResource(R.drawable.ic_add_a_photo_white_96dp);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoading(boolean enable) {
        saveBtn.setEnabled(!enable);
        progress.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_PHOTO_RESULT_CODE: {
                if (resultCode == RESULT_OK) {
                    setSelectedImage(data, true);
                }
                break;
            }
            case REQUEST_IMAGE_CAPTURE: {
                if (resultCode == RESULT_OK) {
                    setSelectedImage(data, false);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_GRANTED:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
        }

    }

    private void setListeners() {
        saveBtn.setOnClickListener(v -> {

            String aliment = alimentTxt.getText().toString();
            mPresenter.save(photoUri != null ? photoUri.toString() : null, aliment);
        });

        newPhotoImg.setOnClickListener(v -> PermissionsUtil.requestPermissions(getActivity(), new PermissionsUtil.PermissionGranted() {
            @Override
            public void onExecute() {
                dispatchTakePictureIntent();
            }
            @Override
            public void onExplanation(String permission) {
            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE));

        galleryImg.setOnClickListener(v -> PermissionsUtil.requestPermissions(getActivity(), new PermissionsUtil.PermissionGranted() {
            @Override
            public void onExecute() {
                openGallery();
            }
            @Override
            public void onExplanation(String permission) {
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE));

        selectedImg.setOnLongClickListener(v -> {
            clearPhotoSelection();
            return true;
        });
    }
}
