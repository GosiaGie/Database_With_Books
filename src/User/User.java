package User;

public class User {

    int id;
    String mail;

    public User(int id, String mail){
        this.id = id;
        this.mail = mail;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
       return mail;
    }


}
