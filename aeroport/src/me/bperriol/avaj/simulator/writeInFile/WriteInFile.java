package me.bperriol.avaj.simulator.writeInFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import me.bperriol.avaj.simulator.exceptions.CustomException;

public class WriteInFile {

	private String filename;
	
	private static final WriteInFile INSTANCE = new WriteInFile();

	private WriteInFile() {}

	public static WriteInFile getInstance() {
		return INSTANCE;
	}

	public void create(String name) throws CustomException{
		PrintWriter writer = null;

		try {
			File file = new File(name);
			this.filename = name;

			if (file.createNewFile()) {

				// file has been created
			}
			else {

				// file already exists
				writer = new PrintWriter(file);
			}
		}
		catch (Exception e) {
			throw new CustomException("Cannot create or open file: " + name);
		}
		finally {
			if (writer != null)
				writer.close();
		}
	}

	public void write(String str) throws CustomException{

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
			writer.println(str);
		} catch (IOException e) {
			throw new CustomException("Cannot write to file");
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}
