package com.example.vladislav.numberlist.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vladislav.numberlist.InfoActivity;
import com.example.vladislav.numberlist.contacts.Contact;
import com.example.vladislav.numberlist.contacts.ContactsProvider;
import com.example.vladislav.numberlist.listeners.ItemLongClickListener;
import com.example.vladislav.numberlist.NewUserActivity;
import com.example.vladislav.numberlist.R;

import java.util.List;


public class NumberFragment extends android.support.v4.app.Fragment{

    int position;
    boolean isBigMode;
    LayoutInflater inflater;
    RecyclerView rv;

    public static android.support.v4.app.Fragment newInstance(int position, boolean isBigMode){
        NumberFragment fragment = new NumberFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        bundle.putBoolean("bigmode", isBigMode);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("pos");
            isBigMode = getArguments().getBoolean("bigmode");
        }else {
            position = 0;
            isBigMode = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        final View view = inflater.inflate(R.layout.fragment_pager_list, container, false);
        rv = (RecyclerView)view.findViewById(R.id.list_number);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListAdapter listAdapter = new ListAdapter(position, inflater);
        listAdapter.setBigMode(isBigMode);
        rv.setAdapter(listAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button addNew = (Button) view.findViewById(R.id.add_new);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewUserActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(ContactsProvider.MY_TAG, "This is NumberFragment, onActivityResult");
        updateList();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void updateList(){
        ((ListAdapter)rv.getAdapter()).setContacts();
    }

    public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        int position;
        boolean isBigMode;
        LayoutInflater inflater;
        List<Contact> contacts;

        ItemLongClickListener itemLongClickListener;

        public ListAdapter(int position, LayoutInflater inflater) {
            this.position = position;
            this.inflater = inflater;
            if (position == 0){
                this.contacts = ContactsProvider.getInstance().getContacts();
                notifyDataSetChanged();
            }else {
                this.contacts = ContactsProvider.getInstance().getDeletedContacts();
            }
            if (position == 0) {
                setItemClickListener();
            }
        }

        public void setBigMode(boolean bigMode) {
            isBigMode = bigMode;
        }

        public void setItemClickListener() {
            this.itemLongClickListener = new ItemLongClickListener() {
                @Override
                public void onItemLongClick(int position) {
                    DeleteContactFragment dialogFragment = new DeleteContactFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pos", position);
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(getActivity().getSupportFragmentManager(), DeleteContactFragment.class.getName());
                }
            };
        }

        public void setContacts(){
            if (position ==0) {
                this.contacts = ContactsProvider.getInstance().getContacts();
            }else {
                this.contacts = ContactsProvider.getInstance().getDeletedContacts();
            }
            Log.d(ContactsProvider.MY_TAG, "This is setContacts");
            Log.d(ContactsProvider.MY_TAG, "This is contacts size: " + this.contacts.size());
            Log.d(ContactsProvider.MY_TAG, "This is ContactsProvider contacts size: " + ContactsProvider.getInstance().getContacts().size());
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.number_item, parent, false);
            return new ListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof ListViewHolder){
                ((ListViewHolder) holder).getTvNumber().setText(contacts.get(position).getUsername());
                if (position == 0) {
                    ((ListViewHolder) holder).getItemView().setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            itemLongClickListener.onItemLongClick(position);
                            return false;
                        }
                    });
                    ((ListViewHolder) holder).getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isBigMode){

                                InfoFragment fragment = new InfoFragment();

                                Bundle bundle = new Bundle();
                                bundle.putString("name", contacts.get(position).getUsername());
                                bundle.putString("number", contacts.get(position).getPhoneNumber());

                                fragment.setArguments(bundle);

                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.info_container, fragment, InfoFragment.class.getName())
                                        .commit();

                            }else {
                                Intent intent = new Intent(getActivity(), InfoActivity.class);
                                intent.putExtra("name", contacts.get(position).getUsername());
                                intent.putExtra("number", contacts.get(position).getPhoneNumber());
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }
    }

    private class ListViewHolder extends RecyclerView.ViewHolder{

        TextView tvNumber;
        View itemView;

        public ListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvNumber = (TextView) itemView.findViewById(R.id.number_list);
        }

        public TextView getTvNumber() {
            return tvNumber;
        }

        public View getItemView() {
            return itemView;
        }
    }
}
