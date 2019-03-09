package githubfort.k.githubsearchapp.activitys.gitbubsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import githubfort.k.githubsearchapp.GitHubSearchApplication;
import githubfort.k.githubsearchapp.R;
import githubfort.k.githubsearchapp.activitys.gitbubsearch.di.GitHubSearchModule;
import githubfort.k.githubsearchapp.data.Item;

public class GitHubSearchActivity extends AppCompatActivity implements GitHubSearchContract.View {

    @Inject
    GitHubSearchContract.Presenter presenterGHS;

    @BindView(R.id.searchEditText)
    EditText searchRepoEditText;

    List<Item> listOfItemsToAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.git_hub_search_activity);
        ButterKnife.bind(this);
        ((GitHubSearchApplication) getApplication())
                .getAppComponent()
                .plus(new GitHubSearchModule(this))
                .inject(this);


    }


    @OnClick(R.id.buttonSearch)
    public void onSearchClick(){
        presenterGHS.searchForRepo(searchRepoEditText.getText().toString());
        searchRepoEditText.clearFocus();
    }


    @Override
    public void showTextAfterEditTextSearchIsEmpty() {

    }

    @Override
    public void displayTextConfirmData(String name) {
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addPagedItemToList(List<Item> items) {
        listOfItemsToAdapter.addAll(items);
    }
}
