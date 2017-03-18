package com.jennifer.controller.rest;

import com.jennifer.service.StorageService;
import com.jennifer.service.exception.StorageFileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controls all things related to uploading physical files to the server
 */

@RestController
public class RestUploadController {

    private static final Logger log = LoggerFactory.getLogger(RestUploadController.class);
    private final StorageService storageService;

    @Autowired
    public RestUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/file")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> upload(MultipartHttpServletRequest request) {
        MultipartFile multiPartFile = request.getFile("file");
        return Collections.singletonMap("newFileName", storageService.store(multiPartFile));
    }

    @DeleteMapping("/file/{filename:.+}")
    public ResponseEntity delete(@PathVariable String filename) {
        Path filePath = storageService.load(filename);
        if(!new File(filePath.toString()).exists()){
            return new ResponseEntity<>("No file found for name '" + filename + "'", HttpStatus.NOT_FOUND);
        }
        storageService.delete(filePath);
        return new ResponseEntity<>(filename, HttpStatus.OK);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        log.info("files/filesnameeeeeee");
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @GetMapping("/files")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", storageService
                .loadAll()
                .map(path -> MvcUriComponentsBuilder
                                .fromMethodName(RestUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

}
