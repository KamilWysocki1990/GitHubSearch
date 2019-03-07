package githubfort.k.githubsearchapp.di;

import dagger.Component;
import githubfort.k.githubsearchapp.ApplicationScope;
import githubfort.k.githubsearchapp.activitys.gitbubsearch.di.GitHubSearchComponent;
import githubfort.k.githubsearchapp.activitys.gitbubsearch.di.GitHubSearchModule;

@ApplicationScope
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    GitHubSearchComponent plus(GitHubSearchModule GitHubSearchModule);




}
