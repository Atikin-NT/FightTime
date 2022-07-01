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

public class RoundDialogFragment extends DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.alert_dialog, null);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        EditText editTextFightMin = (EditText) promptsView.findViewById(R.id.editTextFightMin);
        EditText editTextFightSec = (EditText) promptsView.findViewById(R.id.editTextFightSec);
        EditText editTextBreakMin = (EditText) promptsView.findViewById(R.id.editTextBreakMin);
        EditText editTextBreakSec = (EditText) promptsView.findViewById(R.id.editTextBreakSec);
        EditText editTextCount = (EditText) promptsView.findViewById(R.id.editTextCount);
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

        builder.setView(promptsView);

        builder.setTitle("Новый раунд")
                .setMessage("Введите данные")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "add",
                                Toast.LENGTH_LONG).show();
                        ((MainActivity)getActivity()).addRoundAfterDialog(
                                editTextFightMin.getText().toString(),
                                editTextFightSec.getText().toString(),
                                editTextBreakMin.getText().toString(),
                                editTextBreakSec.getText().toString(),
                                editTextCount.getText().toString()
                        );
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_LONG)
                            .show();
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}
