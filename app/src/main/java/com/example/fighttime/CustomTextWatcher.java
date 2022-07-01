package com.example.fighttime;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class CustomTextWatcher implements TextWatcher {
    EditText editText;

    CustomTextWatcher(EditText _editText){
        editText = _editText;
    }

    private void changeText(String text){
        editText.setText(text);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(charSequence.length() != 0 && charSequence.charAt(0) >= '7' || (charSequence.length() == 2 && charSequence.charAt(1) > '0' && charSequence.charAt(0) == '6')){
            changeText("0");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}
