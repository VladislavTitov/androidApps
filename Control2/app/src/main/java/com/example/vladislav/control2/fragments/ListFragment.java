package com.example.vladislav.control2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vladislav.control2.R;
import com.example.vladislav.control2.activities.MainActivity;
import com.example.vladislav.control2.models.Note;
import com.example.vladislav.control2.observe.Observer;
import com.example.vladislav.control2.providers.NotesProvider;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView list;
    ListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = (RecyclerView) view.findViewById(R.id.list);
        adapter = new ListAdapter();
        NotesProvider.getInstance().registerObserver(adapter);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Observer{

        ArrayList<Note> notes;

        public ListAdapter() {
            setNotes();
        }

        public void setNotes() {
            this.notes = NotesProvider.getInstance().getNotesList();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_layout, parent, false);
            return new ListItemHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof ListItemHolder) {
                ((ListItemHolder) holder).getItemView()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditFragment fragment = new EditFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("name", notes.get(position).getName());
                                bundle.putString("content", notes.get(position).getContent());
                                bundle.putInt("position", position);
                                fragment.setArguments(bundle);

                                if (!((MainActivity)getActivity()).isBigMode()) {
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.list_fragment_container, fragment, EditFragment.class.getName())
                                            .addToBackStack("toEditFragment")
                                            .commit();
                                }else {
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.edit_fragment_container, fragment, EditFragment.class.getName())
                                            .commit();
                                }
                            }
                        });
                ((ListItemHolder) holder).getItemName().setText(notes.get(position).getName());
                ((ListItemHolder) holder).getItemContent().setText(notes.get(position).getContent());
            }
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }

        @Override
        public void update(ArrayList<Note> notes) {
            this.notes = notes;
            notifyDataSetChanged();
        }
    }

    private class ListItemHolder extends RecyclerView.ViewHolder{

        private View itemView;
        private TextView itemName;
        private TextView itemContent;
        public ListItemHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.itemName = (TextView) itemView.findViewById(R.id.item_name);
            this.itemContent = (TextView) itemView.findViewById(R.id.item_content);
        }

        public View getItemView() {
            return itemView;
        }

        public TextView getItemName() {
            return itemName;
        }

        public TextView getItemContent() {
            return itemContent;
        }
    }
}
