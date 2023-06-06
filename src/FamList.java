public class FamList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private int capacity;
    private Object[] internalArray;


    public FamList() {
        this.capacity = DEFAULT_CAPACITY;
        internalArray = new Object[this.capacity];
    }
    public T get(int index) {
        return (T) this.internalArray[index];
    }
    
    public int size() {
        return this.size;
    }
    
    private void resize() {
        int newCapacity = this.capacity * 2;
        Object[] resizedData = new Object[newCapacity];
        for (int i = 0; i < this.size; i++) {
            resizedData[i] = this.internalArray[i];
        }
        this.internalArray = resizedData;
        this.capacity = newCapacity;
    }
    
    public void add(T value) {
        if (capacity == size) {
            resize();
        }
        internalArray[size] = value;
        size++;
    }
    public void remove(int index) {
        if (index != this.size) {
            shiftDownToIndex(index);
        }
        this.internalArray[this.size] = null;
        size--;
    }

    private void shiftDownToIndex(int index) {
        for (int i = index; i < this.size; i++) {
            internalArray[i] = internalArray[i + 1];
        }

    }
    public boolean contains(T value) {
        for (int i = 0; i < this.size; i++) {
            T currentValue = (T) internalArray[i];
            if (currentValue != null && currentValue.equals(value)) {
                return true;
            }
        }
        return false;
    }
    public FamList<T> subList(int startpos, int finalpos){

        FamList<T> fList = new FamList<>();
        
        for(int i=0;i<finalpos - startpos;i++){
            fList.add((T) internalArray[startpos + i]);
        }

        return fList;

    }

    public String toString() {
        String value = "[";

        for (int i = 0; i < size; i++) {
            value = value + internalArray[i];
            if (i != size - 1) {
                value = value + ", ";
            }
        }
        value = value + "]";
        return value;
    }    
}
