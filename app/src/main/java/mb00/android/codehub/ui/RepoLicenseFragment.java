package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Code;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.api.Base64Decoder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RepoLicenseFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private TextView licenseTextView;

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
        View repoLicenseView = inflater.inflate(R.layout.fragment_repo_license, container, false);
        licenseTextView = (TextView) repoLicenseView.findViewById(R.id.repo_license_text_view);
        
        repoLicenseCall(authHeader, userName, repoName);

        return repoLicenseView;
    }

    private void repoLicenseCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<Code> call = service.getRepoLicense(header, user, repo);

        call.enqueue(new Callback<Code>() {
            @Override
            public void onResponse(Call<Code> call, Response<Code> response) {
                if (response.body() != null) {
                    String license = Base64Decoder.decodeBase64(response.body().getContent());
                    licenseTextView.setText(license);
                } else {
                    licenseTextView.setText(R.string.no_license);
                }
            }

            @Override
            public void onFailure(Call<Code> call, Throwable t) {

            }
        });
    }

}
