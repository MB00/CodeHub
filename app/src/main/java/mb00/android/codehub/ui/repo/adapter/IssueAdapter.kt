package mb00.android.codehub.ui.repo.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import mb00.android.codehub.R
import mb00.android.codehub.api.model.Issue
import mb00.android.codehub.logic.utils.DateParser
import mb00.android.codehub.ui.repo.view.RepoIssuesFragment
import mb00.android.codehub.ui.search.view.SearchIssuesFragment

/**
 * RecyclerView adapter used to display issues in [RepoIssuesFragment] and [SearchIssuesFragment]
 */

class IssueAdapter(private val issueList: List<Issue>) : RecyclerView.Adapter<IssueAdapter.SearchIssuesHolder>() {

    inner class SearchIssuesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statusImageView: ImageView = itemView.findViewById(R.id.issue_status_image_view)
        val titleTextView: TextView = itemView.findViewById(R.id.issue_title_text_view)
        val dateTextView: TextView = itemView.findViewById(R.id.issue_date_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchIssuesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val issueView = inflater.inflate(R.layout.view_holder_issue, parent, false)
        return SearchIssuesHolder(issueView)
    }

    override fun onBindViewHolder(holder: SearchIssuesHolder, position: Int) {
        val issue = issueList[position]

        if (issue.state == "open") {
            holder.statusImageView.setImageResource(R.drawable.ic_issue_open)
            val openDate = "Opened " + DateParser.parseEnglish(issue.openDate)
            holder.dateTextView.text = openDate
        } else { // "closed"
            holder.statusImageView.setImageResource(R.drawable.ic_issue_closed)
            val closedDate = "Closed " + DateParser.parseEnglish(issue.closeDate)
            holder.dateTextView.text = closedDate
        }
        holder.titleTextView.text = issue.title
    }

    override fun getItemCount(): Int {
        return issueList.size
    }

}
