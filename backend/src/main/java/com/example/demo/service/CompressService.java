package com.example.demo.service;

import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class CompressService {
//    https://wildeveloperetrain.tistory.com/289


    public BufferedImage awtGraphics2D_TYPE(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();

        //RenderingHints
//        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);

        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);


        graphics2D.dispose();
        return resizedImage;
    }

    public BufferedImage scaledInstance_TYPE(BufferedImage originalImage, int targetWidth, int targetHeight) {


        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
//        Image.SCALE_AREA_AVERAGING   //Area Averaging 이미지 스케일링 알고리즘 사용
//        Image.SCALE_DEFAULT          //기본 이미지 스케일링 알고리즘 사용
//        Image.SCALE_FAST             //생성되는 이미지의 품질보다 이미지 생성 속도를 우선으로 하는 알고리즘 사용
//        Image.SCALE_REPLICATE        //ReplicateScaleFilter 클래스에 구현된 이미지 스케일링 알고리즘 사용
//        Image.SCALE_SMOOTH           //이미지 생성 속도보다 생성되는 이미지의 품질을 우선으로 하는 알고리즘 사용

        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);


        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

        return outputImage;
    }

    @SneakyThrows
    public BufferedImage thumbnailator_TYPE(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Thumbnails.of(originalImage)
                .size(targetWidth, targetHeight)
                .asBufferedImage();
    }

    public BufferedImage Imgscalr_TYPE(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(originalImage, targetWidth, targetHeight);
    }

    public BufferedImage Imgscalr_TYPE2(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(originalImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC
                ,targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
    }
}
