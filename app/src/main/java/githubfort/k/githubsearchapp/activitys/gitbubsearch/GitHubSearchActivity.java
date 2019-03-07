package githubfort.k.githubsearchapp.activitys.gitbubsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import githubfort.k.githubsearchapp.GitHubSearchApplication;
import githubfort.k.githubsearchapp.R;
import githubfort.k.githubsearchapp.activitys.gitbubsearch.di.GitHubSearchModule;

public class GitHubSearchActivity extends AppCompatActivity implements GitHubSearchContract.View {

    @Inject
    GitHubSearchContract.Presenter presenterGHS;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.git_hub_search_activity);
        ButterKnife.bind(this);
        ((GitHubSearchApplication) getApplication())
                .getAppComponent()
                .plus(new GitHubSearchModule(this))
                .inject(this);


    }



}
