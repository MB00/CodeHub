package mb00.android.codehub.ui.user.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.repo.view.RepoContributorsFragment;
import mb00.android.codehub.ui.search.view.SearchUsersFragment;
import mb00.android.codehub.ui.user.view.UserActivity;
import mb00.android.codehub.ui.user.view.UserFollowersFragment;
import mb00.android.codehub.ui.user.view.UserFollowingFragment;

/**
 * RecyclerView adapter used to display users in {@link SearchUsersFragment},
 *                                               {@link RepoContributorsFragment},
 *                                               {@link UserFollowersFragment},
 *                                           and {@link UserFollowingFragment}
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        private LinearLayout userViewHolder;
        private TextView loginText;
        private ImageView avatar;

        public UserHolder(final View itemView) {
            super(itemView);
            userViewHolder = itemView.findViewById(R.id.user_view_holder);
            loginText = itemView.findViewById(R.id.user_login_text);
            avatar = itemView.findViewById(R.id.user_avatar);

            userViewHolder.setOnClickListener(view -> {
                User user = userList.get(getAdapterPosition());
                String userLogin = user.getLogin();
                Bundle userBundle = new Bundle();
                userBundle.putString(BundleKeys.USER_NAME, userLogin);

                Intent userActivityIntent = new Intent(itemView.getContext(), UserActivity.class);
                userActivityIntent.putExtras(userBundle);
                itemView.getContext().startActivity(userActivityIntent);
            });
        }
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View userView = inflater.inflate(R.layout.view_holder_user, parent, false);
        return new UserHolder(userView);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User user = userList.get(position);
        String avatarUrl = user.getAvatarUrl();
        Glide.with(holder.avatar.getContext()).load(avatarUrl).into(holder.avatar);
        holder.loginText.setText(user.getLogin());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}
