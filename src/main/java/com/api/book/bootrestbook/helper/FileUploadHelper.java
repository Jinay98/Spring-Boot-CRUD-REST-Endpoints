package com.api.book.bootrestbook.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public final String UPLOAD_DIR = new ClassPathResource("/META-INF/resources/images/").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile multipartFile) {
        boolean result = false;
        try {
            //Method 1 Using Streams
//            InputStream is = multipartFile.getInputStream();
//            byte data[] = new byte[is.available()];
//            is.read(data);
//
//            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename());
//            fos.write(data);
//
//            fos.flush();
//            fos.close();

            Files.copy(multipartFile.getInputStream(),
                    Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
