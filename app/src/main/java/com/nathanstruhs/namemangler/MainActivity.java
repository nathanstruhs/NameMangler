package com.nathanstruhs.namemangler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private Button mangleNicelyButton;
    private Button mangleRudelyButton;
    private String nameInputString;

    public static final String KEY_NAME = "name";
    public static final String KEY_DISPOSITION = "disposition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearEditText();

        mangleNicelyButton = (Button) findViewById(R.id.mangle_nicely_button);
        mangleNicelyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameInput = (EditText) findViewById(R.id.name_input);
                nameInputString = nameInput.getText().toString();
                final String disposition = "nicely";
                if (nameInputString.isEmpty()) {
                    showEmptyNameToast();
                } else {
                    sendMessage(nameInputString, disposition);
                }
            }
        });

        mangleRudelyButton = (Button) findViewById(R.id.mangle_rudely_button);
        mangleRudelyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameInput = (EditText) findViewById(R.id.name_input);
                nameInputString = nameInput.getText().toString();
                final String disposition = "rudely";
                if (nameInputString.isEmpty()) {
                    showEmptyNameToast();
                } else {
                    sendMessage(nameInputString, disposition);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearEditText();
    }

    private void showEmptyNameToast() {
        String text = getResources().getString(R.string.empty_edit_text_toast);
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void sendMessage(String name, String disposition) {
        Intent intent = new Intent(this, MangledNameActivity.class);
        intent.putExtra(KEY_NAME, name);
        intent.putExtra(KEY_DISPOSITION, disposition);
        startActivity(intent);
    }

    public void clearEditText() {
        nameInput = (EditText) findViewById(R.id.name_input);
        nameInput.setText("");
    }
}
