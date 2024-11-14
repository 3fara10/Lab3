package org.example.domain.factory;

import org.example.domain.Entity;

public interface IEntityFactory<T extends Entity> {
    String toString(T entity);
    T fromString(String string);
}
