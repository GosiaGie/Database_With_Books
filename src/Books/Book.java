package Books;

public class Book {

    int id;
    String title;
    String first_name;
    String last_name;
    String price;
    String imagePath;

    public Book( ){
    }

    public Book(int id, String title, String first_name, String last_name, String price, String imagePath){

        this.id = id;
        this.title = title;
        this.first_name = first_name;
        this.last_name = last_name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String first_name) { this.first_name = first_name; }

    public void setLastName(String last_name) { this.last_name = last_name; }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
