package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Note> notes = new ArrayList<Note>();
	private String name;

	public Folder(String name) {
		this.name = name;
	}

	public void addNote(Note note){
		notes.add(note);
	}

	public String getName(){
		return name;
	}

	public ArrayList<Note> getNotes(){
		return notes;
	}

	public void sortNotes(){
		Collections.sort(notes);
	}
	
	public boolean removeNotes(String title){
		for(Note n: notes)
			if(n.getTitle().equals(title)){
				notes.remove(n);
				return true;
			}
				
		return false;
	}

	public List<Note> searchNotes(String keywords){
		List<Note> tampNote = new ArrayList<Note>();
		ArrayList<String> compareString = new ArrayList<String>();
		StringBuilder tampString = new StringBuilder();
		String[] tokens = keywords.split(" ", 0);

		boolean orAppear = false;
		for(int i = 0; i < tokens.length; i++){
			String word = tokens[i];
			if(word.equalsIgnoreCase("or")){
				orAppear = true;
			} else {
				if(orAppear){
					tampString.append(" " + word);
					compareString.add(tampString.toString());
					tampString.setLength(0);
					orAppear = false;
				} else {
					tampString.append(word);
				}
			}
		}

		if(tokens.length == 1)
		compareString.add(tokens[0]);

		for(Note note:notes){
			boolean match = false;
			for(String word: compareString){
				match = false;
				tokens = word.split(" ", 0);

				for(String orWord:tokens){
					if(note instanceof ImageNote){
						if(note.getTitle().matches("(?i).*" +orWord+ ".*"))
							match = true;
					} else {
						if(note.getTitle().matches("(?i).*" +orWord+ ".*"))
							match = true;
						if(((TextNote)note).content != null)
							if(((TextNote)note).content.matches("(?i).*" +orWord+ ".*"))
								match = true;
					}
				}
				if(!match)
					break;
			}
			if (match)
				tampNote.add(note);
		}

		return tampNote;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		// TODO
		for(Note note : notes){
			if(note instanceof TextNote)
				nText++;
			else nImage++;
		}

		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int compareTo(Folder folder) {
		// TODO Auto-generated method stub
		if(name.compareTo(folder.name) > 0)
			return 1;
		else if(name.compareTo(folder.name) < 0)
			return -1;
		else
			return 0;
	}

}
