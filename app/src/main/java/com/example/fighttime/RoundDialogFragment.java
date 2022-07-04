package com.example.fighttime;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RoundDialogFragment extends DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.alert_dialog, null);

        MaterialAlertDialogBuilder materialAlertDialog = new MaterialAlertDialogBuilder(getActivity(), R.style.AlertDialogStyle);

        TextInputLayout editTextFightMinL = (TextInputLayout) promptsView.findViewById(R.id.editTextFightMin);
        TextInputLayout editTextFightSecL = (TextInputLayout) promptsView.findViewById(R.id.editTextFightSec);
        TextInputLayout editTextBreakMinL = (TextInputLayout) promptsView.findViewById(R.id.editTextBreakMin);
        TextInputLayout editTextBreakSecL = (TextInputLayout) promptsView.findViewById(R.id.editTextBreakSec);
        TextInputLayout editTextCountL = (TextInputLayout) promptsView.findViewById(R.id.editTextCount);


        EditText editTextFightMin = editTextFightMinL.getEditText();
        EditText editTextFightSec = editTextFightSecL.getEditText();
        EditText editTextBreakMin = editTextBreakMinL.getEditText();
        EditText editTextBreakSec = editTextBreakSecL.getEditText();
        EditText editTextCount = editTextCountL.getEditText();

        editTextFightMin.setInputType(2);
        editTextFightSec.setInputType(2);
        editTextBreakMin.setInputType(2);
        editTextBreakMin.setInputType(2);
        editTextBreakSec.setInputType(2);
        editTextCount.setInputType(2);
        editTextFightMin.addTextChangedListener(new CustomTextWatcher(editTextFightMin));
        editTextFightSec.addTextChangedListener(new CustomTextWatcher(editTextFightSec));
        editTextBreakMin.addTextChangedListener(new CustomTextWatcher(editTextBreakMin));
        editTextBreakSec.addTextChangedListener(new CustomTextWatcher(editTextBreakSec));

        materialAlertDialog.setView(promptsView);

        materialAlertDialog
                .setTitle(R.string.NewRound)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "add", Toast.LENGTH_LONG).show();
                        ((MainActivity)getActivity()).addRoundAfterDialog(
                                editTextFightMin.getText().toString(),
                                editTextFightSec.getText().toString(),
                                editTextBreakMin.getText().toString(),
                                editTextBreakSec.getText().toString(),
                                editTextCount.getText().toString()
                        );
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_LONG).show();
                        dialogInterface.cancel();
                    }
                });
        return materialAlertDialog.create();
    }
}
