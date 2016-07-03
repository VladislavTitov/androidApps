package titov.testgame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);



    }

    public void checkSelect(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Молодец!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
