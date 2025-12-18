package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;

    public String firstname;

    public String middlename;

    public String lastname;

    public String mobile;

    public String nickname = new String();
    public String company = new String();
    public String title = new String();
    public String address;
    public String home;
    public String work;
    public String phone2;
    public String fax = new String();
    public String email;
    public String email2;
    public String email3;
    public String homepage = new String();


    public ContactRecord() {

    }

    public ContactRecord(int id, String firstname, String lastname, String middlename, String mobile, String home, String work, String phone2, String address, String email, String email2, String email3) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.mobile = mobile;
        this.home = home;
        this.work = work;
        this.phone2 = phone2;
        this.address = address;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
    }

    //public List<GroupRecord> groups;

}
