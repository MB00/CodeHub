package android.mb00.codehub.ui.adapter;

import android.content.Intent;
import android.mb00.codehub.R;
import android.mb00.codehub.api.model.User;
import android.mb00.codehub.data.BundleKeys;
import android.mb00.codehub.ui.UserActivity;
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


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.SearchUsersHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public class SearchUsersHolder extends RecyclerView.ViewHolder {

        private LinearLayout userViewHolder;
        private TextView loginText;
        private ImageView avatar;

        public SearchUsersHolder(final View itemView) {
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

    @Override
    public SearchUsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View userView = inflater.inflate(R.layout.view_holder_user, parent, false);
        return new SearchUsersHolder(userView);
    }

    @Override
    public void onBindViewHolder(SearchUsersHolder holder, int position) {
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
