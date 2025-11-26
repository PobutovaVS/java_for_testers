package model;

public record ContactData(String id, String firstname, String middlename, String lastname, String mobile) {
    public ContactData() {
        this("", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.middlename, this.lastname, this.mobile);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.mobile);
    }

    public ContactData withMiddleName(String middlename) {
        return new ContactData(this.id, this.firstname, middlename, this.lastname, this.mobile);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, this.middlename, lastname, this.mobile);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, mobile);
    }

}