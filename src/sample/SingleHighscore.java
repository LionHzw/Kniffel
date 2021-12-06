package sample;

public class SingleHighscore {
    private final int total;
    private final String name;
    private final int[] points;

    public SingleHighscore(int total, String name, int[] points) {
        this.total = total;
        this.name = name;
        this.points = points;
    }

    public int getTotal() {
        return total;
    }

    public String getName() {
        return name;
    }

    public int[] getPoints() {
        return points;
    }
}
