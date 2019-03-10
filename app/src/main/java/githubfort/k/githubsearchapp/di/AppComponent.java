package githubfort.k.githubsearchapp.di;

import dagger.Component;
import githubfort.k.githubsearchapp.ApplicationScope;
import githubfort.k.githubsearchapp.activitys.githubsearch.di.GitHubSearchComponent;
import githubfort.k.githubsearchapp.activitys.githubsearch.di.GitHubSearchModule;

@ApplicationScope
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    GitHubSearchComponent plus(GitHubSearchModule GitHubSearchModule);




}
