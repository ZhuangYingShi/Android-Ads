package com.example.tranganhthi.demobannersize;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private Button mLoadButton;
    private FrameLayout mAdFrameLayout;
    private Spinner mSizesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSizesSpinner = (Spinner) findViewById(R.id.bannersizes_spn_size);
        mLoadButton = (Button) findViewById(R.id.bannersizes_btn_loadad);
        mAdFrameLayout = (FrameLayout) findViewById(R.id.bannersizes_fl_adframe);

        String[] sizesArray;
        // mobile SDK hổ trợ 2 loại màn hình trên di động và trên table.
        // trên phone hổ trợ gồm Banner, Large Banner và Smart Banner
        // trên tablet hổ trợ BannerLarge, Banner, Smart Banner, Full Banner, Medium Rectangle,Leaderboard
        // để xác định các loại trên cần check kích thước màn hình.
        int screenSize = getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
        // lay cac phan tu trong file string.xml gán vào sizesArray;
        if ((screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE)
                || (screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)) {
            sizesArray = getResources().getStringArray(R.array.bannersizes_largesizes);
        } else {
            sizesArray = getResources().getStringArray(R.array.bannersizes_smallsizes);
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(mSizesSpinner.getContext(),
                android.R.layout.simple_spinner_dropdown_item, sizesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSizesSpinner.setAdapter(adapter);
        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdView != null) {
                    mAdFrameLayout.removeView(mAdView);
                    mAdView.destroy();
                }
                mAdView = new AdView(MainActivity.this);
                mAdView.setAdUnitId(getString(R.string.admob_banner_ad_unit_id));
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdFrameLayout.addView(mAdView);
                // check vị trí của các item trong spinner vào lựa chọn kích thước banner tương ứng.
                switch (mSizesSpinner.getSelectedItemPosition()) {
                    case 0:
                        mAdView.setAdSize(AdSize.BANNER);
                        break;
                    case 1:
                        mAdView.setAdSize(AdSize.LARGE_BANNER);
                        break;
                    case 2:
                        mAdView.setAdSize(AdSize.SMART_BANNER);
                        break;
                    case 3:
                        mAdView.setAdSize(AdSize.FULL_BANNER);
                        break;
                    case 4:
                        mAdView.setAdSize(AdSize.MEDIUM_RECTANGLE);
                        break;
                    case 5:
                        mAdView.setAdSize(AdSize.LEADERBOARD);
                        break;
                }
                mAdView.loadAd(new AdRequest.Builder().build());
            }
        });


    }
}
