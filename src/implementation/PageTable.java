package implementation;

import java.util.ArrayList;
import java.util.List;

public class PageTable {

    private List<TableLine> tableLines;
    private int Id;

    PageTable(int pages, int Id){
    	this.Id = Id;
        tableLines = new ArrayList<>();
        for(int i = 0; i < pages; i++){
            tableLines.add(new TableLine());
            tableLines.get(i).setPage(i);
        }
    }

    // Setters

    void setPagePresenceBit(int page, int newValue){
        tableLines.get(page).setPresenceBit(newValue);
    }

    void setPageModificationBit(int page, int newValue){
        tableLines.get(page).setModificationBit(newValue);
    }

    void setPageMemoryLocation(int page, int newValue){
        tableLines.get(page).setMemoryLocation(newValue);
    }

    // Getters

    int getPagePresenceBit(int page){
        return tableLines.get(page).getPresenceBit();
    }

    int getPageModificationBit(int page){
        return tableLines.get(page).getModificationBit();
    }

    int getPageMemoryLocation(int page){
        return tableLines.get(page).getMemoryLocation();
    }

    public List<TableLine> getTableLines(){ return tableLines; }

}
