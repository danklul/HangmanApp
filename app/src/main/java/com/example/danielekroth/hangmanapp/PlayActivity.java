package com.example.danielekroth.hangmanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayActivity extends AppCompatActivity {


    private Hangman hangMan = Hangman.getInstance();
    private Toolbar t;
    private ImageView imageView;
    private TextView wordView;
    private TextView triesView;
    private TextView guessesView;
    private EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        t = findViewById(R.id.toolbar);
        setSupportActionBar(t);

        String[] temp = getResources().getStringArray(R.array.wordArray);
        ArrayList<String> words = new ArrayList<>(Arrays.asList(temp));
        hangMan.setWords(words);
        hangMan.newWord();

        imageView = findViewById(R.id.imageView);
        wordView = findViewById(R.id.wordWindow);
        triesView = findViewById(R.id.triesLeft);
        guessesView = findViewById(R.id.guessTextView);
        input = findViewById(R.id.userInput);

        wordView.setText(hangMan.getHiddenWord());
        triesView.setText(Integer.toString(hangMan.getTriesLeft()));
        guessesView.setText(hangMan.getBadLetterUsed());

        Button guessButton = findViewById(R.id.guessButton);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessButton();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void guessButton() {
        String guess = input.getText().toString();

        if (guess.length() == 1) {
            char guessChar = guess.charAt(0);

            if (Character.isLetter(guessChar)) {
                if (!hangMan.hasUsedLetter(guessChar)) {
                    hangMan.guess(guessChar);
                    changeImage();
                    wordView.setText(hangMan.getHiddenWord());
                    triesView.setText(Integer.toString(hangMan.getTriesLeft()));
                    guessesView.setText(hangMan.getBadLetterUsed());
                }
                input.setText("");

                if (hangMan.hasWon()) {
                    won();
                }
                if (hangMan.hasLost()) {
                    lost();
                }
            }

        }
    }

    private void changeImage(){
        int i = hangMan.getTriesLeft();
        if (i == 9) {
            imageView.setImageResource(R.drawable.hang9);
        } else if (i == 8) {
            imageView.setImageResource(R.drawable.hang8);
        } else if (i == 7) {
            imageView.setImageResource(R.drawable.hang7);
        } else if (i == 6) {
            imageView.setImageResource(R.drawable.hang6);
        } else if (i == 5) {
            imageView.setImageResource(R.drawable.hang5);
        } else if (i == 4) {
            imageView.setImageResource(R.drawable.hang4);
        } else if (i == 3) {
            imageView.setImageResource(R.drawable.hang3);
        } else if (i == 2) {
            imageView.setImageResource(R.drawable.hang2);
        } else if (i == 1) {
            imageView.setImageResource(R.drawable.hang1);
        } else if (i == 0) {
            imageView.setImageResource(R.drawable.hang0);
        }
    }

    public void won() {
        hangMan.setResult(true);
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    public void lost() {
        hangMan.setResult(false);
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
