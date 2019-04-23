package mb00.android.codehub.ui.gist.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mb00.android.codehub.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class GistActivityTest {

    @Rule
    public ActivityTestRule<GistActivity> testRule = new ActivityTestRule<>(GistActivity.class);

    @Test
    public void checkGistActivityVisibility() {
        onView(withId(R.id.gist_view_pager)).check(matches(isDisplayed()));
    }

}
