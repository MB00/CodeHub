package mb00.android.codehub.ui.adapter;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.RepoContributorsFragment;
import mb00.android.codehub.ui.SearchUsersFragment;
import mb00.android.codehub.ui.UserActivity;
import mb00.android.codehub.ui.UserFollowersFragment;
import mb00.android.codehub.ui.UserFollowingFragment;

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

/**
 * RecyclerView adapter used to display users in {@link SearchUsersFragment},
 *                                               {@link RepoContributorsFragment},
 *                                               {@link UserFollowersFragment},
 *                                           and {@link UserFollowingFragment}
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    //==============================================================================================
    // UserAdapter fields
    //==============================================================================================

    private List<User> userList;

    //==============================================================================================
    // UserAdapter constructor
    //==============================================================================================

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    //==============================================================================================
    // ViewHolder inner class
    //==============================================================================================

    public class UserHolder extends RecyclerView.ViewHolder {

        //==========================================================================================
        // UserHolder fields
        //==========================================================================================

        private LinearLayout userViewHolder;
        private TextView loginText;
        private ImageView avatar;

        //==========================================================================================
        // UserHolder constructor
        //==========================================================================================

        public UserHolder(final View itemView) {
            super(itemView);
            userViewHolder = (LinearLayout) itemView.findViewById(R.id.user_view_holder);
            loginText = (TextView) itemView.findViewById(R.id.user_login_text);
            avatar = (ImageView) itemView.findViewById(R.id.user_avatar);

            userViewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user = userList.get(getAdapterPosition());
                    String userLogin = user.getLogin();
                    Bundle userBundle = new Bundle();
                    userBundle.putString(BundleKeys.USER_NAME, userLogin);

                    Intent userActivityIntent = new Intent(itemView.getContext(), UserActivity.class);
                    userActivityIntent.putExtras(userBundle);
                    itemView.getContext().startActivity(userActivityIntent);
                }
            });
        }
    }

    //==============================================================================================
    // RecyclerView.Adapter methods
    //==============================================================================================

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
