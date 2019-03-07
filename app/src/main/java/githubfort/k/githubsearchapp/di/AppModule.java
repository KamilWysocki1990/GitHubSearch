package githubfort.k.githubsearchapp.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import githubfort.k.githubsearchapp.ApplicationScope;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    Context provideContext() {
        return context;
    }



}
