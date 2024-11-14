package org.example.validators;

import org.example.domain.Entity;
import org.example.exceptions.EntityException;
import org.example.exceptions.ValidationException;

public interface IValidator<T extends Entity> {
    void validate(T entity) throws EntityException;

}
