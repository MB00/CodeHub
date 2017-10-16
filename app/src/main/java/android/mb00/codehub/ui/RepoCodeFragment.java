package android.mb00.codehub.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Code;
import android.mb00.codehub.api.service.GitHubService;
import android.mb00.codehub.data.BundleKeys;
import android.mb00.codehub.api.RetrofitBuilder;
import android.mb00.codehub.data.PreferenceKeys;
import android.mb00.codehub.ui.adapter.CodeAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RepoCodeFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private RecyclerView repoCodeRecyclerView;
    static CodeAdapter codeAdapter;

    private ImageButton pathHomeButton;

    private static LayoutInflater layoutInflater;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getArguments().getString(BundleKeys.USER_NAME);
        repoName = getArguments().getString(BundleKeys.REPO_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        View repoCodeView = inflater.inflate(R.layout.fragment_repo_code, container, false);

        repoCodeRecyclerView = (RecyclerView) repoCodeView.findViewById(R.id.repo_code_recycler_view);
        repoCodeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repoCodeRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        repoCodeCall(repoCodeRecyclerView, authHeader, userName, repoName, "");

        pathHomeButton = (ImageButton) repoCodeView.findViewById(R.id.path_home_button);
        pathHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repoCodeCall(repoCodeRecyclerView, authHeader, userName, repoName, "");
                displayPathAsViewObjects("", view.getContext(), (ViewGroup) view.getParent());
            }
        });

        return repoCodeView;
    }

    public static void repoCodeCall(final RecyclerView codeRecyclerView, final String header, final String user, final String repo, String path) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Code>> call = service.getRepoContents(header, user, repo, path);

        call.enqueue(new Callback<List<Code>>() {
            @Override
            public void onResponse(Call<List<Code>> call, Response<List<Code>> response) {
                List<Code> codeList = response.body();
                List<Code> sortedCodeList = sortCodeList(codeList);
                codeAdapter = new CodeAdapter(sortedCodeList, codeRecyclerView, header, user, repo);
                codeRecyclerView.setAdapter(codeAdapter);
            }

            @Override
            public void onFailure(Call<List<Code>> call, Throwable t) {

            }
        });
    }

    private static List<Code> sortCodeList(List<Code> codeList) {
        List<Code> sortedCodeList = new ArrayList<>();

        for (Code code : codeList) {
            if (code.getType().equals("dir")) {
                sortedCodeList.add(code);
            }
        }
        for (Code code : codeList) {
            if (code.getType().equals("file")) {
                sortedCodeList.add(code);
            }
        }
        return sortedCodeList;
    }

    // Parses the path and turns each directory into a clickable TextView
    public static void displayPathAsViewObjects(String path, Context context, ViewGroup viewParent) {
        View repoCodeView = layoutInflater.inflate(R.layout.fragment_repo_code, viewParent, false);
        LinearLayout dynamicPath = (LinearLayout) repoCodeView.findViewById(R.id.repo_dynamic_path);
        List<String> pathList = new ArrayList<>();

        int parsePosition = 0;

        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                pathList.add(path.substring(parsePosition, i));
                if ((i + 1) < path.length()) {
                    parsePosition = i + 1;
                }
            } else {
                pathList.add(path.substring(parsePosition, path.length()));
                break;
            }
        }

        for (int j = 0; j < pathList.size(); j++) {
            ImageView pathSeparator = new ImageView(context);
            TextView pathTextView = new TextView(context);

            pathSeparator.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            pathTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            pathSeparator.setImageResource(R.drawable.ic_chevron_right);
            pathTextView.setText(pathList.get(j));

            dynamicPath.addView(pathSeparator);
            dynamicPath.addView(pathTextView);
        }

        //layoutInflater.inflate(R.layout.fragment_repo_code, viewParent, false);
    }

    /*public static Observable<Void> displayPathAsViewObjectsObservable(String path, Context context, ViewGroup viewParent) {
        return Observable.just(displayPathAsViewObjects(path, context, viewParent));
    }*/


}
