package com.api.book.bootrestbook.controllers;

import com.api.book.bootrestbook.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {
    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        System.out.println(file.getSize());
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getContentType());
//        System.out.println(file.getName());
        try {
            //Validation
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File needs to be sent to this request");
            }
            boolean result = fileUploadHelper.uploadFile(file);
            if (result) {
//                return ResponseEntity.ok("File Uploaded Successfully");
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("resources/images/").path(file.getOriginalFilename()).toUriString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong try again");


    }
}
