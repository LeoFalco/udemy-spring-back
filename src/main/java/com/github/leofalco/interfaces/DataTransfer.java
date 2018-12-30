package com.github.leofalco.interfaces;

public interface DataTransfer<D, E> {
    D asDTO();
    E asEntity();
}
