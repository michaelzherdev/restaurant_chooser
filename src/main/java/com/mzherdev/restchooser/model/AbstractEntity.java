package com.mzherdev.restchooser.model;

public interface AbstractEntity {

    boolean isNew();

    void setId(Integer id);

    Integer getId();
}
