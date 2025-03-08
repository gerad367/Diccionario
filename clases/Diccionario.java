package clases;

/** 
 * Clase que representa un diccionario de claves 'K' a valores 'V'
 * 
 * */ 
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
        if ((used + 1) / capacity > 0.8) resize(capacity * 2);

        int i = key.hashCode() % capacity;
        while (keys[i] != null) {
            if (keys[i] == key) {
                values[i] = value;
                return;
            }
            i = (i + 1) % capacity;
        }
        keys[i] = key;
        values[i] = value;
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
            if (keys[i] == null) continue;
            int j = keys[i].hashCode() % new_capacity;
            while (new_keys[j] != null) j = (j + 1) % new_capacity;
            new_keys[j] = keys[i];
            new_values[j] = values[i];
        }
        keys = new_keys;
        values = new_values;
        capacity = new_capacity;
    }

    // Alejandro
    public void clear(){}

    // Gerad
    @SuppressWarnings("unchecked")
	public Diccionario<K, V> copy(){
        Diccionario<K, V> dict;
        Object[][] items = this.items();
        for (int i = 0; i < items.length; i++) {
            dict.set((K) items[i][0], (V) items[i][1]);
        }
        return dict;
    }

    // Alejandro
    public void fromKeys(){}

    // Ricardo
    public Object[][] items() {
        Object[][] items = new Object[this.used][2];
        for(int i = 0; i < this.used; i++) {
            if (this.keys[i] != null) {
                items[i][0] = this.keys[i];
                items[i][1] = this.values[i];
            }
        }
        return items;
    }

    // Gerad
    public Object[] keys(){
        Object[] keys = new Object[this.used];
        for (int i, j = 0; i < this.used; i++) {
            if (this.keys[i] != null)
                keys[j++] = this.keys[i];
        }
        return keys;
    }

    // Alberto
    public void pop(){}

    // Alejandro
    public void popItem(){}

    // Bea
    public void setDefault(){}

    // Alberto
    public void update(){}

    // Ricardo
    public  Object[] values() {
        Object[] values = new Object[this.used];
        for(int i = 0; i < this.used; i++) {
            if (this.values[i] != null) {
                values[i] = this.values[i];
            }
        }
        return values;
    }
}
