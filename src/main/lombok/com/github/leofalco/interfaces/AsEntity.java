package com.github.leofalco.interfaces;

import java.io.Serializable;

public interface AsEntity<E> extends Serializable {
    E asEntity();
}