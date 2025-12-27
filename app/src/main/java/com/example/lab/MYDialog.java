package com.example.lab;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MYDialog extends DialogFragment {

    @NonNull
    @Override
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder mb = new AlertDialog.Builder(getActivity());

        mb.setIcon(R.drawable.ic_launcher_background);
        mb.setTitle("Delete");
        mb.setMessage("Are you sure?");
        mb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.db.contactDao().deleteContact(CA.contacts.get(CA.pos).phoneNumber);
                Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        mb.setNegativeButton("No", null);

        return mb.create();
    }
}
