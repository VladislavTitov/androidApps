package titov.chatandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mUserNameEditText;
    private Button mLogInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserNameEditText = (EditText) findViewById(R.id.my_edit_text);
        mLogInButton = (Button) findViewById(R.id.log_in_button);
        mLogInButton.setText("Вход");

    }

    public void login(View view) {
        Toast.makeText(this, "Событие", Toast.LENGTH_SHORT).show();
    }
}
