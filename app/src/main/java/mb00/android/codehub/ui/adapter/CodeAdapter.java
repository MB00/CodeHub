package mb00.android.codehub.ui.adapter;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Code;
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

    private List<Code> codeList;
    private RecyclerView codeRecyclerView;
    private String header;
    private String user;
    private String repo;

    public CodeAdapter(List<Code> codeList, RecyclerView codeRecyclerView, String header, String user, String repo) {
        this.codeList = codeList;
        this.codeRecyclerView = codeRecyclerView;
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
                    Code code = codeList.get(getAdapterPosition());
                    if (code.getType().equals("dir")) {
                        String path = code.getPath();
                        RepoCodeFragment.repoCodeCall(codeRecyclerView, header, user, repo, path);
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


    private String parseSize(int size) {
        String sizeText = String.valueOf(size);

        if (sizeText.length() <= 3) { // size in bytes
            sizeText += "B";
            return sizeText;
        } else if (sizeText.length() == 4) { // size in kilobytes
            String kilobytesInteger = sizeText.substring(0, 1);
            String kilobytesFractional = sizeText.substring(1);
            String sizeInKilobytes = kilobytesInteger + "." + kilobytesFractional;
            sizeText = new BigDecimal(sizeInKilobytes).round(new MathContext(2)).toString() + "KB";
            return sizeText;
        } else if (sizeText.length() == 5) { // size in kilobytes
            String kilobytesInteger = sizeText.substring(0, 2);
            String kilobytesFractional = sizeText.substring(2);
            String sizeInKilobytes = kilobytesInteger + "." + kilobytesFractional;
            sizeText = new BigDecimal(sizeInKilobytes).round(new MathContext(2)).toString() + "KB";
            return sizeText;
        } else if (sizeText.length() == 6){ // size in megabytes;
            String megabytesInteger = sizeText.substring(0, 1);
            String megabytesFractional = sizeText.substring(1);
            String sizeInMegabytes = megabytesInteger + "." + megabytesFractional;
            sizeText = new BigDecimal(sizeInMegabytes).round(new MathContext(2)).toString() + "MB";
            return sizeText;
        } else { // some horrific edge case
            return sizeText;
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
        Code code = codeList.get(position);

        if (code.getType().equals("dir")) {
            holder.codeTypeImageView.setImageResource(R.drawable.ic_directory);
            holder.codeSizeTextView.setVisibility(View.GONE);
        } else { // code.getType().equals("file")
            holder.codeTypeImageView.setImageResource(R.drawable.ic_file);
            holder.codeSizeTextView.setText(parseSize(code.getSize()));
        }
        holder.codeTextView.setText(code.getName());
    }

    @Override
    public int getItemCount() {
        return codeList.size();
    }

}
