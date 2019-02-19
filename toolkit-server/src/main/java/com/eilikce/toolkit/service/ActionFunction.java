package com.eilikce.toolkit.service;

import com.eilikce.toolkit.action.ActionException;

import java.util.Optional;

@FunctionalInterface
public interface ActionFunction<R>  {

    Optional<R> apply() throws ActionException;

}
