package githubfort.k.githubsearchapp.activitys.githubsearch.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import githubfort.k.githubsearchapp.activitys.githubsearch.GitHubSearchContract;
import githubfort.k.githubsearchapp.activitys.githubsearch.GitHubSearchPresenter;
import githubfort.k.githubsearchapp.network.Api;

@Module
public class GitHubSearchModule {

    GitHubSearchContract.View view;

    public GitHubSearchModule(GitHubSearchContract.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    GitHubSearchContract.Presenter provideGitHubSearchPresenter(Api api){
        return new GitHubSearchPresenter(view,api);
    }




}
