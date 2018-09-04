package mb00.android.codehub.ui.repo.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Commit;
import mb00.android.codehub.logic.utils.DateParser;
import mb00.android.codehub.ui.repo.view.RepoCommitsFragment;

/**
 * A RecyclerView adapter used to display commits in {@link RepoCommitsFragment}
 */

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.CommitHolder> {

    private List<Commit> commitList;
    private Context context;

    public CommitAdapter(List<Commit> commitList, Context context) {
        this.commitList = commitList;
        this.context = context;
    }

    public class CommitHolder extends RecyclerView.ViewHolder {

        private ImageView commitAvatarImageView;
        private TextView commitTitleTextView;
        private TextView commitDetailTextView;

        public CommitHolder(View itemView) {
            super(itemView);

            commitAvatarImageView = itemView.findViewById(R.id.repo_commit_avatar_image_view);
            commitTitleTextView = itemView.findViewById(R.id.repo_commit_title_text_view);
            commitDetailTextView = itemView.findViewById(R.id.repo_commit_detail_text_view);
        }
    }

    @Override
    public CommitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View commitView = inflater.inflate(R.layout.view_holder_commit, parent, false);
        return new CommitHolder(commitView);
    }

    @Override
    public void onBindViewHolder(CommitHolder holder, int position) {
        Commit commit = commitList.get(position);
        holder.commitTitleTextView.setText(commit.getCommitNested().getMessage());
        String details = commit.getCommitNested().getAuthor().getName() + " " + DateParser.parseEnglish(commit.getCommitNested().getAuthor().getDate());
        holder.commitDetailTextView.setText(details);

        try {
            Glide.with(context).load(commit.getAuthor().getAvatarUrl()).into(holder.commitAvatarImageView);
        } catch (NullPointerException npe) {
            Context context = holder.commitAvatarImageView.getContext();
            holder.commitAvatarImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_github));
        }
    }

    @Override
    public int getItemCount() {
        return commitList.size();
    }

}
