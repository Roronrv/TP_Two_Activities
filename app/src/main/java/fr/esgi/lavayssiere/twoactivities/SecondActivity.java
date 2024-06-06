package fr.esgi.lavayssiere.twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "fr.esgi.lavayssiere.twoactivities.REPLY";

    private EditText editTextReply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.textView_message);
        textView.setText(message);

        editTextReply = findViewById(R.id.editText_reply);

        // Écouter l'événement de pression sur la touche "Entrée" dans l'EditText
        editTextReply.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Si l'utilisateur appuie sur la touche "Entrée" et relâche
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    returnReply(v);
                    return true; // Indique que l'événement est bien traité
                }
                return false; // Indique que l'événement n'est pas traité ici
            }
        });
    }

    public void returnReply(View view) {
        String reply = editTextReply.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
