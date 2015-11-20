package pl.bielak.csvparser.services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVSaver {
  private final String outputFilename;
  private final int rowsPerFile;
  private int outputFileCounter;
  private PrintWriter invalidRecordsWriter;
  private List<String> recordBuffer;

  public CSVSaver(String outputFilename, String errorFilename, int rowsPerFile) throws FileNotFoundException {
    this.outputFilename = outputFilename;
    this.rowsPerFile = rowsPerFile;
    this.outputFileCounter = 0;
    this.invalidRecordsWriter = new PrintWriter("result/" + errorFilename + ".csv");
    this.recordBuffer = new ArrayList<>();
  }

  public void saveValidRecord(String record) throws FileNotFoundException {
    recordBuffer.add(record);

    if(recordBuffer.size() == rowsPerFile) {
      flushBuffer();
    }
  }

  private void flushBuffer() throws FileNotFoundException {
    PrintWriter validRecordsWriter = new PrintWriter("result/" + outputFilename + "_" + recordBuffer.size() + "_" + (++outputFileCounter) + ".csv");
    recordBuffer.forEach(validRecordsWriter::println);
    recordBuffer.clear();
    validRecordsWriter.close();
  }

  public void saveInvalidRecord(String record) {
    invalidRecordsWriter.println(record);
  }

  public void closeWriters() throws FileNotFoundException {
    if(!recordBuffer.isEmpty()) {
      flushBuffer();
    }
    invalidRecordsWriter.close();
  }
}
