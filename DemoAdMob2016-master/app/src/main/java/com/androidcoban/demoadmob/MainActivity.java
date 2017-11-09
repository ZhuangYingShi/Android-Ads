package com.androidcoban.demoadmob;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //Admob
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private Button btnInterstitial;
    private  boolean firstLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_lanscape);
        }

        mAdView =(AdView)findViewById(R.id.adView);
       //Load ads
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Webview

        WebView myWebView = (WebView) findViewById(R.id.webview1);
        myWebView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("http://www.google.com");

        String customHtml = "<html><body><div style=\"background-color: #f4f4f4; border-color: gray; border-style: solid; border-width: 1px; cursor: text; font-family: consolas,'Courier New',courier,monospace; line-height: 12pt; margin: 20px 0px 10px; max-height: 200px; overflow: auto; padding: 4px; width: 97.5%;\">\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"font-style: italic;\">&lt;?</span><span style=\"color: blue; font-weight: bold;\">xml version=</span><span style=\"color: green; font-weight: bold;\">\"1.0\" </span><span style=\"color: blue; font-weight: bold;\">encoding=</span><span style=\"color: green; font-weight: bold;\">\"utf-8\"</span><span style=\"font-style: italic;\">?&gt;</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\">&lt;<span style=\"color: navy; font-weight: bold;\">RelativeLayout </span><span style=\"color: blue; font-weight: bold;\">xmlns:</span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">=</span><span style=\"color: green; font-weight: bold;\">\"http://schemas.android.com/apk/res/android\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: blue; font-weight: bold;\">xmlns:</span><span style=\"color: #660e7a; font-weight: bold;\">tools</span><span style=\"color: blue; font-weight: bold;\">=</span><span style=\"color: green; font-weight: bold;\">\"http://schemas.android.com/tools\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: blue; font-weight: bold;\">xmlns:</span><span style=\"color: #660e7a; font-weight: bold;\">ads</span><span style=\"color: blue; font-weight: bold;\">=</span><span style=\"color: green; font-weight: bold;\">\"http://schemas.android.com/apk/res-auto\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:id=</span><span style=\"color: green; font-weight: bold;\">\"@+id/activity_main\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:layout_width=</span><span style=\"color: green; font-weight: bold;\">\"match_parent\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:layout_height=</span><span style=\"color: green; font-weight: bold;\">\"match_parent\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:paddingBottom=</span><span style=\"color: green; font-weight: bold;\">\"@dimen/activity_vertical_margin\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:paddingLeft=</span><span style=\"color: green; font-weight: bold;\">\"@dimen/activity_horizontal_margin\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:paddingRight=</span><span style=\"color: green; font-weight: bold;\">\"@dimen/activity_horizontal_margin\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:paddingTop=</span><span style=\"color: green; font-weight: bold;\">\"@dimen/activity_vertical_margin\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">    </span><span style=\"color: #660e7a; font-weight: bold;\">tools</span><span style=\"color: blue; font-weight: bold;\">:context=</span><span style=\"color: green; font-weight: bold;\">\"com.androidcoban.demoadmob.MainActivity\"</span>&gt;\n" +
                "\n" +
                "    &lt;<span style=\"color: navy; font-weight: bold;\">com.google.android.gms.ads.AdView</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: navy; font-weight: bold;\">        </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:id=</span><span style=\"color: green; font-weight: bold;\">\"@+id/adView\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">        </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:layout_width=</span><span style=\"color: green; font-weight: bold;\">\"wrap_content\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">        </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:layout_height=</span><span style=\"color: green; font-weight: bold;\">\"wrap_content\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">        </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:layout_centerHorizontal=</span><span style=\"color: green; font-weight: bold;\">\"true\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">        </span><span style=\"color: #660e7a; font-weight: bold;\">android</span><span style=\"color: blue; font-weight: bold;\">:layout_alignParentBottom=</span><span style=\"color: green; font-weight: bold;\">\"true\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">        </span><span style=\"color: #660e7a; font-weight: bold;\">ads</span><span style=\"color: blue; font-weight: bold;\">:adSize=</span><span style=\"color: green; font-weight: bold;\">\"BANNER\"</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">\n" +
                "</span></pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\"><span style=\"color: green; font-weight: bold;\">        </span><span style=\"color: #660e7a; font-weight: bold;\">ads</span><span style=\"color: blue; font-weight: bold;\">:adUnitId=</span><span style=\"color: green; font-weight: bold;\">\"@string/banner_ad_unit_id\"</span>&gt;\n" +
                "    &lt;/<span style=\"color: navy; font-weight: bold;\">com.google.android.gms.ads.AdView</span>&gt;</pre>\n" +
                "<pre style=\"background-color: white; font-family: &quot;Courier New&quot;; font-size: 9pt;\">&lt;/<span style=\"color: navy; font-weight: bold;\">RelativeLayout</span>&gt;</pre></body></html>";
        myWebView.loadData(customHtml, "text/html", "UTF-8");

        WebView myWebView1 = (WebView) findViewById(R.id.webview);
        myWebView1.loadUrl("http://www.example.com");
        //
        //Nếu quảng cáo đã tắt tiến hành load quảng cáo
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                loadInterstitialAd();
            }

            @Override
            public void onAdLoaded() {
                if (firstLoad) {
                    mInterstitialAd.show();
                    firstLoad = false;
                }
            }
        });
        loadInterstitialAd();

        //Load sẵn quảng cáo khi ứng dụng mở
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdView != null) {
            mAdView.destroy();
        }
    }

    //Load InterstitialAd
    private void loadInterstitialAd() {
        if (mInterstitialAd != null) {
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();

            mInterstitialAd.loadAd(adRequest);
            Log.d("AdRequest", String.valueOf(adRequest != null));
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_lanscape);
        } else {
            setContentView(R.layout.activity_main);
            //Load ads
            mAdView =(AdView)findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

            // nay thi` xoay
            // moi lan xoay la moi lan phai tao lai view.
        }
    }
}
