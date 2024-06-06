package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // Get the original name of the file
        String name = file.getOriginalFilename();

        // Generate a random id
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));

        // Create the file path
        String filePath = path + File.separator + fileName;

        // Create the directory if it doesn't exist
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Create the file
        File f = new File(filePath);

        // Copy the file to the path
        Files.copy(file.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return name;
    }

    @Override
    public InputStream getResource(String path, String FileName) throws FileNotFoundException {
        String fullPath = path + File.separator + FileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
