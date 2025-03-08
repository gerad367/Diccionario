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
    public void copy(){}

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
    public void keys(){}

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
>>>>>>> 5530b22b9b2560e0f2f700861391f52a809578b0


    public static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }
    
    //popitem
    public Pair<K, V> popitem() {
        if (used == 0) {
            throw new IllegalStateException("Dictionary is empty");
        }

        for (int i = capacity - 1; i >= 0; i--) {
            if (keys[i] != null) {
                K key = (K) keys[i];
                V value = (V) values[i];
                keys[i] = null;
                values[i] = null;
                used--;
                return new Pair<>(key, value);
            }
        }
        throw new IllegalStateException("Error, dictionary is not empty, but cannot pop item");
    }

    public static <K, V> Diccionario<K, V> fromkeys(K[] keys, V value) {
        if (keys.length == 0) {
            throw new IllegalArgumentException("The keys array is empty.");
        }
        Diccionario<K, V> nuevoDiccionario = new Diccionario<>(keys.length);
        for (K k : keys) {
            nuevoDiccionario.set(k, value);
        }
        return nuevoDiccionario;
    }

    public static <K> Diccionario<K, Object> fromkeys(K[] keys) {
        if (keys.length == 0) {
            throw new IllegalArgumentException("Error, the keys array is empty.");
        }
        return fromkeys(keys, null);
    }

    //clear
    public void clear() {
        if(this.used == 0) {
            throw new IllegalStateException("Error, dictionary is empty.");
        }
        this.used = 0;
        this.keys = new Object[this.capacity];
        this.values = new Object[this.capacity];
    }
}
}
}
