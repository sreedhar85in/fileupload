package com.Javatips.framework.fileupload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/uploadapi")
public class UploadController {
	
	@ApiOperation(value = "Upload API", notes= "This is to upload the file via API")
	@PostMapping("/fileupload")
	public ResponseEntity uploadController(HttpServletRequest request, @RequestPart("file") MultipartFile file) throws IOException {
		
		if(file.getOriginalFilename()==null) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(file.getOriginalFilename());
		Files.write(path, bytes);
		System.out.println(path.getFileName());		
		return new ResponseEntity("Files uploaded", HttpStatus.OK);
		
		
	}

}
