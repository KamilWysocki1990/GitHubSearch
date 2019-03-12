package githubfort.k.githubsearchapp.ViewTest;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import githubfort.k.githubsearchapp.activitys.githubsearch.GitHubSearchActivity;

@RunWith(RobolectricTestRunner.class)
public class GitHubSearchActivityTest {


    private GitHubSearchActivity activity;

    @Before
    public void setUp() throws Exception{
         activity = Robolectric.buildActivity(GitHubSearchActivity.class)
                .create()
                .resume()
                .get();



    }

    @Test
    public void shouldNotBeNull() throws Exception{
        assertNotNull(activity);
    }


}
