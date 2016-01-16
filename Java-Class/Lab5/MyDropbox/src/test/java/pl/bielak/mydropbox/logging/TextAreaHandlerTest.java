package pl.bielak.mydropbox.logging;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Handler;
import java.util.logging.Logger;

import static org.testng.Assert.*;

public class TextAreaHandlerTest {

  private static final Logger LOGGER = Logger.getLogger("TestLogger");
  private TextAreaHandler handler;
  private JTextArea textArea;

  @BeforeMethod
  public void setUp() throws Exception {
    textArea = new JTextArea();
    handler = new TextAreaHandler(textArea);

    addHandlerToLogger(handler);
  }

  private void addHandlerToLogger(Handler myHandler) {
    Handler[] handlers = LOGGER.getHandlers();
    for(Handler h : handlers) {
      LOGGER.removeHandler(h);
    }

    LOGGER.addHandler(myHandler);
  }

  @Test
  public void loggerShouldWriteToTextArea() {
    final String message = "Hello world!";
    LOGGER.info(message);

    assertEquals(textArea.getText(), message + "\r\n");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void handlerShouldThrowExceptionForNullArgument() {
    handler = new TextAreaHandler(null);
  }

  @Test
  public void loggerShouldHaveTextAreaHandler() {
    Handler[] handlers = LOGGER.getHandlers();
    Handler myHandler = handlers[0];

    assertTrue((handlers.length == 1) && (myHandler instanceof TextAreaHandler));
  }
}