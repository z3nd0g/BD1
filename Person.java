public class Person {
    private int ID;
    private String FirstName;
    private String LastName;
    private String Gender;
    //add date....
    private String BirthDate;
    private String BirthPlace;

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }
    public void setGender(String gender) {
        Gender = gender;
    }

     public String getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }
    public void setBirthPlace(String birthPlace) {
        BirthPlace = birthPlace;
    }
}
