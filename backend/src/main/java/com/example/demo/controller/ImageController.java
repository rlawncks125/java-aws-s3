package com.example.demo.controller;


import com.example.demo.service.CompressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ImageController {

    private final CompressService compressService;

    @GetMapping
    public String hello(){
        return " hello";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("files")MultipartFile[] files ){

        List<MultipartFile> fs= Arrays.stream(files).toList();

        fs.forEach(file -> {
            System.out.println("Get File Name : " + file.getOriginalFilename());



            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss");
                String currentTime = LocalDate.now() +"T" + LocalTime.now().format(formatter);

                String savePath = "./images/"+ currentTime + "-" + file.getOriginalFilename()  ;


//                MultipartFile 파일로 받은 형태의 파일 바로 저장
//                file.transferTo(Path.of(savePath));

// ############ 이미지 리사이징

//                1. MultipartFile to buffierIamge
                BufferedImage image = ImageIO.read(file.getInputStream());

//                2.
//                BufferedImage resizeImage = compressService.awtGraphics2D_TYPE(image,100,100);
//                BufferedImage resizeImage = compressService.scaledInstance_TYPE(image,100,100);
                BufferedImage resizeImage = compressService.thumbnailator_TYPE(image,100,100); // 이것도 비율 조절 됨
//                BufferedImage resizeImage = compressService.Imgscalr_TYPE(image,100,100); // 이것도 비율 조절 됨

//                3. BufferedImage 타입 저장
                ImageIO.write(resizeImage, "jpg", new File(savePath));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return "hihi";
    }

}
