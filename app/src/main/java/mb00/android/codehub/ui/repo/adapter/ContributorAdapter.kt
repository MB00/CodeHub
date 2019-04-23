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

import com.bumptech.glide.Glide

import mb00.android.codehub.R
import mb00.android.codehub.api.model.Contributor
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.ui.repo.view.RepoContributorsFragment
import mb00.android.codehub.ui.user.view.UserActivity

/**
 * A RecyclerView adapter used to display repository contributors in [RepoContributorsFragment]
 */

class ContributorAdapter(
        private val contributorList: List<Contributor>
) : RecyclerView.Adapter<ContributorAdapter.ContributorHolder>() {

    inner class ContributorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val contributorViewHolder: LinearLayout = itemView.findViewById(R.id.repo_contributor_view_holder)
        val avatarImageView: ImageView = itemView.findViewById(R.id.repo_contributors_avatar_image_view)
        val titleTextView: TextView = itemView.findViewById(R.id.repo_contributors_title_text_view)
        val detailTextView: TextView = itemView.findViewById(R.id.repo_contributors_detail_text_view)

        init {
            contributorViewHolder.setOnClickListener {
                val contributor = contributorList[adapterPosition]
                val userLogin = contributor.login
                val userBundle = Bundle()
                userBundle.putString(BundleKeys.USER_NAME, userLogin)

                val userActivityIntent = Intent(itemView.context, UserActivity::class.java)
                userActivityIntent.putExtras(userBundle)
                itemView.context.startActivity(userActivityIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorHolder {
        val inflater = LayoutInflater.from(parent.context)
        val contributorView = inflater.inflate(R.layout.view_holder_contributor, parent, false)
        return ContributorHolder(contributorView)
    }

    override fun onBindViewHolder(holder: ContributorHolder, position: Int) {
        val contributor = contributorList[position]
        Glide.with(holder.avatarImageView.context).load(contributor.avatarUrl).into(holder.avatarImageView)
        holder.titleTextView.text = contributor.login
        holder.detailTextView.text = contributor.contributions + " contributions"
    }

    override fun getItemCount(): Int {
        return contributorList.size
    }

}
