package githubfort.k.githubsearchapp.activitys.githubsearch.di;

import javax.inject.Singleton;

import dagger.Subcomponent;
import githubfort.k.githubsearchapp.activitys.githubsearch.GitHubSearchActivity;

@Singleton
@Subcomponent(modules = {GitHubSearchModule.class})
public interface GitHubSearchComponent {


    void inject(GitHubSearchActivity gitHubSearchActivity);

}
