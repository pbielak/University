package pl.bielak.mydropbox.logging;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TextAreaHandler extends Handler{

  private JTextArea area;

  public TextAreaHandler(JTextArea area) {
    if(area == null) {
      throw new IllegalArgumentException("Text area mustn't be null!");
    }

    this.area = area;
  }

  @Override
  public void publish(LogRecord record) {
    SwingUtilities.invokeLater(() -> {
      StringWriter text = new StringWriter();
      PrintWriter out = new PrintWriter(text);

      out.print(area.getText());
      out.println(record.getMessage());
      area.setText(text.toString());
    });
  }

  @Override
  public void flush() {

  }

  @Override
  public void close() throws SecurityException {

  }
}
