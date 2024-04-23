package edu.myutsa.two_note;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Scanner;

public class NoteSpaceActivity extends AppCompatActivity {

    private Account profileInfo;
    private AssetManager assets;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        assets = getAssets();
        setupProfile();
    }

    public void setupProfile(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);

        //profileInfo = new Account(id, assets);

        Scanner scan;
        String str = "";
        String[] arr = null;

        try {
            scan = new Scanner(this.assets.open("accounts.txt"));
            while (scan.hasNext()) {
                str = scan.nextLine();
                arr = str.split(",");
                if (Integer.parseInt(arr[0]) == id) {
                    profileInfo = new Account(id, arr[1], arr[2]);
                    break;
                }
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        TextView name = (TextView)  findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        name.setText(profileInfo.getName());
        email.setText(profileInfo.getEmail());
    }

    private void setupButtons() {
        button = findViewById(R.id.signInButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText uText = (EditText) findViewById(R.id.userNameEditText);
                EditText pText = (EditText) findViewById(R.id.passwordEditText);
                Intent intent = new Intent(NoteSpaceActivity.this, NavigationPage.class);
                startActivity(intent);

            }
        });
    }
}