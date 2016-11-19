package com.example.vladislav.control2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.vladislav.control2.R;
import com.example.vladislav.control2.fragments.ListFragment;
import com.example.vladislav.control2.providers.NotesProvider;

public class MainActivity extends AppCompatActivity {

    boolean isBigMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.edit_fragment_container) != null){
            isBigMode = true;
        }else{
            isBigMode = false;
        }
        ListFragment fragment = new ListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_fragment_container, fragment, ListFragment.class.getName())
                .commit();
        NotesProvider.getInstance().restoreNotes(this);
    }

    public boolean isBigMode() {
        return isBigMode;
    }
}
