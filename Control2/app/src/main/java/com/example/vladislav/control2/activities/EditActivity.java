package com.example.vladislav.control2.activities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.vladislav.control2.R;
import com.example.vladislav.control2.callbacks.EditAsyncCallback;
import com.example.vladislav.control2.fragments.EditAsyncFragment;

public class EditActivity extends AppCompatActivity implements EditAsyncCallback{

    public static final int OK_RESULT_CODE = 1;
    public static final int CANCEL_RESULT_CODE = 0;

    EditText name;
    EditText content;
    ProgressBar progressBar;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        name = (EditText) findViewById(R.id.newName);
        content = (EditText) findViewById(R.id.newContent);
        progressBar = (ProgressBar) findViewById(R.id.progress_edit);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        if (savedInstanceState != null){
            if (savedInstanceState.getInt("visibility") == ProgressBar.VISIBLE)
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override   
            public void onClick(View v) {
                setResult(CANCEL_RESULT_CODE);
                finish();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("visibility", progressBar.getVisibility());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void setProgress(boolean isVisible) {
        int visibility = isVisible ? ProgressBar.VISIBLE : ProgressBar.GONE;
        progressBar.setVisibility(visibility);
    }

    public EditAsyncFragment getFragment(){
        FragmentManager manager = getSupportFragmentManager();
        EditAsyncFragment fragment = (EditAsyncFragment) manager.findFragmentByTag(EditAsyncFragment.class.getName());

        if (fragment == null){
            fragment = new EditAsyncFragment();
            manager.beginTransaction()
                    .add(fragment, EditAsyncFragment.class.getName())
                    .commit();
        }
        return fragment;
    }

    @Override
    public void closeActivity() {
        Intent intent = new Intent();
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("content", content.getText().toString());
        setResult(OK_RESULT_CODE, intent);
        finish();
    }
}
