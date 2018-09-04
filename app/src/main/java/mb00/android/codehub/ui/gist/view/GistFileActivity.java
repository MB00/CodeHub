package mb00.android.codehub.ui.gist.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.gist.adapter.GistFileAdapter;

/**
 * Launched from {@link GistFileAdapter} if gist file in RecyclerView is clicked
 */

public class GistFileActivity extends AppCompatActivity {

    private Bundle gistFileBundle;
    private String fileName;
    private String fileContent;

    private ImageButton fileBackButton;
    private TextView fileTitleTextView;
    private TextView fileTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gist_file);

        gistFileBundle = getIntent().getExtras();
        fileName = gistFileBundle.getString(BundleKeys.FILE_NAME);
        fileContent = gistFileBundle.getString(BundleKeys.FILE_CONTENT);
        fileBackButton = findViewById(R.id.gist_file_back_button);
        fileTitleTextView = findViewById(R.id.gist_file_title_text_view);
        fileTextView = findViewById(R.id.gist_file_text_view);

        fileBackButton.setOnClickListener(view -> finish());
        fileTitleTextView.setText(fileName);
        fileTextView.setText(fileContent);
    }

}
