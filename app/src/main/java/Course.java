public class Course {
    private String category;
    private String name;
    private String price;
    private String rating;

    public Course(String category, String name, String price, String rating) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }
}
