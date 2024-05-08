package edu.myutsa.two_note;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);

        TextView textViewHeader = findViewById(R.id.tvPrivacyPolicyHeader);
        TextView textViewTitle = findViewById(R.id.tvPrivacyPolicyTitle);
        TextView textViewBody = findViewById(R.id.tvPrivacyPolicyBody);

        textViewHeader.setText(getString(R.string.privacy_policy_header));
        textViewTitle.setText(getString(R.string.privacy_policy_title));
        textViewBody.setText(getString(R.string.privacy_policy_body));

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


}