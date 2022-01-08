package uz.episodeone.merchants.domain.generic;

public interface Identity<T> {
    T getId();
    void setId(T id);
}
