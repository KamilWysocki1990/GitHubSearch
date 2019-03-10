package githubfort.k.githubsearchapp.activitys.githubsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import githubfort.k.githubsearchapp.GitHubSearchApplication;
import githubfort.k.githubsearchapp.R;
import githubfort.k.githubsearchapp.activitys.githubsearch.di.GitHubSearchModule;
import githubfort.k.githubsearchapp.data.Item;

public class GitHubSearchActivity extends AppCompatActivity implements GitHubSearchContract.View {

    @Inject
    GitHubSearchContract.Presenter presenterGHS;

    @BindView(R.id.git_repo_recycler)
    RecyclerView gitRepoRecycler;


    @BindView(R.id.searchEditText)
    EditText searchRepoEditText;

    List<Item> listOfItemsToAdapter = new ArrayList<>();

    private GitHubSearchAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.git_hub_search_activity);
        ButterKnife.bind(this);
        ((GitHubSearchApplication) getApplication())
                .getAppComponent()
                .plus(new GitHubSearchModule(this))
                .inject(this);

        adapter = new GitHubSearchAdapter();
        gitRepoRecycler.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        gitRepoRecycler.addItemDecoration(itemDecoration);
        gitRepoRecycler.setHasFixedSize(true);
        gitRepoRecycler.setAdapter(adapter);
    }


    @OnClick(R.id.buttonSearch)
    public void onSearchClick(){
        listOfItemsToAdapter.clear();
        adapter.clearRepoBeforeNextRequest();
        searchRepoEditText.clearFocus();
        presenterGHS.searchForRepo(searchRepoEditText.getText().toString());

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
        adapter.updateRepoList(listOfItemsToAdapter);

    }
}
