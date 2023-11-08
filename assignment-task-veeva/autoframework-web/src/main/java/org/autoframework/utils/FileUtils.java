package org.autoframework.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class FileUtils {

	private static Properties properties;

	public static String readDataFromDriverConfigPropertiesFile(String Key) throws IOException {
		String getPropertyValue = "";

		// Use the class loader to load the resource file
		ClassLoader classLoader = FileUtils.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("DriverConfig.properties");

		if (inputStream == null) {
			throw new IOException("DriverConfig.properties Resource file is not found.");
		}

		try {
			properties = new Properties();
			properties.load(inputStream);
			getPropertyValue = properties.getProperty(Key);
			System.out.println("Web Base URL: " + getPropertyValue.trim());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return getPropertyValue;
	}

	/**
	 * 
	 * @param dataInput
	 * @return
	 */
	public static boolean writeDataIntoTextFile(List<String> dataInput) {
		String fileName = "output";
		String folderName = "output_files";
		boolean writenTheContent = false;
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String formattedDate = dateFormat.format(date);
			folderName = folderName + formattedDate;
			File folder = new File(folderName);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			fileName = folderName + File.separator + fileName + "_" + formattedDate + ".txt";

			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(dataInput.toString());
			bufferedWriter.newLine();
			bufferedWriter.close();
			System.out.println("Date has been written to " + fileName);
		} catch (IOException e) {
			writenTheContent = false;
			e.printStackTrace();
		}
		return writenTheContent;
	}

	/**
	 * 
	 * @param dataInput
	 * @return
	 */
	public static boolean writeDataIntoTextFile(StringBuilder dataInput, boolean removeAllTheOutputFilesIfexists) {
		String fileName = "Output"; 
		String folderPath = "output-files";
		boolean writenTheContent = false;
		String projectRoot = System.getProperty("project.basedir");
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String formattedDate = dateFormat.format(date);
            File folder = new File(projectRoot, folderPath);
            fileName = folder.getAbsolutePath() + File.separator + fileName + "_"+ formattedDate + ".txt";
            
            if (!folder.exists()) {
                folder.mkdirs();
            }else {
            	if(removeAllTheOutputFilesIfexists) {
            		File[] files = folder.listFiles();
            		if (files != null) {
                        for (File file : files) {
                            if (file.isFile()) {
                                if (file.delete()) {
                                    System.out.println("output-files folder files are deleted, Deleted file: " + file.getName());
                                } else {
                                    System.err.println("output-files folder files are failed to delete, Non Deleted file: " + file.getName());
                                }
                            }
                        }
            		}
            	}
            } 
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(dataInput.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
        	writenTheContent = true;
        } catch (IOException e) {
        	writenTheContent = false;
            e.printStackTrace();
        }
        return writenTheContent;
	}

}
