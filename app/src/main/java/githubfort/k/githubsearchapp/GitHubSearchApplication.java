package githubfort.k.githubsearchapp;

import android.app.Application;

import githubfort.k.githubsearchapp.di.AppComponent;
import githubfort.k.githubsearchapp.di.AppModule;
import githubfort.k.githubsearchapp.di.DaggerAppComponent;
import githubfort.k.githubsearchapp.di.DataModule;


public class GitHubSearchApplication extends Application {


    private AppComponent appComponent;


    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();


    }


}