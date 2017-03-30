package com.example;

import java.util.Random;

/**
 * Created by vengalrao on 30-03-2017.
 */

public class Jokes {
    private String[] jokes;
    private Random random;
    public Jokes(){
        jokes=new String[5];
        jokes[0]="When I see lovers' names carved in a tree, I don't think it's sweet. I just think it's surprising how many people bring a knife on a date.";
        jokes[1]="Can a kangaroo jump higher than a house? Of course, a house doesn't jump at all.";
        jokes[2]="A man asks a farmer near a field, 'Sorry sir, would you mind if I crossed your field instead of going around it? You see, I have to catch the 4:23 train.'\n" +
                "\n" +
                "The farmer says, 'Sure, go right ahead. And if my bull sees you, you’ll even catch the 4:11 one.'";
        jokes[3]="I heard women love a man in uniform. Can't wait to start working at McDonalds.";
        jokes[4]="I can't believe I forgot to go to the gym today. That's 7 years in a row now.";
        random=new Random();
    }
    public String  getJoke() {
       return jokes[random.nextInt(jokes.length)];
    }
}
