package githubfort.k.githubsearchapp.activitys.githubsearch;

import java.util.List;

import githubfort.k.githubsearchapp.data.Item;

public interface GitHubSearchContract {

    interface View{

        void showTextAfterEditTextSearchIsEmpty();

        void displayHowManyItemsFromResponseTotal(String name);

        void addPagedItemToList(List<Item> items);

        void showProgress();

        void showError();
    }

    interface Presenter{


        void searchForRepo(String textToSearch);
    }
}

