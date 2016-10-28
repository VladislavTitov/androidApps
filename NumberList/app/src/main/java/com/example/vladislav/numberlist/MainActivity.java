package com.example.vladislav.numberlist;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.vladislav.numberlist.contacts.ContactsProvider;
import com.example.vladislav.numberlist.fragments.DeleteContactFragment;
import com.example.vladislav.numberlist.fragments.NumberFragment;

public class MainActivity extends AppCompatActivity implements DeleteContactFragment.DeleteContactListener{

    static final int PAGE_NUM = 2;

    ViewPager viewPager;
    PageAdapter adapter;
    boolean isBigMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactsProvider.getInstance().restoreContacts(getApplicationContext());

        if (findViewById(R.id.info_container) != null){
            isBigMode = true;
        }

        viewPager = (ViewPager) findViewById(R.id.list_container);
        adapter = new PageAdapter(getSupportFragmentManager(), isBigMode);
        adapter.notifyDataSetChanged();

        viewPager.setAdapter(adapter);

    }

    @Override
    public void onDialogPositiveClick() {
        Log.d(ContactsProvider.MY_TAG, "Dialog Positive Click");
        ((NumberFragment)adapter.getItem(0)).updateList();
        ((NumberFragment)adapter.getItem(1)).updateList();
    }

    private class PageAdapter extends FragmentStatePagerAdapter {

        NumberFragment contactFragment;
        NumberFragment deletedContactFragment;

        public PageAdapter(FragmentManager fm, boolean isBigMode) {
            super(fm);
            contactFragment = (NumberFragment) NumberFragment.newInstance(0, isBigMode);
            deletedContactFragment = (NumberFragment) NumberFragment.newInstance(1, isBigMode);
            notifyDataSetChanged();
        }



        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if (position == 0){
                return contactFragment;
            }else {
                return deletedContactFragment;
            }
        }

        @Override
        public int getCount() {
            return PAGE_NUM;
        }

    }
}
