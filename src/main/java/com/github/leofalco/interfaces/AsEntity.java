package com.github.leofalco.interfaces;

import java.io.Serializable;

public interface AsEntity<Entity> extends Serializable {
    Entity asEntity();
}