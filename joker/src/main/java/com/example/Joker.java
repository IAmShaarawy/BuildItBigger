package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Joker {

    private static Joker instance;
    private List<String> jokesList;
    private Random random;

    private Joker() {

        jokesList = new ArrayList<>(10);

        //0
        jokesList.add("Why do you take baths in milk?" +
                "\n" +
                "I can't find a cow tall enough for a shower.");

        //1
        jokesList.add("You look very funny wearing that belt." +
                "\n" +
                "I would look even funnier if I didn't wear it.");

        //2
        jokesList.add("I was born in California." +
                "\n" +
                "Which part?" +
                "\n" +
                "All of me.");


        //3
        jokesList.add("If tin whistles are made of tin, what are fog horns made of?");

        //4
        jokesList.add("Teacher: Do you have trouble making decisions? " +
                "\n" +
                "Student: Well...yes and no.");

        //5
        jokesList.add("If vegetarians eat vegetables, what do humanitarians eat? ");

        //6
        jokesList.add("Teacher: Did your father help your with your homework? " +
                "\n" +
                "Student: No, he did it all by himself.");

        //7
        jokesList.add("If big elephants have big trunks, do small elephants have suitcases? ");

        //8
        jokesList.add("I used to be a werewoolf... " +
                "\n" +
                "But I'm much better noooooooooooow !");

        //9
        jokesList.add("If tin whistles are made of tin, what are fog horns made of?");


        random = new Random();


    }

    public static Joker getInstance() {

        if (instance == null) {
            instance = new Joker();
        }

        return instance;
    }

    public String tellJoke() {
        return jokesList.get(random.nextInt(10));
    }

}
