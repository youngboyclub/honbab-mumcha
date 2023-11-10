package yougboyclub.honbabstop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.repository.LikesRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikesServiceImpl implements LikesService{
    private final LikesRepository likesRepository;
    @Override
    public List<Likes> findByUserNo(Long id) {
        return likesRepository.findByUserNo(id);
    }
}
