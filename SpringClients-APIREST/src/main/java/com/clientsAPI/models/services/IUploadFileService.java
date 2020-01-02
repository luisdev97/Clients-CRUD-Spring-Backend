package com.clientsAPI.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public Resource load(String imgName) throws MalformedURLException;
	public String copy(MultipartFile file) throws IOException;
	public boolean remove(String imgName);
	public Path getPath(String imgName);
	
}
