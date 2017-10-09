package com.nathanstruhs.namemangler;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import static android.R.attr.max;
import static android.R.attr.restoreAnyVersion;

public class MangledNameActivity extends AppCompatActivity {

    private Button reset_button;
    private Button remangle_button;
    private String name_input_string;

    public static final String KEY_RANDOM = "random_word";
    private String current_random_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangled_name);

        Intent intent = getIntent();
        name_input_string = intent.getStringExtra(MainActivity.KEY_NAME);

        if (savedInstanceState != null) {
            current_random_word = savedInstanceState.getString(KEY_RANDOM);
            mangle(name_input_string, current_random_word);
        } else {
            current_random_word = get_random_word();
            mangle(name_input_string, current_random_word);
        }

        reset_button = (Button) findViewById(R.id.reset_button);
        reset_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reset();
            }
        });

        remangle_button = (Button) findViewById(R.id.remangle_button);
        remangle_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mangle(name_input_string, get_random_word());
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
        return random_words[random_index];
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
        outState.putString(KEY_RANDOM, current_random_word);
        super.onSaveInstanceState(outState);
    }
}
