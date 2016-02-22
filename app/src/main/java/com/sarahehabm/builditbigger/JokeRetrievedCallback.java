package com.sarahehabm.builditbigger;

/**
 * Created by Sarah E. Mostafa on 20-Feb-16.
 */
public interface JokeRetrievedCallback {
    void onJokeRetrieved(String joke);
    void showProgressBar();
    void hideProgressBAr();
}
