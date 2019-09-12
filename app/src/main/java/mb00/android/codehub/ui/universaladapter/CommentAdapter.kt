package mb00.android.codehub.ui.universaladapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.bumptech.glide.Glide

import mb00.android.codehub.R
import mb00.android.codehub.api.model.Comment
import mb00.android.codehub.logic.utils.DateParser
import mb00.android.codehub.logic.utils.MarkdownParser
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.ui.gist.view.GistCommentsFragment
import mb00.android.codehub.ui.user.view.UserActivity

/**
 * A RecyclerView adapter used to display comments in [GistCommentsFragment]
 */

class CommentAdapter(private val commentList: List<Comment>, private val context: Context) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {

    inner class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val commenterLayout: LinearLayout = itemView.findViewById(R.id.gist_commenter_layout)
        val commentAvatarImageView: ImageView = itemView.findViewById(R.id.gist_comment_avatar_image_view)
        val commentUsernameTextView: TextView = itemView.findViewById(R.id.gist_comment_username_text_view)
        val commentDateTextView: TextView = itemView.findViewById(R.id.gist_comment_date_text_view)
        val commentBodyTextView: TextView = itemView.findViewById(R.id.gist_comment_body_text_view)

        init {
            commenterLayout.setOnClickListener {
                val comment = commentList[adapterPosition]
                val userLogin = comment.user?.login
                val userBundle = Bundle()
                userBundle.putString(BundleKeys.USER_NAME, userLogin)

                val userActivityIntent = Intent(itemView.context, UserActivity::class.java)
                userActivityIntent.putExtras(userBundle)
                itemView.context.startActivity(userActivityIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val inflater = LayoutInflater.from(parent.context)
        val commentView = inflater.inflate(R.layout.view_holder_comment, parent, false)
        return CommentHolder(commentView)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val comment = commentList[position]
        val commentBody = MarkdownParser.parseMarkdown(comment.body)

        Glide.with(context).load(comment.user?.avatarUrl).into(holder.commentAvatarImageView)
        holder.commentUsernameTextView.text = comment.user?.login
        holder.commentDateTextView.text = DateParser.parseEnglish(comment.creationDate)
        holder.commentBodyTextView.text = Html.fromHtml(commentBody)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

}
