package mb00.android.codehub.ui.repo.view;


import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mb00.android.codehub.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class RepoActivityTest {

    @Rule
    public ActivityTestRule<RepoActivity> testRule = new ActivityTestRule<>(RepoActivity.class);

    @Test
    public void checkRepoActivityVisibility() {
        onView(withId(R.id.repo_view_pager)).check(matches(isDisplayed()));
    }

}