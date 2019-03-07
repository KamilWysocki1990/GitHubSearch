package githubfort.k.githubsearchapp.activitys.gitbubsearch.di;

import javax.inject.Singleton;

import dagger.Subcomponent;
import githubfort.k.githubsearchapp.activitys.gitbubsearch.GitHubSearchActivity;

@Singleton
@Subcomponent(modules = {GitHubSearchModule.class})
public interface GitHubSearchComponent {


    void inject(GitHubSearchActivity gitHubSearchActivity);

}
