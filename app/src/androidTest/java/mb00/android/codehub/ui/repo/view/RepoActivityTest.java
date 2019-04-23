package mb00.android.codehub.ui.repo.view;

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
public class RepoActivityTest {

    @Rule
    public ActivityTestRule<RepoActivity> testRule = new ActivityTestRule<>(RepoActivity.class);

    @Test
    public void checkRepoActivityVisibility() {
        onView(withId(R.id.repo_view_pager)).check(matches(isDisplayed()));
    }

}