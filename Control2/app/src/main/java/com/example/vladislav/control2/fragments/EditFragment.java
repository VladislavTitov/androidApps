package com.example.vladislav.control2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vladislav.control2.R;
import com.example.vladislav.control2.activities.EditActivity;
import com.example.vladislav.control2.providers.NotesProvider;

public class EditFragment extends Fragment {

    public static final int EDIT_REQUEST_CODE = 1;

    TextView name;
    TextView content;
    Button btnToEdit;

    String nameText;
    String contentText;
    int position;

    View root;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            nameText = getArguments().getString("name");
            contentText = getArguments().getString("content");
            position = getArguments().getInt("position");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        root = view;
        name = (TextView) view.findViewById(R.id.name_edit_fragment);
        content = (TextView) view.findViewById(R.id.content_edit_fragment);
        btnToEdit = (Button) view.findViewById(R.id.toEditActivity);
        name.setText(nameText);
        content.setText(contentText);
        btnToEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                intent.putExtra("name", nameText);
                intent.putExtra("content", contentText);
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_REQUEST_CODE){

            if (resultCode == EditActivity.OK_RESULT_CODE) {

                if (data.getExtras() != null) {

                    String newName;
                    if (!data.getStringExtra("name").equals("")) {
                        newName = data.getStringExtra("name");
                        nameText = newName;
                    } else {
                        newName = nameText;
                    }

                    String newContent;
                    if (!data.getStringExtra("content").equals("")) {
                        newContent = data.getStringExtra("content");
                        contentText = newContent;
                    } else {
                        newContent = contentText;
                    }

                    name.setText(newName);
                    content.setText(newContent);

                    NotesProvider.getInstance().updateNotes(position, newName, newContent);
                }
            }else if (resultCode == EditActivity.CANCEL_RESULT_CODE){
                Snackbar.make(root, "Cancel!", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
