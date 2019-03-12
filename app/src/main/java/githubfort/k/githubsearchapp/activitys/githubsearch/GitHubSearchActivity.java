package githubfort.k.githubsearchapp.activitys.githubsearch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import githubfort.k.githubsearchapp.GitHubSearchApplication;
import githubfort.k.githubsearchapp.R;
import githubfort.k.githubsearchapp.activitys.githubsearch.di.GitHubSearchModule;
import githubfort.k.githubsearchapp.model.Item;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class GitHubSearchActivity extends AppCompatActivity implements GitHubSearchContract.View {

    @Inject
    GitHubSearchContract.Presenter presenterGHS;

    @BindView(R.id.git_hub_search_progress)
    ProgressBar GHSProgress;

    @BindView(R.id.git_repo_recycler)
    RecyclerView gitRepoRecycler;

    @BindView(R.id.button_search)
    Button buttonForSearch;

    @BindView(R.id.git_hub_try_again_button)
    Button buttonTryAgain;

    @BindView(R.id.git_hub_search_toolbar)
    Toolbar toolbarGHS;

    @BindView(R.id.searchEditText)
    EditText searchRepoEditText;

    @BindView(R.id.Git_hub_search_error_group)
    Group GHSErrorGroup;

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
        setSupportActionBar(toolbarGHS);

        adapter = new GitHubSearchAdapter();
        gitRepoRecycler.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        gitRepoRecycler.addItemDecoration(itemDecoration);
        gitRepoRecycler.setHasFixedSize(true);
        gitRepoRecycler.setAdapter(adapter);
        createShowCase();



    }

    @OnClick(R.id.button_search)
    public void onSearchClick(){
        listOfItemsToAdapter.clear();
        searchRepoEditText.setSelected(false);
        searchRepoEditText.clearFocus();
        clearKeyboardAfterSearchButton();


        adapter.clearRepoBeforeNextRequest();
        presenterGHS.searchForRepo(searchRepoEditText.getText().toString());

    }

    @OnClick(R.id.closeAppButton)
    public void quitApp(){
        finishAndRemoveTask();
    }

    @OnClick(R.id.git_hub_try_again_button)
    public void tryAgainAction(){
       onSearchClick();
    }


    @Override
    public void showTextAfterEditTextSearchIsEmpty() {
        Toast.makeText(this,R.string.empty_edit_text_search, Toast.LENGTH_LONG).show();
    }


    @Override
    public void addPagedItemToList(List<Item> items) {
        GHSProgress.setVisibility(View.INVISIBLE);
        gitRepoRecycler.setVisibility(View.VISIBLE);
        listOfItemsToAdapter.addAll(items);
        adapter.updateRepoList(listOfItemsToAdapter);

    }

    @Override
    public void showProgress() {
        gitRepoRecycler.setVisibility(View.INVISIBLE);
        GHSErrorGroup.setVisibility(View.INVISIBLE);
        GHSProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        GHSProgress.setVisibility(View.INVISIBLE);
        GHSErrorGroup.setVisibility(View.VISIBLE);
        GHSErrorGroup.requestLayout();
    }

    @Override
    public void displayHowManyItemsFromResponseTotal(String name) {
        Toast.makeText(this,"Found "+ name + " repositories", Toast.LENGTH_LONG).show();
    }

    private void createShowCase() {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "First");
        sequence.setConfig(config);

        sequence.addSequenceItem(searchRepoEditText,
                getString(R.string.showcase_edit_text), getString(R.string.showcase_dismiss_text));

        sequence.addSequenceItem(buttonForSearch,
                getString(R.string.button_for_search_text), getString(R.string.showcase_dismiss_text));
        sequence.addSequenceItem(buttonTryAgain,getString(R.string.showcase_text_about_result),getString(R.string.showcase_dismiss_text ));
        sequence.start();
    }

    private void clearKeyboardAfterSearchButton() {
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(imm).hideSoftInputFromWindow(searchRepoEditText.getWindowToken(), 0);
    }

}
