package edu.kit.informatik.util;

/***Class with different methodes for a linked list

 * @author unyrg
 * @version 0.0.1
 * @param <T> Type of object you want to store
 */
public class List<T> {
    protected Container<T> start;
    protected Container<T> end;
    protected int size;

    /***constructor
     *
     * sets size of the new lest which is 0 because we never added an element to the list before
     */
    public List() {
        this.size = 0;
    }

    /***adds a new element to the list
     *
     * @param element data you want to store
     */
    public void push(T element) {
        Container<T> newContainer = new Container<T>(element, null);
        if (isEmpty()) {
            start = end = newContainer;
        } else {
            end = end.next = newContainer;
        }
        size++;
    }

    /***removes a specific element
     *
     * @param index position of element
     */
    public void remove(int index) {
        Container<T> c = this.start;
        if (index < size) {
            //empty list ?
            if (c == null) {
                return;
            }
            if (index == 0) {
                this.start = c.next;
                size--;
            }

            for (int i = 0; i < size; i++) {
                if (i == index - 1 && c.next != null) {
                    c.next = c.next.next;
                    size--;
                } else {
                    c = c.next;
                }
            }
        } else {
            System.out.println("Sorry!, cant delete out of index");
        }
    }

    /***gets a specific element
     *
     * @param index position of element
     * @return returns the element
     */
    public T get(int index) {
        Container<T> c = this.start;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return c.data;
            }
            c = c.next;
        }
        return null;
    }

    /***checks if a specific element is inside the list
     *
     * @param element element you want to check
     * @return true if its inside, false if not
     */
    public Boolean contains(T element) {
        for (Container<T> c = start; c != null; c = c.next) {
            if (c.data == element) {
                return true;
            }
        }
        return false;
    }

    /***checks if list is epmty
     *
     * @return true if empty, false if not
     */
    private Boolean isEmpty() {
        return size == 0;
    }

    /**
     * returns length of list
     *
     * @return length of list
     */
    public int length() {
        int count = 0;
        for (Container<T> c = start; c != null; c = c.next) {
            count++;
        }
        return count;
    }

    /***swaps the element of index with the next one
     *
     * @param index where you want to swap
     */
    public void swap(int index) {
        Container<T> c = start;
        T temp;
        if (c.data == c.next.data) {
            return;
        }

        for (int i = 0; c.next != null; c = c.next, i++) {
            if (index == i) {
                temp = c.data;
                c.data = c.next.data;
                c.next.data = temp;
            }
        }
    }
}
