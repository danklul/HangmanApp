package com.example.danielekroth.hangmanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Toolbar t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        t = findViewById(R.id.toolbar);
        setSupportActionBar(t);


        result();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.result_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.playNewGame:
                Intent intent = new Intent(this, PlayActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void result() {
        Hangman hang = Hangman.getInstance();
        boolean winner = hang.getResult();
        String result;

        if (winner){
            result = getString(R.string.you_won);
        }else{
            result = getString(R.string.you_lost);
        }

        String word = hang.getRealWord();
        int triesLeft = hang.getTriesLeft();

        TextView resultView = findViewById(R.id.resultView);
        TextView wordView = findViewById(R.id.wordView);
        TextView guessView = findViewById(R.id.guessView);

        resultView.setText(result);
        wordView.setText(word);
        guessView.setText(Integer.toString(triesLeft));

        hang.setWord(null);
    }


    public void backToMenu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
