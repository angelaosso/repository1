package model;

public class Podcast extends Audio{

    private String description;
    private Category category;

    public Podcast(String name, String url, double duration, String description, Category category) {
        super(name, url, duration);
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String play(){
        return "The podcast " + getName() + " is playing...";
    }

}
