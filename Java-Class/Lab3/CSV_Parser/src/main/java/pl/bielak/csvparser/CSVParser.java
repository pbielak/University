package pl.bielak.csvparser;


import pl.bielak.csvparser.documents.Person;
import pl.bielak.csvparser.properties.ConfigService;
import pl.bielak.csvparser.services.CSVSaver;
import pl.bielak.csvparser.services.PeopleService;

import java.io.FileNotFoundException;
import java.io.IOException;

import static pl.bielak.csvparser.parsers.EmailParser.isValidEmail;
import static pl.bielak.csvparser.parsers.IpAdressParser.*;
import static pl.bielak.csvparser.parsers.PeselParser.*;

public class CSVParser {
  private ConfigService configService;
  private PeopleService peopleService;
  private CSVSaver csvSaver;

  public CSVParser() throws IOException {
    initServices();
    parse();
  }

  private void initServices() throws IOException {
    configService = new ConfigService();
    peopleService = new PeopleService(configService.getProperty("filename"));
    csvSaver = new CSVSaver(configService.getProperty("outputFilename"), configService.getProperty("errorsFilename"), configService.getInteger("rowsPerFile"));
  }

  private void parse() throws FileNotFoundException {
    for (Person person : peopleService.getPeople()) {
      String currentPesel = person.getPesel();
      String currentEmail = person.getEmail();
      String currentIpAddress = person.getIpAddress();

      try {
        if (isValidPesel(currentPesel) && isValidEmail(currentEmail) && isValidIpAdress(currentIpAddress)) {
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
