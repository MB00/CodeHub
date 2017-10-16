package android.mb00.codehub.ui.adapter;

import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Issue;
import android.mb00.codehub.api.parser.DateParser;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.SearchIssuesHolder> {

    private List<Issue> issueList;

    public IssueAdapter(List<Issue> issueList) {
        this.issueList = issueList;
    }

    public class SearchIssuesHolder extends RecyclerView.ViewHolder {

        private ImageView statusImageView;
        private TextView titleTextView;
        private TextView dateTextView;

        public SearchIssuesHolder(View itemView) {
            super(itemView);

            statusImageView = (ImageView) itemView.findViewById(R.id.issue_status_image_view);
            titleTextView = (TextView) itemView.findViewById(R.id.issue_title_text_view);
            dateTextView = (TextView) itemView.findViewById(R.id.issue_date_text_view);
        }
    }

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
