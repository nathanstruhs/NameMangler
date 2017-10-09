package com.nathanstruhs.namemangler;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private EditText name_input;
    private Button mangle_button;
    private String name_input_string;

    public static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mangle_button = (Button) findViewById(R.id.mangle_button);
        mangle_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name_input = (EditText) findViewById(R.id.name_input);
                name_input_string = name_input.getText().toString();
                if (name_input_string.isEmpty()) {
                    show_empty_name_toast();
                } else {
                    sendMessage(name_input_string);
                }
            }
        });
    }

    private void show_empty_name_toast() {
        String text = getResources().getString(R.string.empty_edit_text_toast);
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void sendMessage(String name) {
        Intent intent = new Intent(this, MangledNameActivity.class);
        intent.putExtra(KEY_NAME, name);
        startActivity(intent);
    }
}
