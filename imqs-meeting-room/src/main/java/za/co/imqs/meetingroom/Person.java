package za.co.imqs.meetingroom;

/**
 * POJO for representing a person who could attend a meeting
 * Created by donovan on 2014/08/10.
 */
public class Person {

    int id;
    String firstName;
    String lastName;
    String avatarPath;

    private boolean isGroupheader = false;
     public Person(){

     }


    public Person(int id, String firstName, String lastName, String avatarPath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarPath = avatarPath;

    }



    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName){this.firstName =firstName;}
    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public String getAvatPat(){ return avatarPath;}
    public void setAvatarPath(String avatarPath){this.avatarPath =avatarPath;}


    @Override
    public boolean equals(Object person) {
        return person instanceof Person && ((Person)person).id == this.id;
    }


}
