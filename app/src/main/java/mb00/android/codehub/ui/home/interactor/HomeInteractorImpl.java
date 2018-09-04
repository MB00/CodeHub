package mb00.android.codehub.ui.home.interactor;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import mb00.android.codehub.R;


public class HomeInteractorImpl implements HomeInteractor {

    private final AppCompatActivity activity;

    public HomeInteractorImpl(final AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void openNavigationDrawer() {
        DrawerLayout navigationDrawer = activity.getWindow().getDecorView().getRootView().findViewById(R.id.drawer_layout);
        navigationDrawer.openDrawer(Gravity.START);
    }

}
