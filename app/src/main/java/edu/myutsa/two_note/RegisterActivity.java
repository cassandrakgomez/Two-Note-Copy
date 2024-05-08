package edu.myutsa.two_note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class RegisterActivity extends ComponentActivity{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.registration);
            setupButtons();
        }

        private void setupButtons() {
            Button button1 = (Button) findViewById(R.id.register_submit);
            Button button2 = (Button) findViewById(R.id.privacy_policy_button);
            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int id = -1;
                    EditText uname_input = (EditText) findViewById(R.id.register_uname_input);
                    EditText pw_input = (EditText) findViewById(R.id.register_pw_input);
                    EditText name_input = (EditText) findViewById(R.id.register_name_input);
                    EditText email_input = (EditText) findViewById(R.id.register_email_input);
                    if (validateAccountInfo()) {
                        id = createLogin();
                        if (id > 0) {
                            createAccount(id);
                            Toast.makeText(getBaseContext(), "Account created", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    } else {
                        uname_input.setText("");
                        pw_input.setText("");
                        name_input.setText("");
                        email_input.setText("");
                        uname_input.setError("All fields must be filled out");
                        pw_input.setError("All fields must be filled out");
                        name_input.setError("All fields must be filled out");
                        email_input.setError("All fields must be filled out");
                    }

                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(RegisterActivity.this, PrivacyPolicyActivity.class);
                    startActivity(intent);
                }
            });
        }

        private boolean validateAccountInfo() {
            EditText uname_input = (EditText) findViewById(R.id.register_uname_input);
            EditText pw_input = (EditText) findViewById(R.id.register_pw_input);
            EditText name_input = (EditText) findViewById(R.id.register_name_input);
            EditText email_input = (EditText) findViewById(R.id.register_email_input);

            if (!uname_input.getText().toString().equals("") && !pw_input.getText().toString().equals("") &&
                    !name_input.getText().toString().equals("") && !email_input.getText().toString().equals("")) {
                return true;
            }
            return false;
        }

        private int createLogin() {
            EditText uname_input = (EditText) findViewById(R.id.register_uname_input);
            EditText pw_input = (EditText) findViewById(R.id.register_pw_input);
            String username = uname_input.getText().toString();
            String password = pw_input.getText().toString();

            File f = new File(getFilesDir().getAbsolutePath() + "/login.txt");
            OutputStreamWriter writer = null;
            Scanner scan;
            int id = -1;
            String str = null;
            String[] arr;
            if (!f.exists()) {
                id = 1;
                try {
                    writer = new OutputStreamWriter(openFileOutput("login.txt", MODE_PRIVATE));
                    writer.write(id + "," + username + "," + password);
                    writer.close();
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                try {
                    scan = new Scanner(openFileInput("login.txt"));
                    while (scan.hasNextLine()) {
                        str = scan.nextLine();
                    }
                    if (str != null) {
                        arr = str.split(",");
                        if (arr.length == 3) {
                            id = Integer.parseInt(arr[0]) + 1;
                        }
                    }
                    scan.close();

                    writer = new OutputStreamWriter(openFileOutput("login.txt", MODE_APPEND));
                    writer.append("\n" + id + "," + username + "," + password);
                    writer.close();

                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
            return id;
        }

        private void createAccount(int id) {
            EditText name_input = (EditText) findViewById(R.id.register_name_input);
            EditText email_input = (EditText) findViewById(R.id.register_email_input);
            String name = name_input.getText().toString();
            String email = email_input.getText().toString();

            File f = new File(getFilesDir().getAbsolutePath() + "/accounts.txt");
            OutputStreamWriter writer = null;

            if (!f.exists()) {
                try {
                    writer = new OutputStreamWriter(openFileOutput("accounts.txt", MODE_PRIVATE));
                    writer.write(id + "," + name + "," + email);
                    writer.close();
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                try {
                    writer = new OutputStreamWriter(openFileOutput("accounts.txt", MODE_APPEND));
                    writer.append("\n" + id + "," + name + "," + email);
                    writer.close();
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
}

