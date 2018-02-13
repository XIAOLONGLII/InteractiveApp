package xiaolongliapplab.com.interactiveapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import xiaolongliapplab.com.interactiveapp.R;
import xiaolongliapplab.com.interactiveapp.model.Page;
import xiaolongliapplab.com.interactiveapp.model.Story;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();
    private Page page;
    private String name;
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        choice1Button = (Button) findViewById(R.id.choice1Button);
        choice2Button = (Button) findViewById(R.id.choice2Button);
        Intent intent = getIntent();

        name = intent.getStringExtra(getString(R.string.key_name));
        if(name == null || name.isEmpty())
        {
            name = "Friend";
        }
        Log.d(TAG,name);
        story = new Story();
        loadPage(0);

    }

    private void loadPage(int pageNumber)
    {
         page =story.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this,page.getImageId());
        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());

        pageText = String.format(pageText,name);
        storyTextView.setText(pageText);

        if(page.isFinalPage())
        {
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText("PLAY AGAIN");
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //finish();
                    loadPage(0);

                }
            });


        }
        else
            {
                loadButtons();

        }


    }

    private void loadButtons() {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });
        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

}

/*


java.lang.NullPointerException:
Attempt to invoke virtual method 'int xiaolongliapplab.com.interactiveapp.model.Choice.getTextId()'
on a null object reference
         at xiaolongliapplab.com.interactiveapp.ui.StoryActivity.loadPage(StoryActivity.java:65)
        at xiaolongliapplab.com.interactiveapp.ui.StoryActivity.access$100(StoryActivity.java:19)
         at xiaolongliapplab.com.interactiveapp.ui.StoryActivity$1.onClick(StoryActivity.java:70)
 */
