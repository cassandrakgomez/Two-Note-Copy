package edu.myutsa.two_note;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.io.OutputStreamWriter;
import java.util.Scanner;
public class NoteDisplayActivity extends AppCompatActivity {
    private Button saveButton;

    //int id = intent.getIntExtra("id", -1);
    int id = 0;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_display_page);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("search", false)) {
            EditText noteNameInput = findViewById(R.id.ndpNoteNameInput);
            EditText noteContentInput = findViewById(R.id.ndpNoteContentGT);
            id = intent.getIntExtra("id", -1);
            name = intent.getStringExtra("noteName");
            noteNameInput.setText(name);
            try {
                File f = new File(getFilesDir().getAbsolutePath() + "/" + id + "_" + name);
                String content = "";
                if(f.exists()){
                    Scanner scan = new Scanner(openFileInput(id + "_" + name));

                    while (scan.hasNext()) {
                        content += scan.nextLine() + "\n";
                    }
                }
                else{
                    Toast.makeText(getBaseContext(), "Note not found", Toast.LENGTH_SHORT).show();
                }
                noteContentInput.setText(content);
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


        setupButtons();
    }
    private void setupButtons(){
        saveButton = findViewById(R.id.ndpSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save note
                id = getIntent().getIntExtra("id", -1);
                EditText nameInput = (EditText) findViewById(R.id.ndpNoteNameInput);
                EditText contentInput = (EditText) findViewById(R.id.ndpNoteContentGT);
                String name = nameInput.getText().toString();
                String content = contentInput.getText().toString();
                //create a new file for the note
                File f = new File(getFilesDir().getAbsolutePath() + "/" + id + "_" + name);
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(id + "_" + name, MODE_PRIVATE));
                    writer.write(content);
                    writer.close();
                    finish();
        } catch (Exception e) {
                    //toast
                    Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
