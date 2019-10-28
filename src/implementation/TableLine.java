package implementation;

public class TableLine {

    private int page, presenceBit, modificationBit, memoryLocation;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPresenceBit() {
        return presenceBit;
    }

    public void setPresenceBit(int presenceBit) {
        this.presenceBit = presenceBit;
    }

    public int getModificationBit() {
        return modificationBit;
    }

    public void setModificationBit(int modificationBit) {
        this.modificationBit = modificationBit;
    }

    public int getMemoryLocation() {
        return memoryLocation;
    }

    public void setMemoryLocation(int memoryLocation) {
        this.memoryLocation = memoryLocation;
    }
}
