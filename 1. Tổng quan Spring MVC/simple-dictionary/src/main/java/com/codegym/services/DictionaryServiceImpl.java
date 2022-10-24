package com.codegym.services;

import com.codegym.models.Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryServiceImpl implements IDictionaryService{
    private static Map<Integer, Dictionary> dictionaries = new HashMap<>();

    static {
        dictionaries.put(1, new Dictionary("hello", "xin chao"));
        dictionaries.put(2, new Dictionary("dog", "con cho"));
        dictionaries.put(3, new Dictionary("cat", "con meo"));
        dictionaries.put(4, new Dictionary("bye", "tam biet"));
        dictionaries.put(5, new Dictionary("goodbye", "tam biet"));
        dictionaries.put(6, new Dictionary("mouse", "con chuot"));
        dictionaries.put(6, new Dictionary("gailo", "Gai lo"));
    }

    @Override
    public List<Dictionary> findAll() {
        return new ArrayList<>(dictionaries.values());
    }
}
