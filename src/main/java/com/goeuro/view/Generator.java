package com.goeuro.view;


import java.util.List;

public interface Generator<T> {

    void generate(List<T> entities);

}
