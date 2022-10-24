package com.codegym.services;

import com.codegym.models.Dictionary;

import java.util.List;

public interface IDictionaryService {
    List<Dictionary> findAll();
}
