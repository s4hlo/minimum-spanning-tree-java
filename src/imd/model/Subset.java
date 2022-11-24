package imd.model;

public class Subset {

    public Subset(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }

    public Subset() {
    }

    private int parent, rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
