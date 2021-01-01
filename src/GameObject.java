public class GameObject {
    private String name;
    private String description;

    public GameObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return getName() + ": " + getDescription();
    }
}