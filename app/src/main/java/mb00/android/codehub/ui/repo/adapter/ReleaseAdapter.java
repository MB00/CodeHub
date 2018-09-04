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
import mb00.android.codehub.api.model.Release;
import mb00.android.codehub.logic.utils.DateParser;


// not currently used

public class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.ReleaseHolder> {

    private List<Release> releaseList;
    private Context context;

    public ReleaseAdapter(List<Release> releaseList, Context context) {
        this.releaseList = releaseList;
        this.context = context;
    }

    public class ReleaseHolder extends RecyclerView.ViewHolder {

        private ImageView releaseAvatarImageView;
        private TextView releaseTitleTextView;
        private TextView releaseDetailTextView;
        //private ImageButton releaseDownloadButton;

        public ReleaseHolder(final View itemView) {
            super(itemView);

            releaseAvatarImageView = itemView.findViewById(R.id.repo_release_avatar_image_view);
            releaseTitleTextView = itemView.findViewById(R.id.repo_release_title_text_view);
            releaseDetailTextView = itemView.findViewById(R.id.repo_release_detail_text_view);
            //releaseDownloadButton = (ImageButton) itemView.findViewById(R.id.repo_release_download_button);

            /*releaseDownloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View downloadView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_release_download, (ViewGroup) view.getParent(), false);

                    AlertDialog downloadDialog = new AlertDialog.Builder(itemView.getContext())
                            .setTitle("Download release source code")
                            .setView(downloadView)
                            .create();
                    downloadDialog.show();

                    Release release = releaseList.get(getAdapterPosition());
                    final String zipDownloadPath = release.getZipballUrl();
                    final String tarDownloadPath = release.getTarballUrl();

                    Button zipDownloadButton = (Button) downloadView.findViewById(R.id.zip_download_button);
                    Button tarDownloadButton = (Button) downloadView.findViewById(R.id.tar_download_button);

                    zipDownloadButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            releaseDownloadCall(zipDownloadPath);
                        }
                    });

                    tarDownloadButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            releaseDownloadCall(tarDownloadPath);
                        }
                    });
                }
            });*/
        }
    }

    /*private void releaseDownloadCall(String downloadPath) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
    }*/

    @Override
    public ReleaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View releaseView = inflater.inflate(R.layout.view_holder_release, parent, false);
        return new ReleaseHolder(releaseView);
    }

    @Override
    public void onBindViewHolder(ReleaseHolder holder, int position) {
        Release release = releaseList.get(position);
        Glide.with(context).load(release.getAuthor().getAvatarUrl()).into(holder.releaseAvatarImageView);
        holder.releaseTitleTextView.setText(release.getName());
        String details = release.getAuthor().getLogin() + " " + DateParser.parseEnglish(release.getPublicationDate());
        holder.releaseDetailTextView.setText(details);
    }

    @Override
    public int getItemCount() {
        return releaseList.size();
    }

}
