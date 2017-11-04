package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.adapter.GistFileAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Launched from {@link GistFileAdapter} if gist file in RecyclerView is clicked
 */

public class GistFileActivity extends AppCompatActivity {

    //==============================================================================================
    // GistFileActivity fields
    //==============================================================================================

    private Bundle gistFileBundle;
    private String fileName;
    private String fileContent;

    private ImageButton fileBackButton;
    private TextView fileTitleTextView;
    private TextView fileTextView;

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gist_file);

        gistFileBundle = getIntent().getExtras();
        fileName = gistFileBundle.getString(BundleKeys.FILE_NAME);
        fileContent = gistFileBundle.getString(BundleKeys.FILE_CONTENT);
        fileBackButton = (ImageButton) findViewById(R.id.gist_file_back_button);
        fileTitleTextView = (TextView) findViewById(R.id.gist_file_title_text_view);
        fileTextView = (TextView) findViewById(R.id.gist_file_text_view);

        fileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fileTitleTextView.setText(fileName);
        fileTextView.setText(fileContent);
    }

}
