package mb00.android.codehub.ui.search.router;

import android.support.v7.app.AppCompatActivity;

public class SearchRouterImpl implements SearchRouter {

    private AppCompatActivity activity;

    public SearchRouterImpl(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadPrevious() {
        activity.finish();
    }
}
