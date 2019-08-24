package mb00.android.codehub.ui.repo.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import mb00.android.codehub.R
import mb00.android.codehub.api.model.Repo
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.LanguageColor
import mb00.android.codehub.ui.repo.view.RepoActivity
import mb00.android.codehub.ui.search.view.SearchReposFragment
import mb00.android.codehub.ui.user.view.UserReposFragment

/**
 * RecyclerView adapter used to display repositories in [UserReposFragment] and [SearchReposFragment]
 */

class RepoAdapter(private val repoList: List<Repo>) : RecyclerView.Adapter<RepoAdapter.SearchReposHolder>() {

    inner class SearchReposHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val repoViewHolder: LinearLayout = itemView.findViewById(R.id.repo_view_holder)
        val nameText: TextView = itemView.findViewById(R.id.repo_name_text_view)
        val descriptionText: TextView = itemView.findViewById(R.id.repo_description_text_view)
        val languageColorImage: ImageView = itemView.findViewById(R.id.repo_language_color_image_view)
        val languageText: TextView = itemView.findViewById(R.id.repo_language_text_view)
        val stargazersCountText: TextView = itemView.findViewById(R.id.repo_stargazers_count_text_view)
        val forksCountText: TextView = itemView.findViewById(R.id.repo_forks_count_text_view)
        private lateinit var repoBundle: Bundle

        init {
            repoViewHolder.setOnClickListener {
                startRepoActivity(repoBundle, adapterPosition, itemView.context)
            }
        }

    }

    private fun startRepoActivity(repoBundle: Bundle, adapterPosition: Int, context: Context) {
        val repo = repoList[adapterPosition]
        val userLogin = repo.owner?.login
        val repoName = repo.name
        val repoFullName = repo.fullName
        repoBundle = Bundle()
        repoBundle.putString(BundleKeys.USER_NAME, userLogin)
        repoBundle.putString(BundleKeys.REPO_NAME, repoName)
        repoBundle.putString(BundleKeys.REPO_FULL_NAME, repoFullName)

        val repoActivityIntent = Intent(context, RepoActivity::class.java)
        repoActivityIntent.putExtras(repoBundle)
        context.startActivity(repoActivityIntent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchReposHolder {
        val inflater = LayoutInflater.from(parent.context)
        val repoView = inflater.inflate(R.layout.view_holder_repo, parent, false)

        return SearchReposHolder(repoView)
    }

    override fun onBindViewHolder(holder: SearchReposHolder, position: Int) {
        val repo = repoList[position]

        holder.nameText.text = repo.name
        holder.descriptionText.text = repo.description
        holder.languageColorImage.setImageResource(R.drawable.ic_dot)
        holder.languageText.text = repo.language

        if (repo.language.isNullOrEmpty()) {
            holder.languageColorImage.setColorFilter(LanguageColor.getColor(repo.language ?: ""))
        } else {
            holder.languageColorImage.visibility = View.GONE
        }

        if (repo.description.isNullOrEmpty()) {
            holder.descriptionText.visibility = View.GONE
        }
        holder.stargazersCountText.text = repo.stargazersCount
        holder.forksCountText.text = repo.forksCount
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

}
