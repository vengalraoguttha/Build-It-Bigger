package com.example.vengalrao.myapplication.backend;

import com.example.Jokes;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private Jokes joke;
    public MyBean(){
        joke=new Jokes();
    }

    public String getData() {
        return joke.getJoke();
    }

    public void setData(Jokes data) {
        joke = data;
    }
}