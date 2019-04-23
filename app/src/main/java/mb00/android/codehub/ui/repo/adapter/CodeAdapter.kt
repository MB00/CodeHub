package mb00.android.codehub.ui.repo.adapter

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mb00.android.codehub.R
import mb00.android.codehub.api.model.Code
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.logic.utils.FileSizeParser
import mb00.android.codehub.ui.repo.view.RepoCodeFragment
import mb00.android.codehub.ui.repo.view.RepoFileActivity
import mb00.android.codehub.ui.repo.viewmodel.RepoCodeViewModel
import timber.log.Timber

/**
 * A RecyclerView adapter used to display folders and files in [RepoCodeFragment]
 */

class CodeAdapter(
        private val viewModel: RepoCodeViewModel,
        private val fileList: List<Code>,
        private val codeRecyclerView: RecyclerView,
        private val header: String,
        private val user: String,
        private val repo: String
) : RecyclerView.Adapter<CodeAdapter.CodeHolder>() {

    inner class CodeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val codeViewHolder: LinearLayout = itemView.findViewById(R.id.code_view_holder)
        val codeTypeImageView: ImageView = itemView.findViewById(R.id.code_type_image_view)
        val codeTextView: TextView = itemView.findViewById(R.id.code_text_view)
        val codeSizeTextView: TextView = itemView.findViewById(R.id.code_size_text_view)

        init {
            codeViewHolder.setOnClickListener {
                val code = fileList[adapterPosition]
                if (code.type == "dir") {
                    val path = code.path
                    viewModel.loadRepoCode(header, user, repo, path!!)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ repoCodeList ->
                                if (repoCodeList.isNotEmpty()) {
                                    val sortedCodeList = viewModel.sortCodeList(repoCodeList)
                                    codeRecyclerView.adapter = CodeAdapter(viewModel, sortedCodeList, codeRecyclerView, header, user, repo)
                                } else {
                                    val noRepoCodeTextView = (codeRecyclerView.parent as View).findViewById<TextView>(R.id.no_repo_code_text_view)
                                    noRepoCodeTextView.visibility = View.VISIBLE
                                }
                            }, { error -> Timber.e(error.message) })
                    //RepoCodeFragment.displayPathAsViewObjects(path, view.getContext(), (ViewGroup) itemView.getParent());
                } else { // code.getType().equals("file)
                    val fileBundle = Bundle()
                    fileBundle.putString(BundleKeys.USER_NAME, user)
                    fileBundle.putString(BundleKeys.REPO_NAME, repo)
                    fileBundle.putString(BundleKeys.FILE_NAME, code.name)
                    fileBundle.putString(BundleKeys.FILE_PATH, code.path)

                    val fileIntent = Intent(itemView.context, RepoFileActivity::class.java)
                    fileIntent.putExtras(fileBundle)
                    itemView.context.startActivity(fileIntent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val codeView = inflater.inflate(R.layout.view_holder_code, parent, false)
        return CodeHolder(codeView)
    }

    override fun onBindViewHolder(holder: CodeHolder, position: Int) {
        val code = fileList[position]

        if (code.type == "dir") {
            holder.codeTypeImageView.setImageResource(R.drawable.ic_directory)
            holder.codeSizeTextView.visibility = View.GONE
        } else { // code.getType().equals("file")
            holder.codeTypeImageView.setImageResource(R.drawable.ic_file)
            holder.codeSizeTextView.text = FileSizeParser.parseSize(code.size)
        }
        holder.codeTextView.text = code.name
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

}
