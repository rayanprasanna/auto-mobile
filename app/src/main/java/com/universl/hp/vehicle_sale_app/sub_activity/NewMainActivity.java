package com.universl.hp.vehicle_sale_app.sub_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;
import com.universl.hp.vehicle_sale_app.MainMenuActivity;
import com.universl.hp.vehicle_sale_app.R;

public class NewMainActivity extends AppCompatActivity {

    private PopupWindow popupWindow;
    private Context context;
    Activity activity;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        linearLayout = findViewById(R.id.main_linear_layout);
        context = getApplicationContext();
        activity = NewMainActivity.this;
        open_Popup_window();
    }
    @SuppressLint("InflateParams")
    private void open_Popup_window(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        @SuppressLint("InflateParams") View customView = null;
        if (inflater != null) {
            customView = inflater.inflate(R.layout.select_language,null);
        }

        popupWindow = new PopupWindow(
                customView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        if(Build.VERSION.SDK_INT>=21){
            popupWindow.setElevation(5.0f);
        }

        TextView sl_language,en_language;
        if (customView != null) {
            sl_language = customView.findViewById(R.id.sinhala);
            en_language = customView.findViewById(R.id.english);

            sl_language.setPaintFlags(sl_language.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            en_language.setPaintFlags(en_language.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }

        findViewById(R.id.main_linear_layout).post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(linearLayout, Gravity.CENTER_HORIZONTAL,0,0);
            }
        });
    }
}