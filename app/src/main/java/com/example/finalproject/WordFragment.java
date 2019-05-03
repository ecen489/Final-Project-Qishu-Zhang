package com.example.finalproject;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class WordFragment extends DialogFragment {

    private static final int CAMERA_REQUEST_CODE = 200;
    String cameraPermission[];

    public interface OnInputListener{
        void sendInput(String input);
        void sendHint(String hint);
    }

    public OnInputListener mOnInputListener;


    //widgets
    private EditText mInput, mHint;
    private TextView mActionOk, mActionCancel, mError;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word, container, false);
        mActionCancel = view.findViewById(R.id.action_cancel);
        mActionOk = view.findViewById(R.id.action_ok);
        mError = view.findViewById(R.id.error);
        mInput = view.findViewById(R.id.input);
        mHint = view.findViewById(R.id.hint);


        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mInput.getText().toString();
                String hint = mHint.getText().toString();
                String word_string = mInput.getText().toString();
                if((input.length() <= 9) && string_acceptable(word_string)){
                    mOnInputListener.sendInput(input);
                    mOnInputListener.sendHint(hint);
                    getDialog().dismiss();
                }
                else{
                    String message = "Submit ONE Word (< 10 letters)";
                    mError.setText(message);
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
        }
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

    public Boolean string_acceptable(String string){
        if(Pattern.matches("[a-zA-Z]+", string))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}