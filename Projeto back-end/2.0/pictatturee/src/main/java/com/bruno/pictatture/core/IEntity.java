package com.bruno.pictatture.core;

import java.io.Serializable;

public interface IEntity<T> extends Serializable {
    T getId();
}
