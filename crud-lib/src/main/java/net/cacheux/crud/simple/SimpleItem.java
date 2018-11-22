package net.cacheux.crud.simple;

/**
 * Implement this interface to be able to use the {@link SimpleEditViewBinder} implementation.
 */
public interface SimpleItem {
    /**
     * This method implementation must set the value of the object. Usually, the object will
     * contains only one string data field, but it could also be a numeric field and this method
     * will convert it from string.
     * @param value
     */
    void setValue(String value);
}
