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
        if (key == null) return;

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

    // Ricardo
    public Object[][] items() {
        int j = 0;
        Object[][] items = new Object[this.used][2];
        for(int i = 0; i < this.capacity; i++) {
            if (this.keys[i] != null) {
                items[j][0] = this.keys[i];
                items[j][1] = this.values[i];
                j++;
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
    public V pop(K key) {
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i].equals(key)) {
                V value = (V) values[i];
                keys[i] = null;
                values[i] = null;
                used--;
                return value;
            }
        }
        return null;
    }

    // Bea
    public V setDefault(K key, V default){

    	for(int i=0; i<this.capacity;i++){
        	if(key.equals((K) keys[i])) return (V) values[i];
    	}
    	this.set(clave, default);
    	return default;
    }

    // Alberto
    public void update(K key, V value) {
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        
        for (int i = 0; i < capacity; i++) {
            if (keys[i] == null) { 
                keys[i] = key;
                values[i] = value;
                used++;
                return;
            }
        }
        resize(capacity * 2);
        update(key, value);
    }

    // Ricardo
    public  Object[] values() {
        int j = 0;
        Object[] values = new Object[this.used];
        for(int i = 0; i < this.capacity; i++) {
            if (this.keys[i] != null) {
                values[j] = this.values[i];
                j++;
            }
        }
        return values;
    }

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
    @SuppressWarnings("unchecked")
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
