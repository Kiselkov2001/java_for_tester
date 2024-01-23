package model;

public record ContactData(String id, String firstname, String lastname, String photo,
                          String address, String home, String email) {
    public static String[] fld = new String[]{
            "id",
            "firstname",
            "middlename",
            "lastname",
            "nickname",
            "title",
            "company",
            "address",
            "home",
            "email"};

    public ContactData() {
        this("", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.photo, this.address, this.home, this.email);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.photo, this.address, this.home, this.email);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.photo, this.address, this.home, this.email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, address, this.home, this.email);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.address, home, this.email);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.address, this.home, email);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, photo, this.address, this.home, this.email);
    }

    public static int compareById(ContactData o1, ContactData o2) {
        return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    }

    public String repr() {
        return String.format("%s %s %s %s", id, lastname, firstname, address);
    }
}
