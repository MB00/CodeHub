package android.mb00.codehub.ui.adapter;

import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Gist;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class UserGistsAdapter extends RecyclerView.Adapter<UserGistsAdapter.UserGistHolder> {

    private List<Gist> gistList;

    public UserGistsAdapter(List<Gist> gistList) {
        this.gistList = gistList;
    }

    public class UserGistHolder extends RecyclerView.ViewHolder {

        private TextView gistDescriptionTextView;
        private TextView gistFileCountTextView;
        private TextView gistCommentCountTextView;

        public UserGistHolder(View itemView) {
            super(itemView);

            gistDescriptionTextView = (TextView) itemView.findViewById(R.id.gist_description_text_view);
            gistFileCountTextView = (TextView) itemView.findViewById(R.id.gist_file_count_text_view);
            gistCommentCountTextView = (TextView) itemView.findViewById(R.id.gist_comment_count_text_view);
        }
    }

    @Override
    public UserGistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View userGistView = inflater.inflate(R.layout.view_holder_gist, parent, false);
        return new UserGistHolder(userGistView);
    }

    @Override
    public void onBindViewHolder(UserGistHolder holder, int position) {
        Gist gist = gistList.get(position);
        holder.gistDescriptionTextView.setText(gist.getDescription());
        holder.gistFileCountTextView.setText(gist.getFiles().toString());
        holder.gistCommentCountTextView.setText(String.valueOf(gist.getCommentCount()));
    }

    @Override
    public int getItemCount() {
        return gistList.size();
    }

}
