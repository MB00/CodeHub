package mb00.android.codehub.ui.gist.adapter

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import mb00.android.codehub.R
import mb00.android.codehub.api.model.Gist
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.ui.gist.view.GistActivity
import mb00.android.codehub.ui.user.view.UserGistsFragment

/**
 * A RecyclerView adapter used to display gists in [UserGistsFragment]
 */

class GistAdapter(private val gistList: List<Gist>) : RecyclerView.Adapter<GistAdapter.GistHolder>() {

    inner class GistHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val gistViewHolder: LinearLayout = itemView.findViewById(R.id.gist_view_holder)
        val gistDescriptionTextView: TextView = itemView.findViewById(R.id.gist_description_text_view)
        val gistFileCountTextView: TextView = itemView.findViewById(R.id.gist_file_count_text_view)
        val gistCommentCountTextView: TextView = itemView.findViewById(R.id.gist_comment_count_text_view)
        private var gistBundle: Bundle? = null

        init {
            gistViewHolder.setOnClickListener {
                val gist = gistList[adapterPosition]

                gistBundle = Bundle()
                gistBundle?.putString(BundleKeys.GIST_ID, gist.id)
                gistBundle?.putString(BundleKeys.USER_NAME, gist.owner?.login)

                val gistActivityIntent = Intent(itemView.context, GistActivity::class.java)
                gistActivityIntent.putExtras(gistBundle!!)
                itemView.context.startActivity(gistActivityIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistHolder {
        val inflater = LayoutInflater.from(parent.context)
        val userGistView = inflater.inflate(R.layout.view_holder_gist, parent, false)
        return GistHolder(userGistView)
    }

    override fun onBindViewHolder(holder: GistHolder, position: Int) {
        val gist = gistList[position]

        holder.gistDescriptionTextView.text = when (gist.description?.isNotEmpty()) {
            true -> gist.description
            else -> "No description available"
        }
        holder.gistFileCountTextView.text = gist.files.toString()
        holder.gistCommentCountTextView.text = gist.commentCount.toString()
    }

    override fun getItemCount(): Int {
        return gistList.size
    }

}
