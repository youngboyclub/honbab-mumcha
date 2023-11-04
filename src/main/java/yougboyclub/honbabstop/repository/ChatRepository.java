package yougboyclub.honbabstop.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;
import yougboyclub.honbabstop.domain.Chat;

public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {

    //Flux : 흐름 => 데이터를 계속 흘려서 받겠다(한번만 받고 끝나는게 아니라!)
    @Tailable // 커서를 안닫고 유지한다
    @Query("{sender: ?0,receiver: ?1}") // 몽고디비 문법임 각각 sender와 receiver의 내용을 받아오겠다
    Flux<Chat> mFindBySender(String sender, String receiver);

    @Tailable
    @Query("{roomNum: ?0}")
    Flux<Chat> mFindByRoomNum(Integer roomNum);
}
