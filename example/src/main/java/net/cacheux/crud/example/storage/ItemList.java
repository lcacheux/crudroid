package net.cacheux.crud.example.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrap a List of BaseItem to be able to update/delete item based on their id field instead of their
 * position in the list, and ensure that we won't have duplicate ids in the list.
 * @param <T>
 */
public class ItemList<T extends BaseItem> {

    private List<T> content = new ArrayList<>();

    public void add(T item) {
        item.setId(getNextAvailableId());
        content.add(item);
    }

    public boolean update(T item) {
        if (item.getId() > 0) {
            int i = getIndexFromId(item.getId());
            if (i >= 0) {
                content.set(i, item);
                return true;
            }
        }
        return false;
    }

    public boolean delete(T item) {
        if (item.getId() > 0) {
            int i = getIndexFromId(item.getId());
            if (i >= 0) {
                content.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<T> getContent() {
        return content;
    }

    private int getIndexFromId(int id) {
        for (int i = 0; i < content.size(); i++) {
            T item = content.get(i);
            if (item != null && item.getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private int getNextAvailableId() {
        int nextId = 1;
        for (BaseItem item : content) {
            if (item.getId() >= nextId) {
                nextId = item.getId() + 1;
            }
        }
        return nextId;
    }
}
