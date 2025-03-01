public class Diccionario<K, V> {
    private int capacity;
    private int used;
    private Object[] keys;
    private Object[] values;

    public Diccionario(){
        this.capacity = 8;
        this.used = 0;
        this.keys = new Object[this.capacity];
        this.values = new Object[this.capacity];
    }

    public Diccionario(int initial_capacity) {
        this.capacity = initial_capacity;
        this.used = 0;
        this.keys = new Object[this.capacity];
        this.values = new Object[this.capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public void set(K key, V value) {
        if (capacity / used > 0.8) resize(capacity * 2);

        int initial_index = key.hashCode() % capacity;
        int i = initial_index;
        while (keys[i] == null) i = (i + 1) % capacity;

        used++;
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        for (int i = 0; i < capacity; i++) {
            if (key.equals((K) keys[i])) return (V) values[i];
        }
        return null;
    }

    private void resize(int new_capacity) {
        Object[] new_keys = new Object[new_capacity];
        Object[] new_values = new Object[new_capacity];
        for (int i = 0; i < capacity; i++) {
            new_keys[i] = keys[i];
            new_values[i] = values[i];
        }
        keys = new_keys;
        values = new_values;
        capacity = new_capacity;
    }
}