package mb00.android.codehub.ui.universaladapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Comment;
import mb00.android.codehub.api.parser.DateParser;
import mb00.android.codehub.api.parser.MarkdownParser;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.gist.view.GistCommentsFragment;
import mb00.android.codehub.ui.user.view.UserActivity;

/**
 * A RecyclerView adapter used to display comments in {@link GistCommentsFragment}
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder>{

    //==============================================================================================
    // CommentAdapter fields
    //==============================================================================================

    private List<Comment> commentList;
    private Context context;

    //==============================================================================================
    // CommentAdapter constructor
    //==============================================================================================

    public CommentAdapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    //==============================================================================================
    // ViewHolder inner class
    //==============================================================================================

    public class CommentHolder extends RecyclerView.ViewHolder {

        //==========================================================================================
        // CommentHolder fields
        //==========================================================================================

        private LinearLayout commenterLayout;
        private ImageView commentAvatarImageView;
        private TextView commentUsernameTextView;
        private TextView commentDateTextView;
        private TextView commentBodyTextView;

        //==========================================================================================
        // CommentHolder constructor
        //==========================================================================================

        public CommentHolder(final View itemView) {
            super(itemView);

            commenterLayout = (LinearLayout) itemView.findViewById(R.id.gist_commenter_layout);
            commentAvatarImageView = (ImageView) itemView.findViewById(R.id.gist_comment_avatar_image_view);
            commentUsernameTextView = (TextView) itemView.findViewById(R.id.gist_comment_username_text_view);
            commentDateTextView = (TextView) itemView.findViewById(R.id.gist_comment_date_text_view);
            commentBodyTextView = (TextView) itemView.findViewById(R.id.gist_comment_body_text_view);

            commenterLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Comment comment = commentList.get(getAdapterPosition());
                    String userLogin = comment.getUser().getLogin();
                    Bundle userBundle = new Bundle();
                    userBundle.putString(BundleKeys.USER_NAME, userLogin);

                    Intent userActivityIntent = new Intent(itemView.getContext(), UserActivity.class);
                    userActivityIntent.putExtras(userBundle);
                    itemView.getContext().startActivity(userActivityIntent);
                }
            });
        }
    }

    //==============================================================================================
    // RecyclerView.Adapter methods
    //==============================================================================================

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View commentView = inflater.inflate(R.layout.view_holder_comment, parent, false);
        return new CommentHolder(commentView);
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        Comment comment = commentList.get(position);
        String commentBody = MarkdownParser.parseMarkdown(comment.getBody());

        Glide.with(context).load(comment.getUser().getAvatarUrl()).into(holder.commentAvatarImageView);
        holder.commentUsernameTextView.setText(comment.getUser().getLogin());
        holder.commentDateTextView.setText(DateParser.parseEnglish(comment.getCreationDate()));
        holder.commentBodyTextView.setText(Html.fromHtml(commentBody));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

}
