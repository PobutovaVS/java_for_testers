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
    public String address = new String();
    public String home;
    public String work;
    public String phone2;
    public String fax = new String();
    public String email = new String();
    public String email2 = new String();
    public String email3 = new String();
    public String homepage = new String();


    public ContactRecord() {

    }

    public ContactRecord(int id, String firstname, String lastname, String middlename, String mobile) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.mobile = mobile;
    }

   //public List<GroupRecord> groups;

}
