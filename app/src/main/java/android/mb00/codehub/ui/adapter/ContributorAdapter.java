package android.mb00.codehub.ui.adapter;

import android.content.Intent;
import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Contributor;
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


public class ContributorAdapter extends RecyclerView.Adapter<ContributorAdapter.ContributorHolder> {

    private List<Contributor> contributorList;

    public ContributorAdapter(List<Contributor> contributorList) {
        this.contributorList = contributorList;
    }

    public class ContributorHolder extends RecyclerView.ViewHolder {

        private LinearLayout contributorViewHolder;
        private ImageView avatarImageView;
        private TextView titleTextView;
        private TextView detailTextView;

        public ContributorHolder(final View itemView) {
            super(itemView);

            contributorViewHolder = (LinearLayout) itemView.findViewById(R.id.repo_contributor_view_holder);
            avatarImageView = (ImageView) itemView.findViewById(R.id.repo_contributors_avatar_image_view);
            titleTextView = (TextView) itemView.findViewById(R.id.repo_contributors_title_text_view);
            detailTextView = (TextView) itemView.findViewById(R.id.repo_contributors_detail_text_view);

            contributorViewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Contributor contributor = contributorList.get(getAdapterPosition());
                    String userLogin = contributor.getLogin();
                    Bundle userBundle = new Bundle();
                    userBundle.putString(BundleKeys.USER_NAME, userLogin);

                    Intent userActivityIntent = new Intent(itemView.getContext(), UserActivity.class);
                    userActivityIntent.putExtras(userBundle);
                    itemView.getContext().startActivity(userActivityIntent);
                }
            });

            /*
            * userViewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user = userList.get(getAdapterPosition());
                    String userLogin = user.getLogin();
                    userBundle = new Bundle();
                    userBundle.putString(BundleKeys.USER_NAME, userLogin);

                    Intent userActivityIntent = new Intent(itemView.getContext(), UserActivity.class);
                    userActivityIntent.putExtras(userBundle);
                    itemView.getContext().startActivity(userActivityIntent);
                }
            });*/
        }
    }

    @Override
    public ContributorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contributorView = inflater.inflate(R.layout.view_holder_contributor, parent, false);
        return new ContributorHolder(contributorView);
    }

    @Override
    public void onBindViewHolder(ContributorHolder holder, int position) {
        Contributor contributor = contributorList.get(position);
        Glide.with(holder.avatarImageView.getContext()).load(contributor.getAvatarUrl()).into(holder.avatarImageView);
        holder.titleTextView.setText(contributor.getLogin());
        holder.detailTextView.setText(contributor.getContributions() + " contributions");
    }

    @Override
    public int getItemCount() {
        return contributorList.size();
    }

}
