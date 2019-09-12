package mb00.android.codehub.ui.repo.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import mb00.android.codehub.R
import mb00.android.codehub.api.model.PullRequest
import mb00.android.codehub.logic.utils.DateParser
import mb00.android.codehub.ui.repo.view.RepoPullRequestsFragment

/**
 * RecyclerView adapter used to display pull requests in [RepoPullRequestsFragment]
 */

class PullRequestAdapter(
        private val pullRequestList: List<PullRequest>,
        private val context: Context
) : RecyclerView.Adapter<PullRequestAdapter.PullRequestHolder>() {

    inner class PullRequestHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.findViewById(R.id.repo_pull_request_avatar_image_view)
        val titleTextView: TextView = itemView.findViewById(R.id.repo_pull_request_title_text_view)
        val detailTextView: TextView = itemView.findViewById(R.id.repo_pull_request_detail_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestHolder {
        val inflater = LayoutInflater.from(parent.context)
        val pullRequestView = inflater.inflate(R.layout.view_holder_pull_request, parent, false)
        return PullRequestHolder(pullRequestView)
    }

    override fun onBindViewHolder(holder: PullRequestHolder, position: Int) {
        val pullRequest = pullRequestList[position]
        val details = "#" + pullRequest.number + " " + DateParser.parseEnglish(pullRequest.creationDate)

        holder.titleTextView.text = pullRequest.title
        holder.detailTextView.text = details

        Glide.with(context).load(pullRequest.user?.avatarUrl).into(holder.avatarImageView)
    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }

}
