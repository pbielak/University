package pl.bielak.mydropbox;

import pl.bielak.mydropbox.views.MainFrame;

import javax.swing.*;

public class MyDropboxRunner {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(MainFrame::new);
  }
}
