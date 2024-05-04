package edu.myutsa.two_note;

import java.util.ArrayList;

public class NoteManager {
    //Attributes for the NoteManager class
    //Arraylist of notes
    private ArrayList<Note> notes;
    int userId;
    //constructor for the NoteManager class
    public NoteManager(int id){
        this.notes = new ArrayList<Note>();
        this.userId = id;

    }
    //adds a note to the arraylist
    //Naming convention is ID of the user followed by the name of the note separated by an underscore
    public void addNote(String name, String content){
        Note note = new Note();
        note.setName(name);
        note.setContent(content);
        notes.add(note);
    }


}
