package das.anusha.wordswiper;

import androidx.annotation.RawRes;

public class Chord {
    int[] available;
    String[] extensions;
    String notes;
    int sound;
    String base;
    int myPos;
    //will play sound, and show check or x on buttons
    public Chord(String base, String[] extensions, int myPos, String notes, int rawSound){
        this.extensions = extensions;
        this.base = base;
        this.myPos = myPos;
        this.notes = notes;
        sound = rawSound;
    }
    public void setAvailable(int[] exts){
        available = exts;
    }
    public String getBase() {
        return base;
    }
    public String getNotes() {
        return notes;
    }
    public int getRandomExt(){
        return (int)(Math.random()*extensions.length);
    }
    public String getExtString(int indx){
        return extensions[indx];
    }
    public boolean isAvailable(int indx){
        for(int i: available)
            if (i==indx) return true;
        return false;
    }
    public int getMyPos() {
        return myPos;
    }
    public int getSound() {return sound; }

}
