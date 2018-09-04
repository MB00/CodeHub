package mb00.android.codehub.ui.gist.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.GistFile;
import mb00.android.codehub.logic.utils.FileSizeParser;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.gist.view.GistFileActivity;
import mb00.android.codehub.ui.gist.view.GistFilesFragment;

/**
 * RecyclerView adapter used to display gist files in {@link GistFilesFragment}
 */

public class GistFileAdapter extends RecyclerView.Adapter<GistFileAdapter.GistFileHolder> {

    private List<GistFile> fileList;
    private RecyclerView fileRecyclerView;
    private String header;
    private String gist;

    public GistFileAdapter(List<GistFile> fileList, RecyclerView fileRecyclerView, String header, String gist) {
        this.fileList = fileList;
        this.fileRecyclerView = fileRecyclerView;
        this.header = header;
        this.gist = gist;
    }

    public class GistFileHolder extends RecyclerView.ViewHolder {

        private LinearLayout gistFileViewHolder;
        private TextView gistFileTextView;
        private TextView gistFileSizeTextView;

        public GistFileHolder(View itemView) {
            super(itemView);

            gistFileViewHolder = itemView.findViewById(R.id.gist_file_view_holder);
            gistFileTextView = itemView.findViewById(R.id.gist_file_text_view);
            gistFileSizeTextView = itemView.findViewById(R.id.gist_file_size_text_view);

            gistFileViewHolder.setOnClickListener(view -> {
                GistFile gistFile = fileList.get(getAdapterPosition());
                String gistFileName = gistFile.getFileName();
                String gistFileContent = gistFile.getContent();
                Bundle gistFileBundle = new Bundle();
                gistFileBundle.putString(BundleKeys.FILE_NAME, gistFileName);
                gistFileBundle.putString(BundleKeys.FILE_CONTENT, gistFileContent);

                Intent gistFileActivityIntent = new Intent(view.getContext(), GistFileActivity.class);
                gistFileActivityIntent.putExtras(gistFileBundle);
                view.getContext().startActivity(gistFileActivityIntent);
            });
        }

    }

    @Override
    public GistFileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View gistFileView = inflater.inflate(R.layout.view_holder_gist_file, parent, false);
        return new GistFileHolder(gistFileView);
    }

    @Override
    public void onBindViewHolder(GistFileHolder holder, int position) {
        GistFile gistFile = fileList.get(position);
        holder.gistFileTextView.setText(gistFile.getFileName());
        holder.gistFileSizeTextView.setText(FileSizeParser.parseSize(gistFile.getSize()));
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

}
