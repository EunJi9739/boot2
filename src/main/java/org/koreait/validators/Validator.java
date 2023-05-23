package org.koreait.validators;

public interface Validator<T> extends RequiredValidator{
    void check(T t);
}
