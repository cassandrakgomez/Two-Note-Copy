package edu.myutsa.two_note;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private AssetManager assets;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assets = getAssets();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupButtons();
    }
    private void setupButtons() {
        button = findViewById(R.id.signInButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText uText = (EditText) findViewById(R.id.userNameEditText);
                EditText pText = (EditText) findViewById(R.id.passwordEditText);
                int id = authenticate(uText.getText().toString(), pText.getText().toString());
                if(id > 0){
                    Intent intent = new Intent(MainActivity.this, NoteSpaceActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
                else {
                    uText.setText("");
                    pText.setText("");
                    uText.setError("Incorrect username/password");
                    pText.setError("Incorrect username/password");
                }

            }
        });
    }

    private int authenticate(String username, String password){
        Scanner scan;
        String str;
        String [] arr = null;
        boolean authenticated = false;
        int id = -1;

        try{
            scan = new Scanner(this.assets.open("login.txt"));
            while(scan.hasNext()){
                str = scan.nextLine();
                arr = str.split(",");
                if(username.equalsIgnoreCase(arr[1]) && password.equals(arr[2])) {
                    authenticated = true;
                    id = Integer.parseInt(arr[0]);
                    break;
                }
            }
            scan.close();

        }

        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return id;
    }
}