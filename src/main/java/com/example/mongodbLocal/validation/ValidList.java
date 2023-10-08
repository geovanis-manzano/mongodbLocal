package com.example.mongodbLocal.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import lombok.experimental.Delegate;

@Data
public abstract class ValidList<E> implements List<E> {

    @Valid
    @Delegate
    private List<E> list = new ArrayList<>();
}