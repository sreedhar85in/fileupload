package com.Javatips.framework.fileupload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Javatips.framework.fileupload.exception.InternalException;
import com.Javatips.framework.fileupload.model.UploadResponse;
import com.Javatips.framework.fileupload.service.UploadService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/uploadapi")
public class UploadController {
	
	
	@Autowired
	UploadService uploadService;
	
	@ApiOperation(value = "Upload API", notes= "This is to upload the file via API")
	@PostMapping("/fileupload")
	public ResponseEntity uploadController(HttpServletRequest request, @RequestPart("file") MultipartFile file) throws IOException, InternalException {
		
		if(file.getOriginalFilename()==null) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		UploadResponse response = uploadService.upload(request, file);
		
		return new ResponseEntity(response, HttpStatus.OK);
		
		
	}

}
