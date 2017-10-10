package com.nathanstruhs.namemangler;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MangledNameActivity extends AppCompatActivity {

    private Button resetButton;
    private Button remangleButton;
    private String nameInputString;
    public static final String KEY_RANDOM = "random_word";
    private String currentRandomWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangled_name);

        Intent intent = getIntent();
        nameInputString = intent.getStringExtra(MainActivity.KEY_NAME);

        if (savedInstanceState != null) {
            currentRandomWord = savedInstanceState.getString(KEY_RANDOM);
            mangle(nameInputString, currentRandomWord);
        } else {
            currentRandomWord = get_random_word();
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
                mangle(nameInputString, get_random_word());
            }
        });
    }

    private void reset() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private String get_random_word() {
        Resources res = getResources();
        String[] random_words = res.getStringArray(R.array.random_word_array);
        int random_index = new Random().nextInt(random_words.length);
        currentRandomWord = random_words[random_index];
        return currentRandomWord;
    }

    private void mangle(String name, String random_word) {
        Resources res = getResources();
        String mangled_name = res.getString(R.string.mangled_name, name, random_word);
        set_text_view(mangled_name);
    }

    private void set_text_view(String mangled_name) {
        TextView textView = (TextView) findViewById(R.id.mangled_name_text_view);
        textView.setText(mangled_name);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_RANDOM, currentRandomWord);
        super.onSaveInstanceState(outState);
    }
}
