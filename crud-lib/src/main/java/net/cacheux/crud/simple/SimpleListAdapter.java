package net.cacheux.crud.simple;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.cacheux.crud.CrudListAdapter;

/**
 * A simple list adapter to use the standard Android simple list item layout. T should override the
 * toString method to display proper text.
 * @param <T>
 */
public class SimpleListAdapter<T> extends CrudListAdapter<T> {
    public SimpleListAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void bindItem(RecyclerView.ViewHolder holder, T item) {
        ((TextView) holder.itemView).setText(item.toString());
    }
}
