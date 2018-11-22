package net.cacheux.crud;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A {@link RecyclerView.Adapter} implementation for the CRUD list fragment.
 * @param <T>
 */
public abstract class CrudListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<T> itemList;
    private final int layoutId;
    private ItemSelectedListener<T> itemSelectedListener;

    /**
     * Implementations of this class must provide a proper layoutId for list items.
     * @param context
     * @param layoutId
     */
    protected CrudListAdapter(Context context, int layoutId) {
        inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    void setItemSelectedListener(ItemSelectedListener<T> itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutId, parent, false);

        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (itemList != null) {
            final T item = itemList.get(position);
            bindItem(holder, item);

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemSelectedListener != null) {
                        itemSelectedListener.onItemSelected(item);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        if (itemList != null) {
            return itemList.size();
        }
        return 0;
    }

    /**
     * Instanciate a new {@link RecyclerView.ViewHolder} which retains references to views used by
     * the layout passed to the constructor.
     * @param view
     * @return
     */
    public abstract RecyclerView.ViewHolder createViewHolder(View view);

    /**
     * Bind the values from the T item into the views retained by the ViewHolder.
     * @param holder
     * @param item
     */
    public abstract void bindItem(RecyclerView.ViewHolder holder, T item);

    void setItemList(List<T> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    interface ItemSelectedListener<T> {
        void onItemSelected(T item);
    }
}
