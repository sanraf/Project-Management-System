package com.algoExpert.demo.AppUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;
@Service
@Slf4j
public class ImageConvertor {

    public String imageToBase64(String imagePath) {
        try {

            ClassPathResource resource = new ClassPathResource("static/images/" + imagePath);
            InputStream inputStream = resource.getInputStream();

            byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);

            return java.util.Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            log.info("image conversion failure {} :",e.getMessage());
            return null;
        }
    }
}
