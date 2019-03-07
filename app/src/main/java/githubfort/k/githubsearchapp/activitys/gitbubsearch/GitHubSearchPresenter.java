package githubfort.k.githubsearchapp.activitys.gitbubsearch;

import githubfort.k.githubsearchapp.network.Api;

public class GitHubSearchPresenter implements GitHubSearchContract.Presenter {

    private GitHubSearchContract.View viewGHS;
    private Api api;

    public GitHubSearchPresenter(GitHubSearchContract.View viewGHS, Api api) {
        this.viewGHS = viewGHS;
        this.api = api;
    }
}
