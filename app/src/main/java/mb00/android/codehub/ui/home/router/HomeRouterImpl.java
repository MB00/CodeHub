package mb00.android.codehub.ui.home.router;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import mb00.android.codehub.ui.search.view.SearchActivity;


public class HomeRouterImpl implements HomeRouter {

    private final AppCompatActivity activity;

    public HomeRouterImpl(final AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadSearchActivity() {
        Intent i = new Intent(activity, SearchActivity.class);
        activity.startActivity(i);
    }

}
