package githubfort.k.githubsearchapp.network;

import githubfort.k.githubsearchapp.model.Repo;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {
     String BASE_URL ="https://api.github.com/search/";

     @GET
    Flowable<Repo>getRepo(@Url String url);


}
