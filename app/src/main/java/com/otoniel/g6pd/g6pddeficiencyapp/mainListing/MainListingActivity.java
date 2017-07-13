package com.otoniel.g6pd.g6pddeficiencyapp.mainListing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.otoniel.g6pd.g6pddeficiencyapp.R;
import com.otoniel.g6pd.g6pddeficiencyapp.common.BaseActivity;
import com.otoniel.g6pd.g6pddeficiencyapp.newRestriction.NewRestrictionActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by eltonjhony on 08/07/17.
 */

public class MainListingActivity extends BaseActivity {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.fab) FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listing);
        ButterKnife.inject(this);

        setListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @Override
    protected void updateToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setListeners() {
        fab.setOnClickListener(v -> {
            Intent newRestrictionIntent = new Intent(MainListingActivity.this, NewRestrictionActivity.class);
            MainListingActivity.this.startActivity(newRestrictionIntent);
        });
    }
}
