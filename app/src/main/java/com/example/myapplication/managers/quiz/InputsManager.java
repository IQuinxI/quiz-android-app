package com.example.myapplication.managers.quiz;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputsManager {

    public boolean CheckIfEmpty(Map<EditText, TextView> inputs) {
        boolean error = false;
        for(Map.Entry<EditText, TextView> set : inputs.entrySet()) {
            String input = set.getKey().getText().toString().trim();
            TextView errorMsg = set.getValue();
            if(input.equals("")) {
                Log.d("Test", "CheckIfEmpty: "+errorMsg.getText().toString().trim());
                errorMsg.setVisibility(View.VISIBLE);
                if(!error) error = true;
            }else {
                errorMsg.setVisibility(View.GONE);
            }
        }
        return error;
    }
}
