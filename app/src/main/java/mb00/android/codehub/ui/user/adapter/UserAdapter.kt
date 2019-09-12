package mb00.android.codehub.ui.user.adapter

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.bumptech.glide.Glide

import mb00.android.codehub.R
import mb00.android.codehub.api.model.User
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.ui.repo.view.RepoContributorsFragment
import mb00.android.codehub.ui.search.view.SearchUsersFragment
import mb00.android.codehub.ui.user.view.UserActivity
import mb00.android.codehub.ui.user.view.UserFollowersFragment
import mb00.android.codehub.ui.user.view.UserFollowingFragment

/**
 * RecyclerView adapter used to display users in [SearchUsersFragment],
 * [RepoContributorsFragment],
 * [UserFollowersFragment],
 * and [UserFollowingFragment]
 */

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val userViewHolder: LinearLayout = itemView.findViewById(R.id.user_view_holder)
        val loginText: TextView = itemView.findViewById(R.id.user_login_text)
        val avatar: ImageView = itemView.findViewById(R.id.user_avatar)

        init {
            userViewHolder.setOnClickListener {
                val user = userList[adapterPosition]
                val userLogin = user.login
                val userBundle = Bundle()
                userBundle.putString(BundleKeys.USER_NAME, userLogin)

                val userActivityIntent = Intent(itemView.context, UserActivity::class.java)
                userActivityIntent.putExtras(userBundle)
                itemView.context.startActivity(userActivityIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        val userView = inflater.inflate(R.layout.view_holder_user, parent, false)
        return UserHolder(userView)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = userList[position]
        val avatarUrl = user.avatarUrl
        Glide.with(holder.avatar.context).load(avatarUrl).into(holder.avatar)
        holder.loginText.text = user.login
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}
