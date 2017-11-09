package com.example.tranganhthi.bannertargetingdemo;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private AdView mAdView;
    private Button mDatePickButton;
    private Button mLoadAdButton;
    private EditText mBirthdayEdit;
    private RadioButton mMaleRadio;
    private RadioButton mFemaleRadio;
    private RadioButton mChildRadio;
    private RadioButton mNotChildRadio;
    private RadioButton mUnspecifiedRadio;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("M/d/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = (AdView) findViewById(R.id.targeting_av_main);
        mLoadAdButton = (Button) findViewById(R.id.targeting_btn_loadad);
        mDatePickButton = (Button) findViewById(R.id.targeting_btn_datepick);
        mBirthdayEdit = (EditText) findViewById(R.id.targeting_et_birthday);
        mMaleRadio = (RadioButton) findViewById(R.id.targeting_rb_male);
        mFemaleRadio = (RadioButton) findViewById(R.id.targeting_rb_female);
        mChildRadio = (RadioButton) findViewById(R.id.targeting_rb_child);
        mNotChildRadio = (RadioButton) findViewById(R.id.targeting_rb_notchild);
        mUnspecifiedRadio = (RadioButton) findViewById(R.id.targeting_rb_unspecified);

        mDatePickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setOnDateSetListener(MainActivity.this);
                newFragment.show(MainActivity.this.getSupportFragmentManager(), "datePicker");
            }
        });
        mLoadAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdRequest.Builder builder = new AdRequest.Builder();
                if (mMaleRadio.isChecked()) {
                    builder.setGender(AdRequest.GENDER_MALE);
                } else if (mFemaleRadio.isChecked()) {
                    builder.setGender(AdRequest.GENDER_FEMALE);
                }
                if (mUnspecifiedRadio.isChecked()) {
                    // There's actually nothing to be done here. If you're unsure whether or not
                    // the user should receive child-directed treatment, simply avoid calling the
                    // tagForChildDirectedTreatment method. The ad request will not contain any
                    // indication one way or the other.
                } else if (mChildRadio.isChecked()) {
                    builder.tagForChildDirectedTreatment(true);
                } else if (mNotChildRadio.isChecked()) {
                    builder.tagForChildDirectedTreatment(false);
                }

                AdRequest request = builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
                mAdView.loadAd(request);
            }
        });



    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        mBirthdayEdit.setText(mDateFormat.format(calendar.getTime()));
    }
}
