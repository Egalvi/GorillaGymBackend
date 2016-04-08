package kg.gorillagym.shop.content.cachedpicture;

/**
 * Simple cache interface
 */
public interface Cache<T> {
    void put(String key, T value);

    T get(String key);
}
