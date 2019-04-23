package mb00.android.codehub.ui.gist.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.TextView

import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.ui.gist.adapter.GistFileAdapter

/**
 * Launched from [GistFileAdapter] if gist file in RecyclerView is clicked
 */

class GistFileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist_file)

        val gistFileBundle = intent.extras
        val fileName = gistFileBundle!!.getString(BundleKeys.FILE_NAME)
        val fileContent = gistFileBundle.getString(BundleKeys.FILE_CONTENT)
        val fileBackButton = findViewById<ImageButton>(R.id.gist_file_back_button)
        val fileTitleTextView = findViewById<TextView>(R.id.gist_file_title_text_view)
        val fileTextView = findViewById<TextView>(R.id.gist_file_text_view)

        fileBackButton.setOnClickListener { finish() }
        fileTitleTextView.text = fileName
        fileTextView.text = fileContent
    }

}
