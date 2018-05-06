package mb00.android.codehub.ui.repo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Issue;
import mb00.android.codehub.api.parser.DateParser;
import mb00.android.codehub.ui.repo.view.RepoIssuesFragment;
import mb00.android.codehub.ui.search.view.SearchIssuesFragment;

/**
 * RecyclerView adapter used to display issues in {@link RepoIssuesFragment} and {@link SearchIssuesFragment}
 */

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.SearchIssuesHolder> {

    //==============================================================================================
    // IssueAdapter fields
    //==============================================================================================

    private List<Issue> issueList;

    //==============================================================================================
    // IssueAdapter constructor
    //==============================================================================================

    public IssueAdapter(List<Issue> issueList) {
        this.issueList = issueList;
    }

    //==============================================================================================
    // ViewHolder inner class
    //==============================================================================================

    public class SearchIssuesHolder extends RecyclerView.ViewHolder {

        //==========================================================================================
        // SearchIssuesHolder fields
        //==========================================================================================

        private ImageView statusImageView;
        private TextView titleTextView;
        private TextView dateTextView;

        //==========================================================================================
        // SearchIssuesHolder constructor
        //==========================================================================================

        public SearchIssuesHolder(View itemView) {
            super(itemView);

            statusImageView = itemView.findViewById(R.id.issue_status_image_view);
            titleTextView = itemView.findViewById(R.id.issue_title_text_view);
            dateTextView = itemView.findViewById(R.id.issue_date_text_view);
        }

    }

    //==============================================================================================
    // RecyclerView.Adapter methods
    //==============================================================================================

    @Override
    public SearchIssuesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View issueView = inflater.inflate(R.layout.view_holder_issue, parent, false);
        return new SearchIssuesHolder(issueView);
    }

    @Override
    public void onBindViewHolder(SearchIssuesHolder holder, int position) {
        Issue issue = issueList.get(position);

        String state = issue.getState();

        if (state.equals("open")) {
            holder.statusImageView.setImageResource(R.drawable.ic_issue_open);
            String openDate = "Opened " + DateParser.parseEnglish(issue.getOpenDate());
            holder.dateTextView.setText(openDate);
        } else { // "closed"
            holder.statusImageView.setImageResource(R.drawable.ic_issue_closed);
            String closedDate = "Closed " + DateParser.parseEnglish(issue.getCloseDate());
            holder.dateTextView.setText(closedDate);
        }
        holder.titleTextView.setText(issue.getTitle());
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }

}
