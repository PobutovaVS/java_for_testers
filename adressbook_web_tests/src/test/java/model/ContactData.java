package model;

public record ContactData(String id, String firstname, String middlename, String lastname, String mobile,
                          String photo, String home, String work, String secondary) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.middlename, this.lastname, this.mobile, this.photo, this.home, this.work, this.secondary);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.mobile, this.photo, this.home, this.work, this.secondary);
    }

    public ContactData withMiddleName(String middlename) {
        return new ContactData(this.id, this.firstname, middlename, this.lastname, this.mobile, this.photo, this.home, this.work, this.secondary);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, this.middlename, lastname, this.mobile, this.photo, this.home, this.work, this.secondary);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, mobile, this.photo, this.home, this.work, this.secondary);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, this.work, this.secondary);
    }


    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, home, this.work, this.secondary);
    }


    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, work, this.secondary);
    }


    public ContactData withPhone2(String phone2) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, this.work, phone2);
    }

}