package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Likes;

import java.util.List;

public interface LikesService{
    List<Likes> findByUserNo(Long id);
}
