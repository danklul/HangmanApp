package com.example.danielekroth.hangmanapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {

    private ArrayList<String> words = new ArrayList<>();
    private String word;
    private boolean[] foundLetters;
    private List<Character> wrongLetters = new ArrayList<>();
    private List<Character> rightLetters = new ArrayList<>();
    private int triesLeft;
    private boolean win = false;

    private final static Hangman instance = new Hangman();

    public static Hangman getInstance(){
        return instance;
    }

    private Hangman() { }

    public void setResult(boolean i){
        if (i){
            win = true;
        }
    }

    public boolean getResult(){
        return win;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public String getBadLetterUsed(){
        String wrongLettersString = "";

        for (int i = 0; i < wrongLetters.size(); i++) {
            wrongLettersString += wrongLetters.get(i);
            if (i != wrongLetters.size() - 1){
                wrongLettersString += ", ";
            }
        }
        return wrongLettersString;
    }

    public String getHiddenWord(){
        String hiddenWord = "";

        for (int i = 0; i < word.length(); i++) {
            if (foundLetters[i]) {
                hiddenWord += word.charAt(i);
            }else{
                hiddenWord += "-";
            }
        }
        return hiddenWord;
    }

    public String getRealWord(){
        return word;
    }

    public int getTriesLeft(){
        return triesLeft;
    }

    public void guess(char guess){
        boolean foundIt = false;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (guess == c) {
                foundLetters[i] = true;
                foundIt = true;
            }
        }
        if (foundIt){
            rightLetters.add(guess);
        }else{
            wrongLetters.add(guess);
            triesLeft--;
        }
    }

    public boolean hasLost(){
        return triesLeft == 0;
    }

    public boolean hasUsedLetter(char c){
        return rightLetters.contains(c) || wrongLetters.contains(c);
    }

    public boolean hasWon(){
        int check = 0;
        for (int i = 0; i < word.length(); i++) {
            if (foundLetters[i]) {
                check++;
            }
        }
        return check == foundLetters.length;

    }

    public void newWord(){
        if (word == null) {
            Random r = new Random();
            int randomInt = r.nextInt((words.size()));
            word = words.get(randomInt);

            rightLetters.clear();
            wrongLetters.clear();
            triesLeft = 10;

            foundLetters = new boolean[word.length()];
            for (int i = 0; i < word.length(); i++) {
                foundLetters[i] = false;
            }
        }
    }
}