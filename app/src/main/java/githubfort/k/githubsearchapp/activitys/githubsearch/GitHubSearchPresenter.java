package githubfort.k.githubsearchapp.activitys.githubsearch;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;


import githubfort.k.githubsearchapp.network.Api;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GitHubSearchPresenter implements GitHubSearchContract.Presenter, LifecycleObserver {

    private GitHubSearchContract.View viewGHS;
    private Api api;
    private CompositeDisposable compositeDisposable;
    private double numberOfItems;

    public GitHubSearchPresenter(GitHubSearchContract.View viewGHS, Api api) {
        this.viewGHS = viewGHS;
        this.api = api;

        ((LifecycleOwner) viewGHS).getLifecycle().addObserver(this);
        compositeDisposable = new CompositeDisposable();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStopToDo() {
        compositeDisposable.clear();
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
        getFirstResponse(request);

    }


    private void getPagedResponse(String request) {

            double numberOfPageRound = setNumberForPageIteration();
            for (int i = 1; i <= numberOfPageRound; i++) {


                String howManyItemsPerResponse = "100";

                String requestForPagedItems = "repositories?q=" + request + "&page=" + String.valueOf(i) + "&per_page=" + howManyItemsPerResponse;
                compositeDisposable.add(
                        api.getRepo(requestForPagedItems)
                                .subscribeOn(Schedulers.computation())
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




    private double setNumberForPageIteration() {
        double numberOfPage = (numberOfItems/ 100);
        double numberOfPageRound = Math.round(numberOfItems / 100);
        if(numberOfPage>numberOfPageRound){
            numberOfPageRound=numberOfPageRound+1;
        }
        //numberOfPageRound -1  because first request was already done with check for items
        return numberOfPageRound -1;
    }


    private void getFirstResponse(String request) {
        compositeDisposable.clear();
        viewGHS.showProgress();
        String requestForNumberOfItems = "repositories?q=" + request + "&per_page=100";
        compositeDisposable.add(
                api.getRepo(requestForNumberOfItems)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                repoData -> {
                                    numberOfItems = repoData.getTotalCount();
                                    viewGHS.displayHowManyItemsFromResponseTotal(String.valueOf(repoData.getTotalCount()));
                                    viewGHS.addPagedItemToList(repoData.getItems());
                                }, throwable -> viewGHS.showError()

                                , () -> {
                                    getPagedResponse(request);
                                }
                        )
        );

    }


}