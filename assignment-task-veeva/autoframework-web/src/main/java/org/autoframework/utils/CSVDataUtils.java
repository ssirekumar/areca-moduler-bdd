package org.autoframework.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSVDataUtils {

	/**
	 * Create CSV file with these headers "Footer URL's", "Duplicates if any?"
	 * @param removeAllTheOutputFilesIfexists
	 * @return
	 * 
	 */
	public static String writeCSVFileWithUrlAndDuplicates(List<String> allTheUrlData, List<String> allTheDuplicate,
			boolean removeAllTheOutputFilesIfexists) {
		int iterationIndex = 0;
		String fileName = "Output";
		String folderPath = "output-files";
		String projectRoot = System.getProperty("project.basedir");
		File folder = new File(projectRoot, folderPath);

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String formattedDate = dateFormat.format(date);
		fileName = folder.getAbsolutePath() + File.separator + fileName + "_" + formattedDate + ".csv";
		try {
			if (!folder.exists()) {
				folder.mkdirs();
			} else {
				if (removeAllTheOutputFilesIfexists) {
					File[] files = folder.listFiles();
					if (files != null) {
						for (File file : files) {
							if (file.isFile()) {
								if (file.delete()) {
									System.out.println(
											"output-files folder files are deleted, Deleted file: " + file.getName());
								} else {
									System.err.println(
											"output-files folder files are failed to delete, Non Deleted file: "
													+ file.getName());
								}
							}
						}
					}
				}
			}
			FileWriter outputfile = new FileWriter(fileName);
			CSVWriter writer = new CSVWriter(outputfile);

			String[] header = { "Footer URL's", "Duplicates if any?" };
			writer.writeNext(header);

			String duplicateDataAdding = "";
			String duplicateData = "";
			List<String[]> dataPrimary = new ArrayList<>();
			for (String strings : allTheUrlData) {
				try {
					duplicateData = allTheDuplicate.get(iterationIndex);
					if (!duplicateData.isEmpty()) {
						duplicateDataAdding = duplicateData;
					}
				} catch (IndexOutOfBoundsException e) {
					duplicateDataAdding = "";
				}
				dataPrimary.add(new String[] { strings, duplicateDataAdding });
				iterationIndex = iterationIndex + 1;
			}
			writer.writeAll(dataPrimary);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
