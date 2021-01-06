package maze_game.gameobjects;

public class Item extends GameObject {
    private double weight;

    public Item(String name, String description, double weight) {
        super(name, description);
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getLongDescription() {
        return getName() + " has weight of " + getWeight();
    }
}
