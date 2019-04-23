package mb00.android.codehub.ui.repo.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import mb00.android.codehub.R
import mb00.android.codehub.api.model.Commit
import mb00.android.codehub.logic.utils.DateParser
import mb00.android.codehub.ui.repo.view.RepoCommitsFragment

/**
 * A RecyclerView adapter used to display commits in [RepoCommitsFragment]
 */

class CommitAdapter(
        private val commitList: List<Commit>,
        private val context: Context
) : RecyclerView.Adapter<CommitAdapter.CommitHolder>() {

    inner class CommitHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commitAvatarImageView: ImageView = itemView.findViewById(R.id.repo_commit_avatar_image_view)
        val commitTitleTextView: TextView = itemView.findViewById(R.id.repo_commit_title_text_view)
        val commitDetailTextView: TextView = itemView.findViewById(R.id.repo_commit_detail_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitHolder {
        val inflater = LayoutInflater.from(parent.context)
        val commitView = inflater.inflate(R.layout.view_holder_commit, parent, false)
        return CommitHolder(commitView)
    }

    override fun onBindViewHolder(holder: CommitHolder, position: Int) {
        val commit = commitList[position]
        holder.commitTitleTextView.text = commit.commitNested?.message
        val details = commit.commitNested?.author?.name + " " + DateParser.parseEnglish(commit.commitNested?.author?.date)
        holder.commitDetailTextView.text = details

        /*if (commit.author.avatarUrl.isNotEmpty())
            Glide.with(context).load(commit.author.avatarUrl).into(holder.commitAvatarImageView)
        else
            holder.commitAvatarImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_github))*/

        commit.author.let {
            Glide.with(context).load(it?.avatarUrl).into(holder.commitAvatarImageView)
        }
    }

    override fun getItemCount(): Int {
        return commitList.size
    }

}
