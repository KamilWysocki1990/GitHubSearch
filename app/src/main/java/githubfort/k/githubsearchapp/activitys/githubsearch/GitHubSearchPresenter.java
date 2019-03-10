package githubfort.k.githubsearchapp.activitys.gitbubsearch;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;


import githubfort.k.githubsearchapp.network.Api;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GitHubSearchPresenter implements GitHubSearchContract.Presenter,LifecycleObserver {

    private GitHubSearchContract.View viewGHS;
    private Api api;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private int numberOfItems;

    public GitHubSearchPresenter(GitHubSearchContract.View viewGHS, Api api) {
        this.viewGHS = viewGHS;
        this.api = api;

        ((LifecycleOwner) viewGHS).getLifecycle().addObserver(this);

    }


    @Override
    public void searchForRepo(String textToSearch) {

        if (textToSearch.isEmpty()) {
            viewGHS.showTextAfterEditTextSearchIsEmpty();
        } else {
            getDataFromModel(textToSearch);
        }


    }

    private void getDataFromModel(String request) {
         getResponse(request);

    }

    private void getPagedResponse(String request) {
        int numberOfPage = numberOfItems / 50;
        for (int i = 1; i < numberOfPage; i++) {


            String requestForPagedItems = "repositories?q=" + request + "&page=" + String.valueOf(i) + "&per_page=50";
            compositeDisposable.add(
                    api.getRepo(requestForPagedItems)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    repoData -> {
                                        viewGHS.addPagedItemToList(repoData.getItems());
                                    },
                                    Throwable::printStackTrace
                                    , () -> {

                                    }
                            )
            );


        }
    }

    private void getResponse(String request) {

        String requestForNumberOfItems = "repositories?q=" + request + "&per_page=1";
        compositeDisposable.add(
                api.getRepo(requestForNumberOfItems)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                repoData -> {
                                    numberOfItems = repoData.getTotalCount();
                                    //   viewGHS.displayTextConfirmData(repoData.getItems().get(0).getName());
                                }, Throwable::printStackTrace
                                , () -> {
                                   getPagedResponse(request);
                                }
                        )
        );

    }


}