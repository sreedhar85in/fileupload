package com.Javatips.framework.fileupload.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Javatips.framework.fileupload.exception.InternalException;
import com.Javatips.framework.fileupload.model.UploadResponse;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UploadService {

	public UploadResponse upload(HttpServletRequest request, MultipartFile file) throws IOException, InternalException {
		
		String fileExtension = getFileNamExtension(file.getOriginalFilename());
		log.info("The extension of the file is " + " " + fileExtension);
		if (!fileExtension.equalsIgnoreCase("csv")) {

			log.error("The extension of the file is NOT CSV, but it is " + " "
					+ getFileNamExtension(file.getOriginalFilename()));
			
			throw new InternalException(400, "file upload failed", "File extension is not in CSV format");

		}

		byte[] bytes = file.getBytes();
		Path currentDir = Paths.get(".");
		Path fullPath = currentDir.toAbsolutePath();
		log.info("currentDir abosulte path is " + " " + fullPath.toString());

		// code to write the file inside the current folder with the existing filename.
		Path path = Paths.get(file.getOriginalFilename());
		Files.write(path, bytes);
		log.info("Filename of the CSV is " + path.getFileName());

		// code to write the file inside a specific folder in Windows

		/*
		 * Path testPath = Paths.get("E:\\Sreedhar\\newFile.png"); Files.write(testPath,
		 * bytes);
		 */

		UploadResponse response = new UploadResponse();
		response.setOutcomeCode(200);
		response.setOutcomeMessage("Success");
		response.setInternalMessage("File uploaded");
		return response;
	}
	
	
	public String getFileNamExtension(String fileName) {
		
		
		return fileName.substring(fileName.lastIndexOf('.') + 1);
	}

}
