package User;

public class User {

    int id;
    String mail;
    String role;


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

    public void setRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }


}
