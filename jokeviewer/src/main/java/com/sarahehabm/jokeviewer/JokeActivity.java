package com.sarahehabm.jokeviewer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;


public class JokeActivity extends ActionBarActivity {
    public static final String KEY_JOKE = "KEY_JOKE";

    private TextView textView_joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if(getActionBar()!=null)
            getActionBar().setDisplayHomeAsUpEnabled(true);
        else if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView_joke = (TextView) findViewById(R.id.textView_joke);

        String joke;
        if (getIntent() != null && getIntent().hasExtra(KEY_JOKE))
            joke = getIntent().getStringExtra(KEY_JOKE);
        else
            joke = getString(R.string.empty_joke);

        textView_joke.setText(joke);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
