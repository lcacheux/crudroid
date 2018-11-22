package net.cacheux.crud.example.storage;

/**
 * Base class for model classes with an id field.
 */
public class BaseItem {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
