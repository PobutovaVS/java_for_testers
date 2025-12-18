package model;

public record ContactData(String id, String firstname, String middlename, String lastname, String mobile,
                          String photo, String home, String work, String phone2, String address, String email,
                          String email2, String email3) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.middlename, this.lastname, this.mobile, this.photo, this.home, this.work, this.phone2, this.address, this.email, this.email2, this.email3);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.mobile, this.photo, this.home, this.work, this.phone2, this.address, this.email, this.email2, this.email3);
    }

    public ContactData withMiddleName(String middlename) {
        return new ContactData(this.id, this.firstname, middlename, this.lastname, this.mobile, this.photo, this.home, this.work, this.phone2, this.address, this.email, this.email2, this.email3);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, this.middlename, lastname, this.mobile, this.photo, this.home, this.work, this.phone2, this.address, this.email, this.email2, this.email3);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, mobile, this.photo, this.home, this.work, this.phone2, this.address, this.email, this.email2, this.email3);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, this.work, this.phone2, this.address, this.email, this.email2, this.email3);
    }


    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, home, this.work, this.phone2, this.address, this.email, this.email2, this.email3);
    }


    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, work, this.phone2, this.address, this.email, this.email2, this.email3);
    }


    public ContactData withPhone2(String phone2) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, this.work, phone2, this.address, this.email, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, this.work, this.phone2, address, this.email, this.email2, this.email3);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, this.work, this.phone2, this.address, email, this.email2, this.email3);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, this.work, this.phone2, this.address, this.email, email2, this.email3);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.mobile, photo, this.home, this.work, this.phone2, this.address, this.email, this.email2, email3);
    }

}