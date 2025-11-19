package model;

public record ContactData(String firstname, String middlename, String lastname, String mobile) {
    public ContactData() {
        this("", "", "", "");
    }

    public ContactData withFirsName(String firstname) {
        return new ContactData(firstname, this.middlename, this.lastname, this.mobile);
    }

    public ContactData withMiddleName(String middlename) {
        return new ContactData(this.firstname, middlename, this.lastname, this.mobile);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.firstname, this.middlename, lastname, this.mobile);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.firstname, this.middlename, this.lastname, mobile);
    }

}