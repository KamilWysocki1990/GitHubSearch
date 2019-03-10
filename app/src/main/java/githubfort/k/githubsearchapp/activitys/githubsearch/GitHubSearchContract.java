package githubfort.k.githubsearchapp.activitys.githubsearch;

import java.util.List;

import githubfort.k.githubsearchapp.data.Item;

public interface GitHubSearchContract {

    interface View{

        void showTextAfterEditTextSearchIsEmpty();

        void displayTextConfirmData(String name);

        void addPagedItemToList(List<Item> items);
    }

    interface Presenter{


        void searchForRepo(String textToSearch);
    }

}
