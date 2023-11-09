//package yougboyclub.honbabstop.domain;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//
//@Data
//@Document(collection = "chat")
//public class Chat {
//    @Id
//    private String id;
//    private String message;
//    private String sender; // 보내는 사람
//    private String receiver; // 받는 사람(이건 귓속말 할때 중요하다)
//    private Integer roomNum; // 방 번호(어떤 사람이 어떤 방에 보내는지가 중요하다)
//
//    private LocalDateTime createdAt;
//}
