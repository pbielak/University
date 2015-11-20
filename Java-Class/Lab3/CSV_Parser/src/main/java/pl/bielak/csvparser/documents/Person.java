package pl.bielak.csvparser.documents;

public class Person {
  private String pesel;
  private String name;
  private String surname;
  private String email;
  private String country;
  private String ipAddress;

  public Person(String pesel, String name, String surname, String email, String country, String ipAddress) {
    this.pesel = pesel;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.country = country;
    this.ipAddress = ipAddress;
  }

  public String getPesel() {
    return pesel;
  }

  public String getEmail() {
    return email;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  @Override
  public String toString() {
    return String.format("%s,%s,%s,%s,%s,%s", pesel, name, surname, email, country, ipAddress);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Person person = (Person) o;

    return pesel.equals(person.pesel);

  }

  @Override
  public int hashCode() {
    return pesel.hashCode();
  }
}
