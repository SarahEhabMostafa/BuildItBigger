package com.sarahehabm.jokeapp.backend;

/**
 * Created by Sarah E. Mostafa on 20-Feb-16.
 */
public class Joke {
    private String joke;

    public Joke(String joke) {
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
