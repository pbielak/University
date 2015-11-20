package pl.bielak.csvparser.services;

import pl.bielak.csvparser.documents.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PeopleService {
  private List<Person> people = new ArrayList<>();

  public PeopleService(String fileName) throws IOException {
    readAllPeopleFromFile(fileName);
  }

  public List<Person> getPeople() {
    return people;
  }

  private void readAllPeopleFromFile(String fileName) throws IOException {
    Set<Person> personSet = new HashSet<>();
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String line;

    while(((line = reader.readLine()) != null)) {
      String[] info = line.split(",");
      personSet.add(new Person(info[0],info[1],info[2],info[3],info[4],info[5]));
    }

    people = new ArrayList<>(personSet);
  }
}