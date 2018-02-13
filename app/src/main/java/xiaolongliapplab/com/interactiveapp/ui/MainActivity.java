package xiaolongliapplab.com.interactiveapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.*;
import android.content.res.Resources;

import xiaolongliapplab.com.interactiveapp.R;

//AppCompatActivity made sure evething works in old version and new version
public class MainActivity extends AppCompatActivity
{
    private EditText nameField;
    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText) findViewById(R.id.nameEditText);
        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString();
                //Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();

                startStory(name);

            }
        });
    }

    private void startStory(String name)
    {
        Intent intent = new Intent(this,StoryActivity.class);
        Resources resources = getResources();
        String key = resources.getString(R.string.key_name);

        intent.putExtra(key,name);
        startActivity(intent);





    }
}
