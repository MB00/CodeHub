package mb00.android.codehub.ui.repo.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Repo;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.LanguageColor;
import mb00.android.codehub.ui.repo.view.RepoActivity;
import mb00.android.codehub.ui.search.view.SearchReposFragment;
import mb00.android.codehub.ui.user.view.UserReposFragment;

/**
 * RecyclerView adapter used to display repositories in {@link UserReposFragment} and {@link SearchReposFragment}
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.SearchReposHolder> {

    //==============================================================================================
    // RepoAdapter fields
    //==============================================================================================

    private List<Repo> repoList;

    //==============================================================================================
    // RepoAdapter constructor
    //==============================================================================================

    public RepoAdapter(List<Repo> repoList) {
        this.repoList = repoList;
    }

    //==============================================================================================
    // ViewHolder inner class
    //==============================================================================================

    public class SearchReposHolder extends RecyclerView.ViewHolder {

        //==========================================================================================
        // SearchReposHolder fields
        //==========================================================================================

        private LinearLayout repoViewHolder;
        private TextView nameText;
        private TextView descriptionText;
        private ImageView languageColorImage;
        private TextView languageText;
        private TextView stargazersCountText;
        private TextView forksCountText;
        private Bundle repoBundle;

        //==========================================================================================
        // SearchReposHolder constructor
        //==========================================================================================

        public SearchReposHolder(final View itemView) {
            super(itemView);

            repoViewHolder = itemView.findViewById(R.id.repo_view_holder);
            nameText = itemView.findViewById(R.id.repo_name_text_view);
            descriptionText = itemView.findViewById(R.id.repo_description_text_view);
            languageColorImage = itemView.findViewById(R.id.repo_language_color_image_view);
            languageText = itemView.findViewById(R.id.repo_language_text_view);
            stargazersCountText = itemView.findViewById(R.id.repo_stargazers_count_text_view);
            forksCountText = itemView.findViewById(R.id.repo_forks_count_text_view);

            repoViewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Repo repo = repoList.get(getAdapterPosition());
                    String userLogin = repo.getOwner().getLogin();
                    String repoName = repo.getName();
                    String repoFullName = repo.getFullName();
                    repoBundle = new Bundle();
                    repoBundle.putString(BundleKeys.USER_NAME, userLogin);
                    repoBundle.putString(BundleKeys.REPO_NAME, repoName);
                    repoBundle.putString(BundleKeys.REPO_FULL_NAME, repoFullName);

                    Intent repoActivityIntent = new Intent(itemView.getContext(), RepoActivity.class);
                    repoActivityIntent.putExtras(repoBundle);
                    itemView.getContext().startActivity(repoActivityIntent);
                }
            });
        }

    }

    //==============================================================================================
    // RecyclerView.Adapter methods
    //==============================================================================================

    @Override
    public SearchReposHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View repoView = inflater.inflate(R.layout.view_holder_repo, parent, false);

        return new SearchReposHolder(repoView);
    }

    @Override
    public void onBindViewHolder(SearchReposHolder holder, int position) {
        Repo repo = repoList.get(position);

        holder.nameText.setText(repo.getName());
        holder.descriptionText.setText(repo.getDescription());
        holder.languageColorImage.setImageResource(R.drawable.ic_dot);
        holder.languageText.setText(repo.getLanguage());

        if (repo.getLanguage() != null) {
            holder.languageColorImage.setColorFilter(LanguageColor.getColor(repo.getLanguage()));
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
        return repoList.size();
    }

}
