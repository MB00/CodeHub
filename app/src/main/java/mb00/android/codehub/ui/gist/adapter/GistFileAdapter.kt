package mb00.android.codehub.ui.gist.adapter

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import mb00.android.codehub.R
import mb00.android.codehub.api.model.GistFile
import mb00.android.codehub.logic.utils.FileSizeParser
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.ui.gist.view.GistFileActivity
import mb00.android.codehub.ui.gist.view.GistFilesFragment

/**
 * RecyclerView adapter used to display gist files in [GistFilesFragment]
 */

class GistFileAdapter(
        private val fileList: List<GistFile>,
        private val fileRecyclerView: RecyclerView,
        private val header: String,
        private val gist: String
) : RecyclerView.Adapter<GistFileAdapter.GistFileHolder>() {

    inner class GistFileHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val gistFileViewHolder: LinearLayout = itemView.findViewById(R.id.gist_file_view_holder)
        val gistFileTextView: TextView = itemView.findViewById(R.id.gist_file_text_view)
        val gistFileSizeTextView: TextView = itemView.findViewById(R.id.gist_file_size_text_view)

        init {
            gistFileViewHolder.setOnClickListener { view ->
                val gistFile = fileList[adapterPosition]
                val gistFileName = gistFile.fileName
                val gistFileContent = gistFile.content
                val gistFileBundle = Bundle()
                gistFileBundle.putString(BundleKeys.FILE_NAME, gistFileName)
                gistFileBundle.putString(BundleKeys.FILE_CONTENT, gistFileContent)

                val gistFileActivityIntent = Intent(view.context, GistFileActivity::class.java)
                gistFileActivityIntent.putExtras(gistFileBundle)
                view.context.startActivity(gistFileActivityIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistFileHolder {
        val inflater = LayoutInflater.from(parent.context)
        val gistFileView = inflater.inflate(R.layout.view_holder_gist_file, parent, false)
        return GistFileHolder(gistFileView)
    }

    override fun onBindViewHolder(holder: GistFileHolder, position: Int) {
        val gistFile = fileList[position]
        holder.gistFileTextView.text = gistFile.fileName
        holder.gistFileSizeTextView.text = FileSizeParser.parseSize(gistFile.size)
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

}
