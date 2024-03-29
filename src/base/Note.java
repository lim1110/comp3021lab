package base;

import java.io.Serializable;
import java.util.Date;

public class Note implements Comparable<Note>, Serializable{
	private static final long serialVersionUID = 1L;
	private Date date;
	private String title;

	public Note(String title){
		this.title = title;
		date = new Date(System.currentTimeMillis());
	};

	public String getTitle(){
		return title;
	}

	public String toString(){
		return date.toString() + "\t" + title;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public int compareTo(Note note) {
		// TODO Auto-generated method stub
		if(date.before(note.date))
			return 1;
		else if(date.equals(note.date))
			return 0;
		else
			return -1;
	}


}
