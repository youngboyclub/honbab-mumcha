//package yougboyclub.honbabstop.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.core.scheduler.Schedulers;
//import yougboyclub.honbabstop.domain.Chat;
//import yougboyclub.honbabstop.repository.ChatRepository;
//
//import java.time.LocalDateTime;
//
//@RequiredArgsConstructor
//@RestController
//public class ChatController {
//
//    private final ChatRepository chatRepository;
//
//    // Flux : 데이터를 여러건 리턴
//    // 이건 쪽지 보낼때 쓰면 된다
//    @CrossOrigin
//    @GetMapping(value = "/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Chat> getMsg(@PathVariable String sender, @PathVariable String receiver){
//        return chatRepository.mFindBySender(sender, receiver)
//                .subscribeOn(Schedulers.boundedElastic());
//    }
//
//    // 채팅 내역 다받기
//    @CrossOrigin
//    @GetMapping(value = "/chat/roomNum/{roomNum}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Chat> findByRoomNum(@PathVariable Integer roomNum) {
//        return chatRepository.mFindByRoomNum(roomNum).subscribeOn(Schedulers.boundedElastic());
//    }
//
//
//    // Mono : 데이터를 한건 리턴 void해도 상관 없긴함
//    @CrossOrigin
//    @PostMapping("/chat")
//    public Mono<Chat> setMsg(@RequestBody Chat chat) {
//        chat.setCreatedAt(LocalDateTime.now());
//        return chatRepository.save(chat);
//    }
//}
