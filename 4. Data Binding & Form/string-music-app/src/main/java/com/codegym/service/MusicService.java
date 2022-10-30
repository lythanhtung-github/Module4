package com.codegym.service;

import com.codegym.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicService implements IMusicService{
    private static final List<Music> musicList;
    static {
        musicList = new ArrayList<>();
    }
    @Override
    public List<Music> findAll() {
        return musicList;
    }

    @Override
    public void save(Music music) {
        musicList.add(music);
    }

    @Override
    public Music findById(int id) {
        return musicList.get(id);
    }

    @Override
    public void update(int id, Music newMusic) {
        for (Music music : musicList) {
            if (music.getId() == id) {
                music = newMusic;
                break;
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < musicList.size(); i++) {
            if (musicList.get(i).getId() == id) {
                musicList.remove(i);
                break;
            }
        }
    }
}
