package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.parser.DateParser;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.PreferenceKeys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class UserOverviewFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String userLogin;

    private ImageView userAvatarImageView;
    private TextView userNameTextView;
    private TextView userLoginTextView;
    private TextView userCompanyTextView;
    private TextView userLocationTextView;
    private TextView userEmailTextView;
    private TextView userWebsiteTextView;
    private TextView userCreationDateTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userLogin = getArguments().getString(BundleKeys.USER_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View userOverviewView = inflater.inflate(R.layout.fragment_user_overview, container, false);

        userAvatarImageView = (ImageView) userOverviewView.findViewById(R.id.user_overview_avatar_image_view);
        userNameTextView = (TextView) userOverviewView.findViewById(R.id.user_overview_name_text_view);
        userLoginTextView = (TextView) userOverviewView.findViewById(R.id.user_overview_login_text_view);
        userCompanyTextView = (TextView) userOverviewView.findViewById(R.id.user_overview_company_text_view);
        userLocationTextView = (TextView) userOverviewView.findViewById(R.id.user_overview_location_text_view);
        userEmailTextView = (TextView) userOverviewView.findViewById(R.id.user_overview_email_text_view);
        userWebsiteTextView = (TextView) userOverviewView.findViewById(R.id.user_overview_website_text_view);
        userCreationDateTextView = (TextView) userOverviewView.findViewById(R.id.user_overview_creation_date_text_view);

        userOverViewCall(authHeader, userLogin);

        return userOverviewView;
    }

    private void userOverViewCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<User> call = service.getUserOverview(header, user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Glide.with(getActivity()).load(user.getAvatarUrl()).into(userAvatarImageView);
                userNameTextView.setText(user.getName());
                if (user.getName() == null) {
                    userNameTextView.setVisibility(View.GONE);
                }
                userLoginTextView.setText(user.getLogin());
                userCompanyTextView.setText(user.getCompany());
                if (user.getCompany() == null) {
                    userCompanyTextView.setVisibility(View.GONE);
                }
                userLocationTextView.setText(user.getLocation());
                if (user.getLocation() == null) {
                    userLocationTextView.setVisibility(View.GONE);
                }
                userEmailTextView.setText(user.getEmail());
                if (user.getEmail() == null) {
                    userEmailTextView.setVisibility(View.GONE);
                }
                userWebsiteTextView.setText(user.getWebsite());
                if (user.getWebsite() == null) {
                    userWebsiteTextView.setVisibility(View.GONE);
                }
                String creationDate = DateParser.parseEnglish(user.getCreationDate());
                userCreationDateTextView.setText(creationDate);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
