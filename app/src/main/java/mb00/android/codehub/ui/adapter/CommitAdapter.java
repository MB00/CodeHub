package mb00.android.codehub.ui.adapter;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Commit;
import mb00.android.codehub.api.parser.DateParser;
import mb00.android.codehub.ui.RepoCommitsFragment;

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
 * A RecyclerView adapter used to display commits in {@link RepoCommitsFragment}
 */

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.CommitHolder> {

    //==============================================================================================
    // CommitAdapter fields
    //==============================================================================================

    private List<Commit> commitList;
    private Context context;

    //==============================================================================================
    // CommitAdapter constructor
    //==============================================================================================

    public CommitAdapter(List<Commit> commitList, Context context) {
        this.commitList = commitList;
        this.context = context;
    }

    //==============================================================================================
    // ViewHolder inner class
    //==============================================================================================

    public class CommitHolder extends RecyclerView.ViewHolder {

        //==========================================================================================
        // CommitHolder fields
        //==========================================================================================

        private ImageView commitAvatarImageView;
        private TextView commitTitleTextView;
        private TextView commitDetailTextView;

        //==========================================================================================
        // CommitHolder constructor
        //==========================================================================================

        public CommitHolder(View itemView) {
            super(itemView);

            commitAvatarImageView = (ImageView) itemView.findViewById(R.id.repo_commit_avatar_image_view);
            commitTitleTextView = (TextView) itemView.findViewById(R.id.repo_commit_title_text_view);
            commitDetailTextView = (TextView) itemView.findViewById(R.id.repo_commit_detail_text_view);
        }
    }

    //==============================================================================================
    // RecyclerView.Adapter methods
    //==============================================================================================

    @Override
    public CommitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View commitView = inflater.inflate(R.layout.view_holder_commit, parent, false);
        return new CommitHolder(commitView);
    }

    @Override
    public void onBindViewHolder(CommitHolder holder, int position) {
        Commit commit = commitList.get(position);
        Glide.with(context).load(commit.getAuthor().getAvatarUrl()).into(holder.commitAvatarImageView);
        holder.commitTitleTextView.setText(commit.getCommitNested().getMessage());
        String details = commit.getAuthor().getLogin() + " " + DateParser.parseEnglish(commit.getCommitNested().getAuthor().getDate());
        holder.commitDetailTextView.setText(details);
    }

    @Override
    public int getItemCount() {
        return commitList.size();
    }

}
