package android.mb00.codehub.ui.adapter;

import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Repo;
import android.mb00.codehub.ui.*;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import retrofit2.Retrofit;


public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.GithubRepoHolder> {

    private String TAG = "tag";

    private String user;
    private List<Repo> repoList;

    public GitHubRepoAdapter(Retrofit retrofit, String user, List<Repo> repoList) {
        this.user = user;
        this.repoList = repoList;
    }

    public class GithubRepoHolder extends RecyclerView.ViewHolder {

        private TextView nameText;
        private TextView descriptionText;
        private ImageView languageColorImage;
        private TextView languageText;
        private ImageView stargazersImage;
        private TextView stargazersCountText;
        private ImageView forksImage;
        private TextView forksCountText;
        private LinearLayout repoViewHolder;

        public GithubRepoHolder(View itemView) {
            super(itemView);

            nameText = (TextView) itemView.findViewById(R.id.repo_name_text_view);
            descriptionText = (TextView) itemView.findViewById(R.id.repo_description_text_view);
            languageColorImage = (ImageView) itemView.findViewById(R.id.repo_language_color_image_view);
            languageText = (TextView) itemView.findViewById(R.id.repo_language_text_view);
            stargazersImage = (ImageView) itemView.findViewById(R.id.repo_stargazers_image_view);
            stargazersCountText = (TextView) itemView.findViewById(R.id.repo_stargazers_count_text_view);
            forksImage = (ImageView) itemView.findViewById(R.id.repo_forked_image_view);
            forksCountText = (TextView) itemView.findViewById(R.id.repo_forks_count_text_view);
            repoViewHolder = (LinearLayout) itemView.findViewById(R.id.repo_view_holder);

            repoViewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String repoName = nameText.getText().toString();
                }
            });
        }
    }

    @Override
    public GithubRepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "view_holder_repo created");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View layout = inflater.inflate(R.layout.view_holder_repo, parent, false);

        return new GithubRepoHolder(layout);
    }

    @Override
    public void onBindViewHolder(GithubRepoHolder holder, int position) {
        Log.d(TAG, "view_holder_repo bound");
        Repo repo = repoList.get(position);
        holder.nameText.setText(repo.getName());
        holder.descriptionText.setText(repo.getDescription());
        holder.languageColorImage.setImageResource(R.drawable.ic_dot);
        holder.languageText.setText(repo.getLanguage());

        if (repo.getLanguage() != null) {
            LanguageColor languageColor = new LanguageColor(repo.getLanguage());
            holder.languageColorImage.setColorFilter(languageColor.color);
        } else {
            holder.languageColorImage.setVisibility(View.GONE);
        }
        if (repo.getDescription() == null) {
            holder.descriptionText.setVisibility(View.GONE);
        }
        holder.stargazersCountText.setText(repo.getStargazersCount());
        holder.forksCountText.setText(repo.getForksCount());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return repoList.size();
    }

}
