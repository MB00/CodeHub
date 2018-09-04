package mb00.android.codehub.ui.user.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.FragmentUserOverviewBinding;
import mb00.android.codehub.logic.utils.DateParser;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;
import mb00.android.codehub.ui.user.viewmodel.UserOverviewViewModel;
import retrofit2.Retrofit;

/**
 * Fragment containing user overview; launched from {@link UserFragmentPagerAdapter}
 */

public class UserOverviewFragment extends BaseBindingFragment<FragmentUserOverviewBinding, UserOverviewViewModel> {

    private String authHeader;
    private String userLogin;

    @Override
    protected int layout() {
        return R.layout.fragment_user_overview;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userLogin = getArguments().getString(BundleKeys.USER_NAME);
    }

    @Override
    public void onStart() {
        super.onStart();

        userOverViewCall(authHeader, userLogin);
    }

    private void userOverViewCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getUserOverview(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setUserInfo, Throwable::getMessage);
    }
    
    private void setUserInfo(User user) {
        Glide.with(getActivity()).load(user.getAvatarUrl()).into(getBinding().userOverviewAvatarImageView);
        getBinding().userOverviewNameTextView.setText(user.getName());

        if (user.getName() == null || user.getName().isEmpty()) {
            getBinding().userOverviewNameTextView.setVisibility(View.GONE);
        }
        getBinding().userOverviewLoginTextView.setText(user.getLogin());
        getBinding().userOverviewCompanyTextView.setText(user.getCompany());
        if (user.getCompany() == null || user.getCompany().isEmpty()) {
            getBinding().userOverviewCompanyTextView.setVisibility(View.GONE);
        }
        getBinding().userOverviewLocationTextView.setText(user.getLocation());
        if (user.getLocation() == null || user.getLocation().isEmpty()) {
            getBinding().userOverviewLocationTextView.setVisibility(View.GONE);
        }
        getBinding().userOverviewEmailTextView.setText(user.getEmail());
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            getBinding().userOverviewEmailTextView.setVisibility(View.GONE);
        }
        getBinding().userOverviewWebsiteTextView.setText(user.getWebsite());
        if (user.getWebsite() == null || user.getWebsite().isEmpty()) {
            getBinding().userOverviewWebsiteTextView.setVisibility(View.GONE);
        }
        String creationDate = DateParser.parseEnglish(user.getCreationDate());
        getBinding().userOverviewCreationDateTextView.setText(creationDate);
    }

}
