package githubfort.k.githubsearchapp.di;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import githubfort.k.githubsearchapp.ApplicationScope;
import githubfort.k.githubsearchapp.network.Api;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {



    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    Api provideApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }




}

