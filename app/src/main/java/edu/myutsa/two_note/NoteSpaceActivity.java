package edu.myutsa.two_note;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        //assets = getAssets();
        setupProfile();
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
}