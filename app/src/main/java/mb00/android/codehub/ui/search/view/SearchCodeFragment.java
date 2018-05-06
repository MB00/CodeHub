package mb00.android.codehub.ui.search.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import mb00.android.codehub.ui.user.adapter.UserAdapter;


// not currently utilized

public class SearchCodeFragment extends Fragment {
    
    private String code;
    private RecyclerView searchUsersRecyclerView;
    private UserAdapter searchUsersAdapter;
    private TextView noUserResultsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchUsersView = inflater.inflate(R.layout.fragment_search_codes, container, false);
        searchUsersRecyclerView = (RecyclerView) searchUsersView.findViewById(R.id.search_codes_recycler_view);
        searchUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noUserResultsTextView = (TextView) searchUsersView.findViewById(R.id.no_code_results_text_view);

        code = getArguments().getString(SearchActivity.SEARCH_QUERY_KEY);

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
        Call<CodeResult> call = service.codeSearch(code);

        call.enqueue(new Callback<CodeResult>() {
            @Override
            public void onResponse(Call<CodeResult> call, Response<CodeResult> response) {
                List<Code> codeList = response.body().getItems();
                searchCodeAdapter = new SearchCodeAdapter(codeList);
                searchCodeRecyclerView.setAdapter(searchCodeAdapter);
            }

            @Override
            public void onFailure(Call<CodeResult> call, Throwable t) {

            }
        });
    }*/

}
