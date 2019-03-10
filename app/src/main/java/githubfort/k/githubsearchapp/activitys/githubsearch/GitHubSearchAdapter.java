package githubfort.k.githubsearchapp.activitys.gitbubsearch;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import githubfort.k.githubsearchapp.R;
import githubfort.k.githubsearchapp.data.Item;

public class GitHubSearchAdapter extends RecyclerView.Adapter<GitHubSearchAdapter.ViewHolder> {

    private List<Item> gitRepoList = new ArrayList<>();


    public void updateBooks(List<Item>gitRepoFromNetwork){
        gitRepoList.addAll(gitRepoFromNetwork);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public GitHubSearchAdapter.ViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_git_hub_search, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubSearchAdapter.ViewHolder holder, int position) {
    holder.setup
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_avatar_image)
        ImageView avatarImage;

        @BindView(R.id.item_Repo_name_text)
        TextView repoName;

        @BindView(R.id.item_Language_text)
        TextView language;

        @BindView(R.id.item_number_of_Star)
        TextView numberOfStars;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setup(Item item) {
            if (item.getOwner().getAvatarUrl() == null) {
                setImageWithoutApiResponse();
            } else {
                setImageGlide(item);
            }


        }

        private void setImageGlide(Item item) {
            Glide.with(itemView.getContext())
                    .load(item.getOwner().getAvatarUrl())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Toast.makeText(itemView.getContext(), "failed", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            return false;
                        }

                    })
                    .into(avatarImage);
        }

        private void setImageWithoutApiResponse() {
            avatarImage.setImageResource(R.drawable.);
        }

    }

}
