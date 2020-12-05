package by.xxx.pupil.model;

public class Move {

    private final int i;
    private final int j;

    public Move(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public String toString() {
        return "Move{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}