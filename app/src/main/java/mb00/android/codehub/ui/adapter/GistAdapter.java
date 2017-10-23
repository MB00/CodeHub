package mb00.android.codehub.ui.adapter;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Gist;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.GistActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class GistAdapter extends RecyclerView.Adapter<GistAdapter.UserGistHolder> {

    private List<Gist> gistList;

    public GistAdapter(List<Gist> gistList) {
        this.gistList = gistList;
    }

    public class UserGistHolder extends RecyclerView.ViewHolder {

        private LinearLayout gistViewHolder;
        private TextView gistDescriptionTextView;
        private TextView gistFileCountTextView;
        private TextView gistCommentCountTextView;
        private Bundle gistBundle;

        public UserGistHolder(final View itemView) {
            super(itemView);

            gistViewHolder = (LinearLayout) itemView.findViewById(R.id.gist_view_holder);
            gistDescriptionTextView = (TextView) itemView.findViewById(R.id.gist_description_text_view);
            gistFileCountTextView = (TextView) itemView.findViewById(R.id.gist_file_count_text_view);
            gistCommentCountTextView = (TextView) itemView.findViewById(R.id.gist_comment_count_text_view);

            gistViewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gist gist = gistList.get(getAdapterPosition());

                    gistBundle = new Bundle();
                    gistBundle.putString(BundleKeys.GIST_ID, gist.getId());
                    gistBundle.putString(BundleKeys.USER_NAME, gist.getOwner().getLogin());

                    Intent gistActivityIntent = new Intent(itemView.getContext(), GistActivity.class);
                    gistActivityIntent.putExtras(gistBundle);
                    itemView.getContext().startActivity(gistActivityIntent);
                }
            });
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
