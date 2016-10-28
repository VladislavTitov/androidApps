package com.example.vladislav.numberlist.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.vladislav.numberlist.contacts.ContactsProvider;
import com.example.vladislav.numberlist.MainActivity;

public class DeleteContactFragment extends DialogFragment {

    int position;

    public interface DeleteContactListener{
        void onDialogPositiveClick();
    }

    DeleteContactListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (DeleteContactListener) context;
        }catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        position = getArguments()!= null ? getArguments().getInt("pos"):-1;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm deleting");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContactsProvider.getInstance().deleteUser(getContext(), position);
                ((MainActivity) getActivity()).onDialogPositiveClick();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}
