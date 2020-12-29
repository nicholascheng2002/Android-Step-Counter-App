package com.example.bigsteps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class checklist extends YouTubeBaseActivity {

    MyDatabaseHelper2 myDB2;
    SQLiteDatabase db2;

    // definition for webview widgets
    ProgressBar superProgressBar;
    ImageView superImageView;
    WebView superWebView;

    ListView listView;
   // private TextView result_textviews;

    YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    public int result1extra;
    public int result2extra;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        //result_textviews = (TextView) findViewById(R.id.result_textview);

        // codes for webview
        superProgressBar = findViewById(R.id.myProgressBar);
        superImageView = findViewById(R.id.myImageView);
        superWebView = findViewById(R.id.myWebView);

        // set limit for progress bar
        superProgressBar.setMax(100);

        myDB2 = new MyDatabaseHelper2(this);
        //db2 = myDB2.getReadableDatabase();
        Cursor cursor = myDB2.allresult();

        if(cursor.getCount() == 0){
           // result_textviews.setText("no result shown"); // this is for check if ze value of the data extracted from ze sql table to be correct and up to ze latest record
        } else {
            while(cursor.moveToNext()) {
                 result1extra = cursor.getInt(1);
                 result2extra = cursor.getInt(2);

                if(result1extra == 1){
                    if(result2extra == 0){
                        //code to do if user passed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/246/alternate-leg-push-off/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    } else {
                        //code to do if user failed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/135/bodyweight-squat/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                        // this is not used as its not a webview inside the app so its kinda pointless not gonna lie not gonna get my marks for this
                        /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Dx5qFachd3A"));
                        startActivity(intent); */
                    }
                } else if(result1extra == 2){
                    if(result2extra == 0){
                        //code to do if user passed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/170/agility-ladder-lateral-shuffle/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    } else {
                        //code to do if user failed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/23/ankle-flexion/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    }
                } else if(result1extra == 3){
                    if(result2extra == 0){
                        //code to do if user passed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/236/barbell-jammers/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    } else {
                        //code to do if user failed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/294/calf-raise/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    }
                } else if(result1extra == 4){
                    if(result2extra == 0){
                        //code to do if user passed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/60/stability-ball-knee-tucks/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    } else {
                        //code to do if user failed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/177/forward-linear-jumps/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    }
                } else if (result1extra == 5) {
                    if(result2extra == 0){
                        //code to do if user passed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/306/burpee/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    } else {
                        //code to do if user failed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/33/hip-hinge/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    }
                } else if(result1extra == 6){
                    if(result2extra == 0){
                        //code to do if user passed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/347/lateral-lunge-wood-chop/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    } else {
                        //code to do if user failed their test
                        superWebView.loadUrl("https://www.acefitness.org/education-and-resources/lifestyle/exercise-library/53/contralateral-limb-raises/");
                        superWebView.getSettings().setJavaScriptEnabled(true);
                        superWebView.setWebViewClient(new WebViewClient());
                        superWebView.setWebChromeClient(new WebChromeClient(){

                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                superProgressBar.setProgress(newProgress);
                            }

                            @Override
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                                // getSupportActionBar().setTitle(title);
                            }

                            @Override
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                                superImageView.setImageBitmap(icon);
                            }
                        });

                    }
                }

               // result_textviews.setText(String.valueOf(result1extra)); // this is for check if ze value of the data extracted from ze sql table to be correct and up to ze latest record
                //result_textviews.setText("it is working ");
              //  Toast.makeText(getApplicationContext(),"you did it!", Toast.LENGTH_SHORT).show(); // this is to show if the sql getting data method works not intended as main function
        }

           // result_textviews.setText("it is working ");
           // Toast.makeText(getApplicationContext(),"you did it!", Toast.LENGTH_SHORT).show();

       /*  String [] column = {"result1", "result2"};
       // @SuppressLint("Recycle") Cursor cursor = db2.query("checklist", column, null, null, null, null, null);
        Cursor cursor = db2.query("checklisttester", column, null, null, null, "DESC", "1"); */

      /*  if(cursor.getCount() == 0){
            result_textviews.setText("no result shown");
        } else {
            while(cursor.moveToNext()){
                result_textviews.setText("result1 : " + String.valueOf(cursor.getInt(1)) + " result2 :  " + String.valueOf(cursor.getInt(2)));
            }
             }*/
        }


        // for youtube player
        btnPlay = (Button) findViewById(R.id.btnPlay);
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("rRt3tXSg-U8");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        btnPlay.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);
           }
        });
    } //end of youtube player code

    // method for pressing back the webview go back previous page
    @Override
    public void onBackPressed(){
        if (superWebView.canGoBack()){
            superWebView.goBack();
        } else {
            finish();
        }
    }


}