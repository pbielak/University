package pl.bielak.csvparser.services;

import pl.bielak.csvparser.configuration.AppConfiguration;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVSaver {
  private final AppConfiguration appConfiguration;
  private PrintWriter invalidRecordsWriter;
  private int outputFileCounter;
  private List<String> recordBuffer;

  public CSVSaver(final AppConfiguration appConfiguration) {
    this.appConfiguration = appConfiguration;
    this.recordBuffer = new ArrayList<>();
    this.outputFileCounter = 0;
    initInvalidRecordsWriter();
  }

  private void initInvalidRecordsWriter() {
    try {
      this.invalidRecordsWriter = new PrintWriter("result/" + appConfiguration.getErrorsFilename() + ".csv");
    } catch (FileNotFoundException e) {
      throw new RuntimeException("An error occurred while creating invalidRecordsWriter: " + e.getMessage());
    }
  }

  public void saveValidRecord(String record) {
    recordBuffer.add(record);

    if(recordBuffer.size() == appConfiguration.getRowsPerFile()) {
      flushBuffer();
    }
  }

  private void flushBuffer() {
    try (PrintWriter validRecordsWriter = new PrintWriter(getNextValidRecordsFilename())) {
      recordBuffer.forEach(validRecordsWriter::println);
      recordBuffer.clear();
    } catch (FileNotFoundException e) {
      throw new RuntimeException("An error occurred while saving valid records: " + e.getMessage());
    }

  }

  private String getNextValidRecordsFilename() {
    return String.format("result/%s_%s_%s.csv", appConfiguration.getOutputFilename(), recordBuffer.size(), ++outputFileCounter);
  }

  public void saveInvalidRecord(String record) {
    invalidRecordsWriter.println(record);
  }

  public void closeWriters() {
    if(!recordBuffer.isEmpty()) {
      flushBuffer();
    }
    invalidRecordsWriter.close();
  }
}
