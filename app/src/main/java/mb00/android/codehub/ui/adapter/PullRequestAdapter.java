package mb00.android.codehub.ui.adapter;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.PullRequest;
import mb00.android.codehub.api.parser.DateParser;
import mb00.android.codehub.ui.RepoPullRequestsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * RecyclerView adapter used to display pull requests in {@link RepoPullRequestsFragment}
 */

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestHolder> {

    //==============================================================================================
    // PullRequestAdapter fields
    //==============================================================================================

    private List<PullRequest> pullRequestList;
    private Context context;

    //==============================================================================================
    // PullRequestAdapter constructor
    //==============================================================================================

    public PullRequestAdapter(List<PullRequest> pullRequestList, Context context) {
        this.pullRequestList = pullRequestList;
        this.context = context;
    }

    //==============================================================================================
    // ViewHolder inner class
    //==============================================================================================

    public class PullRequestHolder extends RecyclerView.ViewHolder {

        //==========================================================================================
        // PullRequestHolder fields
        //==========================================================================================

        private ImageView avatarImageView;
        private TextView titleTextView;
        private TextView detailTextView;

        //==========================================================================================
        // PullRequestHolder constructor
        //==========================================================================================

        public PullRequestHolder(View itemView) {
            super(itemView);

            avatarImageView = (ImageView) itemView.findViewById(R.id.repo_pull_request_avatar_image_view);
            titleTextView = (TextView) itemView.findViewById(R.id.repo_pull_request_title_text_view);
            detailTextView = (TextView) itemView.findViewById(R.id.repo_pull_request_detail_text_view);
        }

    }

    //==============================================================================================
    // RecyclerView.Adapter methods
    //==============================================================================================

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
