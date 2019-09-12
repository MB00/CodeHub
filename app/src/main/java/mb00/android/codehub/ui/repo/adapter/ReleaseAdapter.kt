package mb00.android.codehub.ui.repo.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import mb00.android.codehub.R
import mb00.android.codehub.api.model.Release
import mb00.android.codehub.logic.utils.DateParser


// not currently used

class ReleaseAdapter(
        private val releaseList: List<Release>,
        private val context: Context
) : RecyclerView.Adapter<ReleaseAdapter.ReleaseHolder>() {

    inner class ReleaseHolder
    //private ImageButton releaseDownloadButton;

    (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val releaseAvatarImageView: ImageView = itemView.findViewById(R.id.repo_release_avatar_image_view)
        val releaseTitleTextView: TextView = itemView.findViewById(R.id.repo_release_title_text_view)
        val releaseDetailTextView: TextView = itemView.findViewById(R.id.repo_release_detail_text_view)

        init {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReleaseHolder {
        val inflater = LayoutInflater.from(parent.context)
        val releaseView = inflater.inflate(R.layout.view_holder_release, parent, false)
        return ReleaseHolder(releaseView)
    }

    override fun onBindViewHolder(holder: ReleaseHolder, position: Int) {
        val release = releaseList[position]
        Glide.with(context).load(release.author?.avatarUrl).into(holder.releaseAvatarImageView)
        holder.releaseTitleTextView.text = release.name
        val details = release.author?.login + " " + DateParser.parseEnglish(release.publicationDate)
        holder.releaseDetailTextView.text = details
    }

    override fun getItemCount(): Int {
        return releaseList.size
    }

}
