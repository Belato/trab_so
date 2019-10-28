package implementation;

import java.io.Serializable;

public class Frame implements Serializable {        // Serializable é preciso para gravar objetos em arquivos.

    private String content;
    private int location, utilizationBit;					// utilizationBit para política de substituição Clock.

    Frame(){
        content = "vazio";
        utilizationBit = 0;
    }

    void setUsed(){ utilizationBit = 1; }

    void setUnused(){
        utilizationBit = 0;
    }

    void setContent(String content){
        this.content = content;
    }

    void setLocation(int location){ this.location = location; }

    public String getContent(){ return content; }

    public int getLocation() { return location; }

    public int getUtilizationBit() { return utilizationBit; }
}