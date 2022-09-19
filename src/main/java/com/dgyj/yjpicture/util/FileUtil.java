package com.dgyj.yjpicture.util;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class FileUtil {


    @Value("${resources.uri_path}")
    private static String uriPath;

    @Value("${resources.location}")
    private static String targetRootLocation;

    public static HashMap<String, Object> fileUpload(String uploadPath, MultipartFile file) throws IOException {
        HashMap<String, Object> result = new HashMap<>();
        String now = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String nowmil = new SimpleDateFormat("yyyyMMddHHmmss.SSS").format(new Date());
        String fileSavePath = targetRootLocation + File.separatorChar + uploadPath + now;
        String path = File.separator + uploadPath + now;
        Path fileLoc = Paths.get(fileSavePath);

        if(!Files.isDirectory(fileLoc)){
            Files.createDirectories(fileLoc);
        }

        if(file != null && !file.isEmpty()){
            String oriFileName = file.getOriginalFilename();
            String newFileName = nowmil + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();

            Files.copy(file.getInputStream(), fileLoc.resolve(newFileName));

            result.put("path", Paths.get(path,newFileName));
            result.put("realPath", Paths.get(fileSavePath,newFileName));
            result.put("name",oriFileName);

        }

        return result;

    }
}
