package mb00.android.codehub.ui.adapter;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Code;
import mb00.android.codehub.api.model.GistFile;
import mb00.android.codehub.api.parser.FileSizeParser;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.RepoFileActivity;
import mb00.android.codehub.ui.RepoCodeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;


public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.CodeHolder> {

    private List<Code> fileList;
    private RecyclerView fileRecyclerView;
    private String header;
    private String user;
    private String repo;

    public CodeAdapter(List<Code> fileList, RecyclerView fileRecyclerView, String header, String user, String repo) {
        this.fileList = fileList;
        this.fileRecyclerView = fileRecyclerView;
        this.header = header;
        this.user = user;
        this.repo = repo;
    }

    public class CodeHolder extends RecyclerView.ViewHolder {

        private LinearLayout codeViewHolder;
        private ImageView codeTypeImageView;
        private TextView codeTextView;
        private TextView codeSizeTextView;

        public CodeHolder(final View itemView) {
            super(itemView);

            codeViewHolder = (LinearLayout) itemView.findViewById(R.id.code_view_holder);
            codeTypeImageView = (ImageView) itemView.findViewById(R.id.code_type_image_view);
            codeTextView = (TextView) itemView.findViewById(R.id.code_text_view);
            codeSizeTextView = (TextView) itemView.findViewById(R.id.code_size_text_view);

            codeViewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Code code = fileList.get(getAdapterPosition());
                    if (code.getType().equals("dir")) {
                        String path = code.getPath();
                        RepoCodeFragment.repoCodeCall(fileRecyclerView, header, user, repo, path);
                        RepoCodeFragment.displayPathAsViewObjects(path, view.getContext(), (ViewGroup) itemView.getParent());
                    } else { // code.getType().equals("file)
                        Bundle fileBundle = new Bundle();
                        fileBundle.putString(BundleKeys.USER_NAME, user);
                        fileBundle.putString(BundleKeys.REPO_NAME, repo);
                        fileBundle.putString(BundleKeys.FILE_NAME, code.getName());
                        fileBundle.putString(BundleKeys.FILE_PATH, code.getPath());

                        Intent fileIntent = new Intent(itemView.getContext(), RepoFileActivity.class);
                        fileIntent.putExtras(fileBundle);
                        itemView.getContext().startActivity(fileIntent);
                    }
                }
            });
        }

    }

    @Override
    public CodeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View codeView = inflater.inflate(R.layout.view_holder_code, parent, false);
        return new CodeHolder(codeView);
    }

    @Override
    public void onBindViewHolder(CodeHolder holder, int position) {
        Code code = fileList.get(position);

        if (code.getType().equals("dir")) {
            holder.codeTypeImageView.setImageResource(R.drawable.ic_directory);
            holder.codeSizeTextView.setVisibility(View.GONE);
        } else { // code.getType().equals("file")
            holder.codeTypeImageView.setImageResource(R.drawable.ic_file);
            holder.codeSizeTextView.setText(FileSizeParser.parseSize(code.getSize()));
        }
        holder.codeTextView.setText(code.getName());
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

}
