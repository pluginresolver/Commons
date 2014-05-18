package com.caved_in.commons.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Folders {

	private String folderLocation = "";
	private Map<String, String> filesInFolder = new HashMap<>();

	public Folders(String folderLocation) {
		this.folderLocation = folderLocation;
		initData();
	}

	private void initData() {
		for (File folderFile : FileUtils.listFiles(new File(folderLocation), null, false)) {
			filesInFolder.put(folderFile.getName(), folderLocation + folderFile.getName());
		}
	}

	/**
	 * Gets all the files in the folderhandlers location
	 *
	 * @return
	 */
	public Collection<String> getFiles() {
		return filesInFolder.values();
	}

	/**
	 * Checks if the file exists, ignoring all case
	 *
	 * @param fileName
	 * @return True if it does, false otherwise
	 */
	public boolean fileExists(String fileName) {
		return filesInFolder.containsValue(folderLocation + fileName);
	}

}