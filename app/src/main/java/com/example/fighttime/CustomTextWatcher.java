package com.example.fighttime;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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
        if(charSequence.length() == 1 && (charSequence.charAt(0) > '9' || charSequence.charAt(0) < '0')){
            changeText("0");
        }
        if(charSequence.length() == 2 && (charSequence.charAt(1) > '9' || charSequence.charAt(1) < '0')){
            changeText("0");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}
