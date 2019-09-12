package mb00.android.codehub.ui.gist.view;


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
public class GistActivityTest {

    @Rule
    public ActivityTestRule<GistActivity> testRule = new ActivityTestRule<>(GistActivity.class);

    @Test
    public void checkGistActivityVisibility() {
        onView(withId(R.id.gist_view_pager)).check(matches(isDisplayed()));
    }

}
