package com.example.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ResultFragment extends DialogFragment {

    TextView title, message, mExit;
    ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result,container,false);
        mExit = v.findViewById(R.id.action_exit);
        title = v.findViewById(R.id.titleDisp);
        message = v.findViewById(R.id.messageDisp);
        img = v.findViewById(R.id.imgDisp);

        Bundle bundle = getArguments();
        String btitle = bundle.getString("title");
        String bmsg = bundle.getString("message");
        Boolean won = bundle.getBoolean("won");

        title.setText(btitle);
        message.setText(bmsg);


        if(won){
            title.setTextColor(Color.parseColor("#058F09"));
            message.setTextColor(Color.parseColor("#058F09"));
            img.setImageResource(R.drawable.win);
        }
        else{
            title.setTextColor(Color.parseColor("#AC0000"));
            message.setTextColor(Color.parseColor("#AC0000"));
            img.setImageResource(R.drawable.lose);
        }

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return v;
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }
}