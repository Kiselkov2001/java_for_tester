package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    public ContactRecord(){}
    public ContactRecord(int id, String firstname, String lastname, String address){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }
    @Id
    @Column(name = "id")
    public int id;
    public String firstname;
    public String lastname;
    public String address;
    public Date deprecated = new Date();
}
