package net.cacheux.crud.example.room;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.cacheux.crud.CrudActivity;
import net.cacheux.crud.CrudListAdapter;
import net.cacheux.crud.EditViewBinder;
import net.cacheux.crud.example.room.model.Article;
import net.cacheux.crud.example.room.viewmodel.ArticleViewModel;

/**
 * A more complex exemple of a {@link CrudActivity} implementation. Here we use custom layouts for
 * list view items and edit dialog.
 */
public class ArticleActivity extends CrudActivity<Article> {
    private EditViewBinder<Article> editViewBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.articles);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public CrudListAdapter<Article> getListAdapter() {
        /**
         * The custom ListAdapter bind a layout with two TextView with {@link Article}.
         */
        return new CrudListAdapter<Article>(this, R.layout.listitem_article) {
            @Override
            public RecyclerView.ViewHolder createViewHolder(View view) {
                return new ListViewHolder(view);
            }

            @Override
            public void bindItem(RecyclerView.ViewHolder holder, Article item) {
                ((ListViewHolder) holder).label.setText(item.getLabel());
                ((ListViewHolder) holder).price.setText(String.valueOf(item.getPrice()));
            }
        };
    }

    @Override
    public EditViewBinder<Article> getEditViewBinder() {
        if (editViewBinder == null) {
            /**
             * Custom ViewBinder bind a layout with two EditText to the values from {@link Article}.
             */
            editViewBinder = new EditViewBinder<Article>() {
                private View root;
                private EditText editLabel;
                private EditText editPrice;

                @Override
                public View getLayout() {
                    root = getLayoutInflater().inflate(R.layout.dialog_editarticle, null,
                        false);
                    editLabel = root.findViewById(R.id.edit_label);
                    editPrice = root.findViewById(R.id.edit_price);

                    return root;
                }

                @Override
                public void bindToView(View view, Article item) {
                    editLabel.setText(item.getLabel());
                    editPrice.setText(String.valueOf(item.getPrice()));
                }

                @Override
                public void updateFromView(View view, Article item) {
                    item.setLabel(editLabel.getText().toString());
                    item.setPrice(Float.parseFloat(editPrice.getText().toString()));
                }

                @Override
                public Article createNew() {
                    // Since we have a default constructor for Article, we can just return a new
                    // instance
                    return new Article();
                }
            };
        }
        return editViewBinder;
    }

    @Override
    public Class getViewModelClass() {
        return ArticleViewModel.class;
    }

    /**
     * ViewHolder implementation contains only two TextView.
     */
    private class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView label;
        private TextView price;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.label = itemView.findViewById(R.id.label);
            this.price = itemView.findViewById(R.id.price);
        }
    }
}
