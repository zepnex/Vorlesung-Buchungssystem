package edu.kit.informatik.util;

/***Container class for linked list
 *
 * @author unyrg
 * @version 0.0.1
 *
 * @param <T> data of type whatever you want to save
 */
public class Container<T> {
    protected T data;
    protected Container<T> next;

    /***constructor
     *
     * @param data data you want to store
     * @param next following Node of the list
     */
    public Container(T data, Container<T> next) {
        if (data != null) {
            this.data = data;
            this.next = next;
        }

    }
}
