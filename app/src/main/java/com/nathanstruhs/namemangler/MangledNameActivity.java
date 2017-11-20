package com.nathanstruhs.namemangler;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MangledNameActivity extends AppCompatActivity {

    private Button resetButton;
    private Button remangleButton;
    private String nameInputString;
    private String disposition;
    public static final String KEY_RANDOM = "random_word";
    public static final String TAG = "namemangler";
    private String currentRandomWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangled_name);

        Intent intent = getIntent();
        nameInputString = intent.getStringExtra(MainActivity.KEY_NAME);
        disposition = intent.getStringExtra(MainActivity.KEY_DISPOSITION);
        Log.d(TAG, disposition);

        setImageView();

        if (savedInstanceState != null) {
            currentRandomWord = savedInstanceState.getString(KEY_RANDOM);
            mangle(nameInputString, currentRandomWord);
        } else {
            currentRandomWord = getRandomWord();
            mangle(nameInputString, currentRandomWord);
        }

        resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reset();
            }
        });

        remangleButton = (Button) findViewById(R.id.remangle_button);
        remangleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mangle(nameInputString, getRandomWord());
            }
        });
    }

    private void reset() {
        finish();
    }

    private String getRandomWord() {
        Resources res = getResources();
        String[] random_words;
        if (disposition.equals("nicely")) {
            random_words = res.getStringArray(R.array.random_nice_word_array);
        } else {
            random_words = res.getStringArray(R.array.random_rude_word_array);
        }
        int random_index = new Random().nextInt(random_words.length);
        currentRandomWord = random_words[random_index];
        return currentRandomWord;
    }

    private void mangle(String name, String random_word) {
        MangledName mangledName = new MangledName(name, random_word);
        Resources res = getResources();
        String mangled_name = res.getString(R.string.mangled_name, mangledName.toString());
        setTextView(mangled_name);
    }

    private void setTextView(String mangled_name) {
        TextView textView = (TextView) findViewById(R.id.mangled_name_text_view);
        textView.setText(mangled_name);
    }

    private void setImageView() {
        ImageView headerImageView = (ImageView) findViewById(R.id.header_image);
        ImageView footerImageView = (ImageView) findViewById(R.id.footer_image);
        if (disposition.equals("nicely")) {
            headerImageView.setImageResource(R.drawable.unicorn);
            footerImageView.setVisibility(View.GONE);
        } else {
            footerImageView.setImageResource(R.drawable.angry_unicorn);
            headerImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_RANDOM, currentRandomWord);
        super.onSaveInstanceState(outState);
    }
}
