package com.algoExpert.demo.AppUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;
@Service
@Slf4j
public class ImageConvertor {

    /**
     * Converts the image located at the specified image path to a Base64-encoded string.
     * The image must be stored in the 'static/images' directory of the classpath.
     *
     * @param imagePath The path to the image file relative to the 'static/images' directory.
     * @return A Base64-encoded string representing the image, or null if the conversion fails.
     * @Author Santos Rafaelo
     */
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
