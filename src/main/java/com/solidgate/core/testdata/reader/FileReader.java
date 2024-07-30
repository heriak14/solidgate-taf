package com.solidgate.core.testdata.reader;

import java.util.List;

public interface FileReader<T> {

    T readOne();

    List<T> readAll();
}
