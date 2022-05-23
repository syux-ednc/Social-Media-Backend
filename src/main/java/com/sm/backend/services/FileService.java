package com.sm.backend.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileService {

    public final String baseFolder = "C:/smFileStorage/";

    // Save file to its created folder
    public String save(String user, String postID, MultipartFile file) {
        String newFileName = "";
        Path targetPath = Paths.get(baseFolder + user + "/" + postID);
        try {
            if (!Files.exists(targetPath)) {
                Files.createDirectories(targetPath);
            }
            newFileName = generateFileName(file.getOriginalFilename());
            Files.copy(file.getInputStream(), targetPath.resolve(newFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFileName;
    }

    // Update file in its folder
    public String update(String user, String postID, MultipartFile file, String oldFileName) {
        String updatedFileName = "";
        try {
            updatedFileName = save(user, postID, file);
            Path pathToDelete = Paths.get(baseFolder + user + "/" + postID + "/" + oldFileName);
            delete(pathToDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedFileName;
    }

    // Delete either specific file or whole folder
    public void delete(Path input) {
        FileSystemUtils.deleteRecursively(input.toFile());
    }

    // Find file from its folder
    public Resource load(String user, String postID, String fileName) {
        try {
            Path target = Paths.get(baseFolder + user + "/" + postID + "/" + fileName);
            Resource resource = new UrlResource(target.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception("Could not read the file!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ---------------- TODO: Refactor Helper Methods ----------------------- */

    private String generateFileName(String input) {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        if (input == null) {
            return fileName;
        } else {
            int index = input.lastIndexOf('.');
            String extension = input.substring(index + 1);
            return fileName + "." + extension;
        }
    }
}
