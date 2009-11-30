package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import constants.IErroresConstantes;

public class UtilFile {
	
	public static final String FS = System.getProperty("file.separator");

	public static StringBuffer fileToString(File file) {

		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(file));
			try {
				String line = null; // not declared within while loop
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		StringBuffer returnStringBuffer = new StringBuffer(contents.toString());
		return returnStringBuffer;

	}

	public static boolean moveFile(File scrFile, File destFile, List errores) {

		// Destination directory
		File dir = new File(destFile.getAbsolutePath());
		if (!dir.isDirectory()) {
			errores.add(IErroresConstantes.DIRECTORIO_NO_ENCONTRADO);

			return false;
		}

		try {
			// FileUtils.copyFile(file, new File(fileOrigen.getAbsolutePath() +
			// FS + file.getName()) );
			FileUtils.moveFile(scrFile, new File(destFile.getAbsolutePath()
					+ FS + scrFile.getName()));
		} catch (IOException e) {
			System.out.println("ERROR AL MOVER EL ARCHIVO");
			throw new RuntimeException(e);
		}

		return true;
	}

	public static boolean deleteFile(File file) {

		try {
			FileUtils.forceDelete(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return true;
	}
	
	
}
