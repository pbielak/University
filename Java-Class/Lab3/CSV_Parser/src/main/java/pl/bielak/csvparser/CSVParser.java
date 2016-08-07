package pl.bielak.csvparser;

import pl.bielak.csvparser.configuration.AppConfiguration;
import pl.bielak.csvparser.models.Person;
import pl.bielak.csvparser.services.CSVSaver;
import pl.bielak.csvparser.services.PeopleService;

import java.io.FileNotFoundException;

import static pl.bielak.csvparser.parsers.EmailParser.isValidEmail;
import static pl.bielak.csvparser.parsers.IpAddressParser.*;
import static pl.bielak.csvparser.parsers.PeselParser.*;

public class CSVParser {
  private PeopleService peopleService;
  private CSVSaver csvSaver;

  public CSVParser(final AppConfiguration appConfiguration) {
    this.peopleService = new PeopleService(appConfiguration);
    this.csvSaver = new CSVSaver(appConfiguration);
  }

  public void run() throws FileNotFoundException {
    for (Person person : peopleService.getPeople()) {
      String currentPesel = person.getPesel();
      String currentEmail = person.getEmail();
      String currentIpAddress = person.getIpAddress();

      try {
        if (isValidPesel(currentPesel) && isValidEmail(currentEmail) && isValidIpAddress(currentIpAddress)) {
          csvSaver.saveValidRecord(person.toString());
        } else {
          csvSaver.saveInvalidRecord(person.toString());
        }
      } catch (IllegalArgumentException e) {
        csvSaver.saveInvalidRecord(person.toString());
      }

    }

    csvSaver.closeWriters();
  }
}
