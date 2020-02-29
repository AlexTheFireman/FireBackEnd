package com.group.appName;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileUploadController {

	@RequestMapping(value = "/u", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile multipartFile) {
		return "Uploaded: " + multipartFile.getSize() + " bytes";
	}
}
