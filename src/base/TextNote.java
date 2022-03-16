package base;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note{
	String content;

	public TextNote(String title){
		super(title);
	}

	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}

	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	private String getTextFromFile(String absolutePath) {
		String result = "";
		try{
			BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(absolutePath)));
			while ((result = br.readLine()) != null);
			br.close();

		} catch (FileNotFoundException e){
			e.printStackTrace();

		} catch (IOException e){
			e.printStackTrace();
		}


		return result;
	}

	public void exportTextToFile(String pathFolder) {
		if(pathFolder == "")
			pathFolder = ".";
		File file = new File(pathFolder + File.separator + getTitle().replace(" ", "_") + ".txt");
		
		try {
			FileWriter out = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(out);
			bw.write(content);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
