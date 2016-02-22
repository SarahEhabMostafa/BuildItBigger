package com.sarahehabm.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.sarahehabm.builditbigger.JokeRetrievedCallback;
import com.sarahehabm.builditbigger.JokesAsyncTask;
import com.sarahehabm.builditbigger.R;
import com.sarahehabm.jokeviewer.JokeActivity;

public class MainActivity extends ActionBarActivity implements JokeRetrievedCallback {
    public String joke;
    private ProgressBar loadingProgressBar;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                startJokeActivity();
            }
        });

        requestNewInterstitial();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new JokesAsyncTask(this).execute(this);
    }

    @Override
    public void onJokeRetrieved(String joke) {
        this.joke = joke;

        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            startJokeActivity();
        }
    }

    private void startJokeActivity() {
        if (joke != null) {
            Intent jokeIntent = new Intent(this, JokeActivity.class);
            jokeIntent.putExtra(JokeActivity.KEY_JOKE, joke);
            startActivity(jokeIntent);
        }
    }

    @Override
    public void showProgressBar() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBAr() {
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(getString(R.string.test_device_id))
                .build();

        interstitialAd.loadAd(adRequest);
    }
}