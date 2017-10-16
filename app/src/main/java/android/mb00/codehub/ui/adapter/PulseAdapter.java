package android.mb00.codehub.ui.adapter;

import android.content.Context;
import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Pulse;
import android.mb00.codehub.api.parser.DateParser;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class PulseAdapter extends RecyclerView.Adapter<PulseAdapter.PulseHolder> {

    private List<Pulse> pulseList;
    private Context context;

    public PulseAdapter(List<Pulse> pulseList, Context context) {
        this.pulseList = pulseList;
        this.context = context;
    }

    public class PulseHolder extends RecyclerView.ViewHolder {

        private ImageView pulseAvatarImageView;
        private TextView pulseTitleTextView;
        private TextView pulseTimeTextView;

        public PulseHolder(View itemView) {
            super(itemView);

            pulseAvatarImageView = (ImageView) itemView.findViewById(R.id.repo_pulse_avatar_image_view);
            pulseTitleTextView = (TextView) itemView.findViewById(R.id.repo_pulse_title_text_view);
            pulseTimeTextView = (TextView) itemView.findViewById(R.id.repo_pulse_time_text_view);
        }

    }

    private String getActionType(Pulse pulse) {
        String repoName = pulse.getRepo().getName();
        String issueNumber;

        switch (pulse.getType()) {
            case "WatchEvent":
                return " starred " + repoName;
            case "IssuesEvent":
                issueNumber = pulse.getPayload().getIssue().getNumber();
                return " opened issue " + issueNumber + " on " + repoName; // closing issues to be added - need to check action
            case "IssueCommentEvent":
                issueNumber = pulse.getPayload().getIssue().getNumber();
                return " commented on issue " + issueNumber + " on " + repoName;
            case "PushEvent":
                return " pushed to " + repoName; // more detail to be added
            case "PullRequestEvent":
                return " pulled from " + repoName; // more detail to be added
            case "ForkEvent":
                return " forked repository " + repoName;
            case "CreateEvent":
                return " created repository " + repoName;
            default:
                return "";
        }
    }

    @Override
    public PulseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View pulseView = inflater.inflate(R.layout.view_holder_pulse, parent, false);
        return new PulseHolder(pulseView);
    }

    @Override
    public void onBindViewHolder(PulseHolder holder, int position) {
        Pulse pulse = pulseList.get(position);
        Glide.with(context).load(pulse.getActor().getAvatarUrl()).into(holder.pulseAvatarImageView);
        holder.pulseTitleTextView.setText(pulse.getActor().getLogin() + getActionType(pulse));
        String creationDate = DateParser.parseEnglish(pulse.getCreationDate());
        holder.pulseTimeTextView.setText(creationDate);
    }

    @Override
    public int getItemCount() {
        return pulseList.size();
    }

}
