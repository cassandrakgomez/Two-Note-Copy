package edu.myutsa.two_note;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class NoteSpaceActivity extends AppCompatActivity {

    private Account profileInfo;
    //private AssetManager assets;
    private Button signoutButton;
    private Button newnoteButton;
    private Button findnoteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        //assets = getAssets();
        setupProfile();
        setupButtons();
        //setupProfile2();
    }

    public void setupProfile() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        //profileInfo = new Account(id, assets);
        File f = new File(getFilesDir().getAbsolutePath() + "/accounts.txt");

        Scanner scan;
        String str = "";
        String[] arr = null;

        try {
            if (f.exists()) {
                scan = new Scanner(openFileInput("accounts.txt"));
                while (scan.hasNext()) {
                    str = scan.nextLine();
                    arr = str.split(",");
                    if (Integer.parseInt(arr[0]) == id) {
                        profileInfo = new Account(id, arr[1], arr[2]);
                        break;
                    }
                }
                scan.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (profileInfo != null) {
            TextView name = (TextView) findViewById(R.id.name);
            TextView email = (TextView) findViewById(R.id.email);
            name.setText(profileInfo.getName());
            email.setText(profileInfo.getEmail());
        }
    }
    private void setupProfile2() {
        Intent intent = getIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            profileInfo = intent.getParcelableExtra("account", Account.class);
        }
        TextView name = (TextView)  findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        name.setText(profileInfo.getName());
        email.setText(profileInfo.getEmail());
    }
    //method called setupButtons
    //sets up the buttons for the note space activity
    private void setupButtons(){
        signoutButton = findViewById(R.id.LandingPageExitButton);
        signoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        newnoteButton = findViewById(R.id.LandPagNewNote);
        newnoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = getIntent();
                Intent newIntent = new Intent(NoteSpaceActivity.this, NoteDisplayActivity.class);
                int id = intent.getIntExtra("id", -1);
                newIntent.putExtra("id", id);
                newIntent.putExtra("search", false);
                startActivity(newIntent);
            }
        });

        findnoteButton = findViewById(R.id.nsa_find_note_button);
        findnoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = getIntent();
                int id = intent.getIntExtra("id", -1);
                EditText noteName = (EditText) findViewById(R.id.nsa_note_search_bar);
                String name = noteName.getText().toString();
                File f = new File(getFilesDir().getAbsolutePath() + "/" + id + "_" + name);
                if(f.exists()){
                    Intent newIntent = new Intent(NoteSpaceActivity.this, NoteDisplayActivity.class);
                    newIntent.putExtra("id", id);
                    newIntent.putExtra("noteName", name);
                    newIntent.putExtra("search", true);
                    startActivity(newIntent);
                }
                else{
                    Toast.makeText(getBaseContext(), "Note not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}