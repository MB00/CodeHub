package mb00.android.codehub.ui.universaladapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import mb00.android.codehub.R
import mb00.android.codehub.api.model.Pulse
import mb00.android.codehub.logic.utils.DateParser
import mb00.android.codehub.ui.repo.view.RepoPulseFragment
import mb00.android.codehub.ui.user.view.UserPulseFragment

/**
 * RecyclerView adapter used to display pulse in [UserPulseFragment] and [RepoPulseFragment]
 */

class PulseAdapter(private val pulseList: List<Pulse>, private val context: Context) : RecyclerView.Adapter<PulseAdapter.PulseHolder>() {

    inner class PulseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pulseAvatarImageView: ImageView = itemView.findViewById(R.id.pulse_avatar_image_view)
        val pulseTitleTextView: TextView = itemView.findViewById(R.id.pulse_title_text_view)
        val pulseTimeTextView: TextView = itemView.findViewById(R.id.pulse_time_text_view)
    }

    private fun getActionType(pulse: Pulse): String {
        val repoName = pulse.repo?.name
        val issueNumber: String

        when (pulse.type) {
            "WatchEvent" -> return " starred $repoName"
            "IssuesEvent" -> {
                issueNumber = pulse.payload?.issue?.number!!
                return " opened issue $issueNumber on $repoName" // closing issues to be added - need to check action
            }
            "IssueCommentEvent" -> {
                issueNumber = pulse.payload?.issue?.number!!
                return " commented on issue $issueNumber on $repoName"
            }
            "PushEvent" -> return " pushed to $repoName" // more detail to be added
            "PullRequestEvent" -> return " pulled from $repoName" // more detail to be added
            "ForkEvent" -> return " forked repository $repoName"
            "CreateEvent" -> return " created repository $repoName"
            else -> return ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PulseHolder {
        val inflater = LayoutInflater.from(parent.context)
        val pulseView = inflater.inflate(R.layout.view_holder_pulse, parent, false)
        return PulseHolder(pulseView)
    }

    override fun onBindViewHolder(holder: PulseHolder, position: Int) {
        val pulse = pulseList[position]
        Glide.with(context).load(pulse.actor?.avatarUrl).into(holder.pulseAvatarImageView)
        holder.pulseTitleTextView.text = pulse.actor?.login + getActionType(pulse)
        val creationDate = DateParser.parseEnglish(pulse.creationDate)
        holder.pulseTimeTextView.text = creationDate
    }

    override fun getItemCount(): Int {
        return pulseList.size
    }

}
