package com.sm.backend.controller;

import com.sm.backend.entity.ResponseMessage;
import com.sm.backend.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/createFile")
    public ResponseEntity<ResponseMessage> createFile(
            @RequestParam("user") String user,
            @RequestParam("postID") String postID,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String fileName = fileService.save(user, postID, file);
            String generatedURL = MvcUriComponentsBuilder.fromMethodName(
                    FileController.class,
                    "getFileURL",
                    user, postID, fileName
            ).build().toString();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(generatedURL));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.toString()));
        }
    }

    @PostMapping("/updateFile")
    public ResponseEntity<ResponseMessage> updateFile(
            @RequestParam("user") String user,
            @RequestParam("postID") String postID,
            @RequestParam("file") MultipartFile file,
            @RequestParam("oldFileName") String oldFileName
    ) {
        try {
            String fileName = fileService.update(user, postID, file, oldFileName);
            String generatedURL = MvcUriComponentsBuilder.fromMethodName(
                    FileController.class,
                    "getFileURL",
                    user, postID, fileName
            ).build().toString();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(generatedURL));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.toString()));

        }
    }

    @DeleteMapping("/deleteSingle/{user:.+}/{postID}")
    public ResponseEntity<ResponseMessage> deleteSingle(
            @PathVariable("user") String user,
            @PathVariable("postID") String postID
    ) {
        try {
            fileService.delete(Paths.get(fileService.baseFolder + user + "/" + postID));
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.toString()));
        }
    }

    @DeleteMapping("/deleteAll/{user}")
    public ResponseEntity<ResponseMessage> deleteAll(@PathVariable("user") String user) {
        try {
            fileService.delete(Paths.get(fileService.baseFolder + user));
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.toString()));
        }
    }

    @GetMapping("/getFileURL/{user:.+}/{postID}/{fileName:.+}")
    public ResponseEntity<Resource> getFileURL(
            @PathVariable String user,
            @PathVariable String postID,
            @PathVariable String fileName
    ) {
        Resource file = fileService.load(user, postID, fileName);
        String extension = fileName.substring(fileName.lastIndexOf("."));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(
                HttpHeaders.CONTENT_DISPOSITION,
                " attachment; filename=\"" + file.getFilename() + "\"");
        if(extension.equals(".mp4")){
            httpHeaders.add(
                    HttpHeaders.CONTENT_TYPE,
                    "video/mp4");
        }
        return ResponseEntity.ok().headers(httpHeaders).body(file);
    }
}
