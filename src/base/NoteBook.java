package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements java.io.Serializable{
	private ArrayList<Folder> folders = new ArrayList<Folder>();
	private static final long serialVersionUID = 1L;

	public NoteBook(){}

	public NoteBook(String file){
		FileInputStream fis = null;
		ObjectInputStream in = null;

		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			in.close();
			this.folders = n.folders;

		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public void addFolder(String folderName) {
	    // TO DO 
		folders.add(new Folder(folderName));
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}

	public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}

	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}

	public ArrayList<Folder> getFolders() {
		return folders;
	}

	public boolean insertNote(String folderName,Note note){
		Folder tampFolder = null;

		for (Folder folder : folders){
			if (folder.getName().equals(folderName))
				tampFolder = folder;
		}

		if(tampFolder == null){
			tampFolder = new Folder(folderName);
			folders.add(tampFolder);
		}

		for (Note n : tampFolder.getNotes()){
			if(n.equals(note)){
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		tampFolder.addNote(note);
		return true;
	}

	public void sortFolders(){
		for(Folder folder: folders)
			folder.sortNotes();

		Collections.sort(folders);
	}

	public List<Note> searchNotes(String keywords){
		List<Note> tampNote = new ArrayList<Note>();

		for(Folder folder:folders)
			tampNote.addAll(folder.searchNotes(keywords));
		return tampNote;
	}

	public boolean save(String file){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			return true;
	}

}
