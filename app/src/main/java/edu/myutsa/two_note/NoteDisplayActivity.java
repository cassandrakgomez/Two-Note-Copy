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
    Intent intent = getIntent();
    //int id = intent.getIntExtra("id", -1);
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_display_page);
        setupButtons();
    }
    private void setupButtons(){
        saveButton = findViewById(R.id.ndpSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save note
                EditText nameInput = (EditText) findViewById(R.id.ndpNoteNameInput);
                EditText contentInput = (EditText) findViewById(R.id.ndpNoteContentGT);
                String name = nameInput.getText().toString();
                String content = contentInput.getText().toString();
                //create a new file for the note
                File f = new File(getFilesDir().getAbsolutePath() + "/" + id + "/" + name);
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(id + "/" + name, MODE_PRIVATE));
                    writer.write(content);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
