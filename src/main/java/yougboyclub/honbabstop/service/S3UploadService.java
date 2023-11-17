//package yougboyclub.honbabstop.service;
//
//import com.amazonaws.services.kms.model.CustomKeyStoreInvalidStateException;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.DeleteObjectRequest;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class S3UploadService {
//
//    private final AmazonS3Client amazonS3Client;
//
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;
//
//    // TODO : 여러 이미지 파일 업로드 관련
//    public List<String> uploadImage(List<MultipartFile> multipartFile) {
//        List<String> fileNameList = new ArrayList<>();
//
//        // forEach 구문을 통해 multipartFile로 넘어온 파일들 하나씩 fileNameList에 추가
//        multipartFile.forEach(file -> {
//            String fileName = createFileName(file.getOriginalFilename());
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentLength(file.getSize());
//            objectMetadata.setContentType(file.getContentType());
//
//            try(InputStream inputStream = file.getInputStream()) {
//                amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
//                        .withCannedAcl(CannedAccessControlList.PublicRead));
//            } catch(IOException e) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
//            }
//
//            fileNameList.add(fileName);
//        });
//
//        return fileNameList;
//    }
//
//    //TODO: 파일 하나 삭제
//    public void deleteFile(String fileName) {
//        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
//    }
//
//    // TODO : 랜덤 이름 생성
//    private String createFileName(String fileName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
//        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
//    }
//
//    private String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
//        try {
//            return fileName.substring(fileName.lastIndexOf("."));
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
//        }
//    }
//
//
//
//
////    public String uploadFile(MultipartFile multipartFile) throws IOException {
////
////        // 파일 이름 중복 방지
////        String originalFilename = UUID.randomUUID()+"-"+multipartFile.getOriginalFilename();
////
////        // S3에 파일의 사이즈를 알려주기 위해 사용
////        ObjectMetadata metadata = new ObjectMetadata();
////
////        metadata.setContentLength(multipartFile.getSize());
////        metadata.setContentType(multipartFile.getContentType());
////
////        //putObject를 통해 파일 Stream을 열어 S3에 파일 업로드
////        amazonS3Client.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
////~
////        //getUrl은 S3에 업로드된 사진 URL을 가져오는 방식
////        return amazonS3Client.getUrl(bucket, originalFilename).toString();
////    }
//}
