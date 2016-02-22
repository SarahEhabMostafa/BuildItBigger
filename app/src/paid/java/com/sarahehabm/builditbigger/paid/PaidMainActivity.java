package com.sarahehabm.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.sarahehabm.builditbigger.JokeRetrievedCallback;
import com.sarahehabm.builditbigger.JokesAsyncTask;
import com.sarahehabm.builditbigger.R;
import com.sarahehabm.jokeviewer.JokeActivity;

public class PaidMainActivity extends ActionBarActivity implements JokeRetrievedCallback {
    public String joke;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingProgressBar = (ProgressBar) findViewById(R.id.progressBar);
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

        Intent jokeIntent = new Intent(this, JokeActivity.class);
        jokeIntent.putExtra(JokeActivity.KEY_JOKE, joke);
        startActivity(jokeIntent);
    }

    @Override
    public void showProgressBar() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBAr() {
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }
}
