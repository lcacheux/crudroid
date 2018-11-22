package net.cacheux.crud;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Display a list of T items. Used by {@link CrudActivity} and should not be directly accessed.
 */
public class CrudListFragment<T> extends Fragment implements
    CrudListAdapter.ItemSelectedListener<T> {
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;
    private CrudListAdapter<T> adapter;
    private CrudActivity<T> activity;
    private T currentItem = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CrudActivity) {
            activity = (CrudActivity<T>) context;
        } else {
            throw new IllegalStateException(
                "CrudListFragment can only be attached to CrudActivity");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_crudlist, container, false);

        recyclerView = root.findViewById(R.id.item_list);
        actionButton = root.findViewById(R.id.add_button);

        DividerItemDecoration dividerItemDecoration
            = new DividerItemDecoration(recyclerView.getContext(),
            DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = activity.getListAdapter();
        adapter.setItemSelectedListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        registerForContextMenu(recyclerView);

        activity.getViewModel().readAll().observe(this, new Observer<List<T>>() {
            @Override
            public void onChanged(@Nullable List<T> ts) {
                adapter.setItemList(ts);
            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showAddItemDialog();
            }
        });

        return root;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
        ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        activity.getMenuInflater().inflate(R.menu.crud_item, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit) {
            activity.showEditItemDialog(currentItem);
        } else if (item.getItemId() == R.id.menu_delete) {
            activity.deleteItem(currentItem);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemSelected(T item) {
        currentItem = item;
    }

    T getCurrentItem() {
        return currentItem;
    }
}
