package com.group.appName;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class FileUploadController {

    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
	public @ResponseBody
	static String handleFileUpload(@RequestParam("file") MultipartFile multipartFile) {
		final String SAVE_LOCATION = "D:/upload/";
		String fileName = multipartFile.getOriginalFilename();
		String location = SAVE_LOCATION;
		File pathFile = new File(location + fileName);
		try {
			multipartFile.transferTo(pathFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Uploaded: " + multipartFile.getSize() + " bytes";

	}
}
