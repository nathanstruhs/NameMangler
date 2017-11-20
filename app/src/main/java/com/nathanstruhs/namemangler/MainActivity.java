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
    private Button mangleButton;
    private String nameInputString;

    public static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mangleButton = (Button) findViewById(R.id.mangle_nicely_button);
        mangleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameInput = (EditText) findViewById(R.id.name_input);
                nameInputString = nameInput.getText().toString();
                if (nameInputString.isEmpty()) {
                    showEmptyNameToast();
                } else {
                    sendMessage(nameInputString);
                }
            }
        });
    }

    private void showEmptyNameToast() {
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
