package am.domain;

import java.util.ArrayList;

import java.util.List;
import java.util.function.Function;

public class ExtendList<T> extends ArrayList {

    int size;
    int[] arr;

    ExtendList(int capacity) {
        this.arr = new int[capacity];
    }

    ExtendList() {
        this(10);
    }

    public Object get(int index) {
        if (index >= this.size || index < 0) {
            System.err.println("Array index error. ");
            return -1;
        }
        return this.arr[index];
    }

    void add(int value) {
        this.checkAndGrow(1);
        this.arr[this.size++] = value;
    }

    void addAll(int[] values) {
        this.checkAndGrow(values.length);
        for (int value : values) {
            this.arr[this.size++] = value;
        }
    }

    void replace(int index, int value) {
        if (index >= this.size || index < 0) {
            System.err.println("Array index error. ");
            return;
        }
        this.arr[index] = value;
    }

    void replaceAll(int index, int[] values) {
        if (index >= this.size || index < 0) {
            System.err.println("Array index error. ");
            return;
        }
        if (index + values.length > this.size) {
            System.err.println("Array size error. ");
            return;
        }

        for (int value : values) {
            this.arr[index++] = value;
        }
    }

    void insert(int index, int value) {
        if (index >= this.size || index < 0) {
            System.err.println("Array index error. ");
            return;
        }
        this.checkAndGrow(1);
        for (int i = this.size - 1; i >= index; i--) {
            this.arr[i + 1] = this.arr[i];
        }
        this.arr[index] = value;
        this.size++;
    }

    void insertAll(int index, int[] values) {
        if (index >= this.size || index < 0) {
            System.err.println("Array index error. ");
            return;
        }
        this.checkAndGrow(values.length);
        for (int i = this.size - 1; i >= index; i--) {
            this.arr[i + values.length] = this.arr[i];
        }

        for (int value : values) {
            this.arr[index++] = value;
        }
        this.size += values.length;
    }

    int removeByIndex(int index) {
        if (index >= this.size || index < 0) {
            System.err.println("Array index error. ");
            return -1;
        }
        int value = this.arr[index];
        for (int i = index; i < this.size - 1; i++) {
            this.arr[i] = this.arr[i + 1];
        }
        this.size--;
        return value;
    }

    int removeByValue(int value) {
        for (int i = 0; i < this.size; i++) {
            if (value == this.arr[i]) {
                removeByIndex(i);
                return i;
            }
        }
        return -1;
    }

    void removeAll(int[] values) {
        for (int value : values) {
            this.removeByValue(value);
        }
    }

    void remove(int from, int to) {
        if (to < from || from >= this.size || from < 0 || to > this.size) {
            System.err.println("Array index error. ");
        }
        int count = to - from;
        for (int i = from; i < this.size; i++) {
            this.arr[i] = this.arr[i + count];
        }
        this.size -= count;
    }

    ExtendList subArray(int from, int to) {
        if (to < from || from >= this.size || from < 0 || to > this.size) {
            System.err.println("Array index error. ");
        }
        ExtendList da = new ExtendList(to - from);
        for (int i = from; i < to; i++) {
            da.add(this.arr[i]);
        }
        return da;
    }

    public void trimToSize() {
        if (this.arr.length > this.size) {
            int[] newArray = new int[this.size];
            for (int i = 0; i < this.size; i++) {
                newArray[i] = this.arr[i];
            }
            this.arr = newArray;
        }
    }

    void sort() {
        for (int i = 0; i < arr.length; i++) {
            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    void reverse() {
        for (int i = 0, j = this.size - 1; i < this.size / 2; i++, j--) {
            swap(i, j);
        }
    }

    void swap(int i, int j) {
        this.arr[i] ^= this.arr[j];
        this.arr[j] ^= this.arr[i];
        this.arr[i] ^= this.arr[j];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    void checkAndGrow(int count) {
        if (this.size + count > this.arr.length) {
            int newLength = (this.size + count) +
                    ((this.size + count) >> 1) + 1;
            int[] newArr = new int[newLength];
            for (int i = 0; i < this.size; i++) {
                newArr[i] = this.arr[i];
            }
            this.arr = newArr;
        }
    }


    public ExtendList<T> map(Function<Object, Object> maps) {
        Object[] elements = super.toArray();
        ExtendList<T> newList = new ExtendList<>();
        for (Object el : elements) {
            newList.add(maps.apply(el));
        }
        return newList;
    }

    public <T> void fill(List<? super T> list, T obj) {
        List list1 = new ArrayList<Integer>();
        for (int i = 1; i < 100; i++) {
            list.add(obj);
        }
        System.out.println(list1);
    }


}
