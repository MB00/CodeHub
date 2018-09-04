package mb00.android.codehub.ui.search.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.user.adapter.UserAdapter;
import retrofit2.Retrofit;


// not currently utilized

public class SearchCodeFragment extends Fragment {
    
    /*
    private String code;
    private RecyclerView searchUsersRecyclerView;
    private UserAdapter searchUsersAdapter;
    private TextView noUserResultsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchUsersView = inflater.inflate(R.layout.fragment_search_code, container, false);
        searchUsersRecyclerView = searchUsersView.findViewById(R.id.search_codes_recycler_view);
        searchUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noUserResultsTextView = searchUsersView.findViewById(R.id.no_code_results_text_view);

        code = getArguments().getString(BundleKeys.SEARCH_QUERY_KEY);

        if (code == null) {
            noUserResultsTextView.setVisibility(View.VISIBLE);
        } else {
            codeCall(code);
        }

        return searchUsersView;
    }

    private void codeCall(String code) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
    }
    */

}
