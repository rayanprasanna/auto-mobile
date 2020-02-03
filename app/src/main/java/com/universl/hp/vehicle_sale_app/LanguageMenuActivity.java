package com.universl.hp.vehicle_sale_app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.universl.smsnotifier.ApiSMSSender;
import com.universl.smsnotifier.Constants;
import com.universl.smsnotifier.MessageOperator;
import com.universl.smsnotifier.MsgOperatorFactory;
import com.universl.smsnotifier.Param;
import com.universl.smsnotifier.SMSSender;
import com.universl.smsnotifier.USSDDialer;

import java.util.ArrayList;
import java.util.List;

public class LanguageMenuActivity extends AppCompatActivity {

    private SMSSender smsSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_menu);
        smsNofify();
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.sinhala:
                if(checked){
                    Intent sinhala_intent = new Intent(LanguageMenuActivity.this,MainMenuActivity.class);
                    startActivity(sinhala_intent);
                    finish();
                    break;
                }
            case R.id.english:
                if(checked){
                    Intent english_intent = new Intent(LanguageMenuActivity.this,MainActivity.class);
                    startActivity(english_intent);
                    finish();
                    break;
                }
        }
    }

    private void smsNofify() {

        List<MessageOperator> messageOperators = new ArrayList<>();
        MessageOperator ideaMartOperator = MsgOperatorFactory.createMessageOperator("", Constants.SP_DIALOG1, Constants.SP_DIALOG2, Constants.SP_DIALOG3, Constants.SP_AIRTEL, Constants.SP_HUTCH);//, Constants.SP_ETISALAT
        ideaMartOperator.setSmsMsg("#780*189#");
        ideaMartOperator.setCharge("2 LKR +Tax P/D + 5 LKR+ Tax M/O");
        messageOperators.add(ideaMartOperator);

        Param param = new Param(getResources().getString(R.string.yes), getResources().getString(R.string.no));
        smsSender = new USSDDialer(this, messageOperators, param);
        smsSender.smsNotify(getResources().getString(R.string.sms_dis_msg), getResources().getString(R.string.app_name));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            case SMSSender.PERMISSIONS_ACTION_CALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    smsSender.smsNotify(getResources().getString(R.string.sms_dis_msg),getResources().getString(R.string.app_name));
                }
                return;
            }
        }
    }

}
