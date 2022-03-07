package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders = new ArrayList<Folder>();

	public NoteBook(){}

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
}
