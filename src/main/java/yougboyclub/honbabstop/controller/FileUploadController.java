package yougboyclub.honbabstop.controller;

import com.amazonaws.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import yougboyclub.honbabstop.service.S3UploadService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final S3UploadService s3UploadService;


    @PostMapping("/file")
    public ResponseEntity<List<String>> uploadFile(@RequestPart List<MultipartFile> files) {
        return ResponseEntity.ok(s3UploadService.uploadImage(files));
    }


//    @PostMapping("/upload")
//    public ResponseEntity<String > uploadFile(@RequestParam("images")MultipartFile multipartFile) throws IOException {
//        String url = s3UploadService.uploadFile(multipartFile);
//        return ResponseEntity.ok(url);
//
//    }
}
