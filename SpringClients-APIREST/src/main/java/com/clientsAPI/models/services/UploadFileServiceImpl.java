package com.clientsAPI.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	private final static String UPLOAD_PATH = "uploads";
 

	@Override
	public Resource load(String imgName) throws MalformedURLException {
		Path filePath = getPath(imgName);
		log.info(filePath.toString());
		Resource resource = new UrlResource(filePath.toUri());
		
		
		if(!resource.exists() && !resource.isReadable()) {
			
			filePath = Paths.get("src/main/resources/static/img").resolve("noUser.pgn").toAbsolutePath();
			resource = new UrlResource(filePath.toUri());
			
			log.error("Error: Couldn't load image -> " + imgName);
		}
		return resource;
	}

	
	@Override
	public String copy(MultipartFile file) throws IOException {
		
		String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ","");
		Path filePath = getPath(UPLOAD_PATH);
		log.info(filePath.toString());
		
		//Si todo sale bien copy mueve el archivo subido al servidor a la ruta elegida
		Files.copy(file.getInputStream(), filePath);
		
		return fileName;
	}

	
	@Override
	public boolean remove(String imgName) {
		if(imgName != null && imgName.length() > 0) {
			
			Path previousImagePath = Paths.get("uploads").resolve(imgName).toAbsolutePath();
			File previousFile = previousImagePath.toFile();
			
			if(previousFile.exists() && previousFile.canRead()) {
				previousFile.delete();
				return true;
			}
			
		}
		return false;
	}

	
	@Override
	public Path getPath(String imgName) {
		return Paths.get(UPLOAD_PATH).resolve(imgName).toAbsolutePath();
	}

}
