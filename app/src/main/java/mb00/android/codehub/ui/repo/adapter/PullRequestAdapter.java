package mb00.android.codehub.ui.repo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.PullRequest;
import mb00.android.codehub.logic.utils.DateParser;
import mb00.android.codehub.ui.repo.view.RepoPullRequestsFragment;

/**
 * RecyclerView adapter used to display pull requests in {@link RepoPullRequestsFragment}
 */

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestHolder> {

    private List<PullRequest> pullRequestList;
    private Context context;

    public PullRequestAdapter(List<PullRequest> pullRequestList, Context context) {
        this.pullRequestList = pullRequestList;
        this.context = context;
    }

    public class PullRequestHolder extends RecyclerView.ViewHolder {

        private ImageView avatarImageView;
        private TextView titleTextView;
        private TextView detailTextView;

        public PullRequestHolder(View itemView) {
            super(itemView);

            avatarImageView = itemView.findViewById(R.id.repo_pull_request_avatar_image_view);
            titleTextView = itemView.findViewById(R.id.repo_pull_request_title_text_view);
            detailTextView = itemView.findViewById(R.id.repo_pull_request_detail_text_view);
        }

    }

    @Override
    public PullRequestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View pullRequestView = inflater.inflate(R.layout.view_holder_pull_request, parent, false);
        return new PullRequestHolder(pullRequestView);
    }

    @Override
    public void onBindViewHolder(PullRequestHolder holder, int position) {
        PullRequest pullRequest = pullRequestList.get(position);
        Glide.with(context).load(pullRequest.getUser().getAvatarUrl()).into(holder.avatarImageView);
        holder.titleTextView.setText(pullRequest.getTitle());
        String details = "#" + pullRequest.getNumber() + " " + DateParser.parseEnglish(pullRequest.getCreationDate());
        holder.detailTextView.setText(details);
    }

    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }

}
