package com.otoniel.g6pd.g6pddeficiencyapp.newRestriction;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.otoniel.g6pd.g6pddeficiencyapp.R;
import com.otoniel.g6pd.g6pddeficiencyapp.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewRestrictionActivity extends BaseActivity {

    @InjectView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restriction);
        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @Override
    protected void updateToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.restrictions_toolbar_name);
    }
}
