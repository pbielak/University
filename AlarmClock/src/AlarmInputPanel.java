import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

/**
 * @author Piotr Bielak
 */
public class AlarmInputPanel extends JPanel implements ActionListener {
  private JTextField alarmHour, alarmMinute;
  private JButton confirmAlarmButton;
  private boolean isAlarmSet;
  private LocalTime alarmTime;

  public AlarmInputPanel() {
    alarmHour = new JTextField("12");
    alarmMinute = new JTextField("00");
    confirmAlarmButton = new JButton("OK!");
    isAlarmSet = false;
    alarmTime = LocalTime.of(12, 00);

    makeLayout();
    confirmAlarmButton.addActionListener(this);
  }

  public boolean isAlarmSet() {
    return isAlarmSet;
  }

  public void unsetAlarm() {
    isAlarmSet = false;
    enableFieldsAndButton(true);
  }

  public LocalTime getAlarmTime() {
    return alarmTime;
  }

  private void makeLayout() {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 4;
    c.insets = new Insets(5, 5, 5, 5);
    add(new JLabel("Ustaw alarm!"), c);

    c.gridy = 1;
    c.gridwidth = 1;
    add(new JLabel("Godzina:"), c);

    c.gridx = 1;
    add(alarmHour, c);

    c.gridx = 2;
    add(new JLabel("Minuta:"), c);

    c.gridx = 3;
    add(alarmMinute, c);

    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 4;
    c.fill = GridBagConstraints.HORIZONTAL;
    add(confirmAlarmButton, c);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == confirmAlarmButton) {
      if (alarmHourAndMinuteAreValid()) {
        enableFieldsAndButton(false);
        isAlarmSet = true;
        alarmTime = LocalTime.of(Integer.parseInt(alarmHour.getText()), Integer.parseInt(alarmMinute.getText()));
      } else {
        JOptionPane.showMessageDialog(this, "Nieprawidlowy format czasu");
      }
    }
  }

  public void enableFieldsAndButton(boolean value) {
    alarmHour.setEnabled(value);
    alarmMinute.setEnabled(value);
    confirmAlarmButton.setEnabled(value);
  }

  private boolean alarmHourAndMinuteAreValid() {
    String hour = alarmHour.getText();
    String minute = alarmMinute.getText();

    return (hour.matches("[0-9]|1[0-9]|2[0-4]") && minute.matches("[0-9]|[0-5][0-9]"));
  }

}
