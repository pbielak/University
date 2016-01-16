package pl.bielak.mydropbox.views;

import pl.bielak.mydropbox.MyDropbox;
import pl.bielak.mydropbox.logging.TextAreaHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static javax.swing.JFileChooser.DIRECTORIES_ONLY;

public class MainFrame extends JFrame implements ActionListener{
  private MyDropbox myDropbox;
  private JButton chooseDirButton;
  private JLabel dir;
  private JSpinner threads;
  private JButton startButton;
  private JTextArea area;

  public MainFrame() {
    initUI();
    myDropbox = new MyDropbox();
    myDropbox.setLoggerHandler(new TextAreaHandler(area));
    setVisible(true);
  }

  private void initUI() {
    setTitle("My Dropbox Clone");
    setSize(new Dimension(700, 400));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    add(makeMainPanel());
  }

  private JPanel makeMainPanel() {
    JPanel mainPanel = new JPanel();

    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    c.fill = HORIZONTAL;
    c.weightx = 0.8;

    dir = new JLabel(new File("").getAbsolutePath());
    dir.setHorizontalAlignment(JLabel.CENTER);
    mainPanel.add(dir, c);

    c.gridx = 2;
    c.gridwidth = 1;
    c.weightx = 0.2;

    chooseDirButton = new JButton("Wybierz folder!");
    chooseDirButton.addActionListener(this);
    mainPanel.add(chooseDirButton, c);


    c.gridy = 1;
    c.gridx = 0;
    c.weightx = 0.8;
    c.gridwidth = 2;
    c.insets = new Insets(20, 0, 20, 0);
    JLabel threadsLabel = new JLabel("Liczba wątków: ");
    threadsLabel.setHorizontalAlignment(JLabel.CENTER);
    mainPanel.add(threadsLabel, c);

    c.gridx = 2;
    c.weightx = 0.2;
    c.gridwidth = 1;
    threads = new JSpinner(new SpinnerNumberModel(5, 1, 20, 1));
    mainPanel.add(threads, c);

    c.gridy = 2;
    c.gridx = 2;
    startButton = new JButton("Start!");
    startButton.addActionListener(this);
    mainPanel.add(startButton, c);

    c.gridy = 3;
    c.gridx = 0;
    c.gridwidth = 3;
    c.ipady = 50;
    c.insets = new Insets(50, 5, 5, 5);

    area = new JTextArea();
    area.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(area);
    mainPanel.add(scrollPane, c);

    return mainPanel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == startButton) {
      if(startButton.getText().equals("Start!")) {
        startButton.setText("Stop!");
        myDropbox.setThreadPoolSize((int) threads.getValue());
        myDropbox.setDirToListen(dir.getText());
        myDropbox.startListening();
      }
      else {
        startButton.setText("Start!");
        myDropbox.stopListening();
      }
    }
    else if(e.getSource() == chooseDirButton) {
      JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));
      chooser.setDialogTitle("Wybierz folder do nasłuchiwania...");
      chooser.setFileSelectionMode(DIRECTORIES_ONLY);
      chooser.setAcceptAllFileFilterUsed(false);

      if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        dir.setText(chooser.getSelectedFile().toString());
      }
    }
  }
}
