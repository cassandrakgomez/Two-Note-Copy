package edu.myutsa.two_note;

public class Note {
    //Attributes for the Note class
    //Name and content of the note
    private String name;
    private String content;
    //constructor for the Note class
    public Note(){
        name = "";
        content = "";
    }
    //sets and gets for note class
    public void setName(String name){
        this.name = name;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getName(){
        return name;
    }
    public String getContent(){
        return content;
    }
}
