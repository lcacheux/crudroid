package net.cacheux.crud;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Edit fragment from {@link CrudActivity}, to display a create/edit popup for an item.
 * This fragment is used internally by {@link CrudActivity} and should not be used with other
 * activities.
 */
public class CrudEditFragment<T> extends DialogFragment {

    private static final String TAG = "CrudEditFragment";

    private CrudActivity<T> activity;
    private EditViewBinder<T> viewBinder;
    private View content;
    private T item;

    public void show(FragmentManager manager) {
        show(manager, (T) null);
    }

    public void show(FragmentManager manager, T item) {
        this.item = item;
        show(manager, TAG);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CrudActivity) {
            activity = (CrudActivity<T>) context;
            viewBinder = activity.getEditViewBinder();
        } else {
            throw new IllegalStateException(
                    "CrudListFragment can only be attached to CrudActivity");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        content = viewBinder.getLayout();
        if (item != null) {
            viewBinder.bindToView(content, item);
        }

        return new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.ic_menu_edit)
                .setTitle(getText(item != null ? R.string.edit_item : R.string.add_item))
                .setView(content)
                .setPositiveButton(getText(R.string.save),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (item == null) {
                                    item = viewBinder.createNew();
                                    viewBinder.updateFromView(content, item);
                                    activity.createItem(item);
                                } else {
                                    viewBinder.updateFromView(content, item);
                                    activity.updateItem(item);
                                }
                                dismiss();
                            }
                        }
                )
                .setNegativeButton(getText(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();
                            }
                        }
                )
                .create();
    }
}
