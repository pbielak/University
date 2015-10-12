import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalTime;

/**
 * @author Piotr Bielak
 */
public class AlarmClock extends JFrame implements Runnable {
  private ClockPanel clockPanel;
  private AlarmInputPanel alarmInputPanel;
  private final String MUSIC_FILENAME = "alarm_sound.wav";

  public AlarmClock() {
    this.clockPanel = new ClockPanel();
    this.alarmInputPanel = new AlarmInputPanel();

    makeLayout();
  }

  private void makeLayout() {
    setTitle("Alarm Clock");
    setResizable(false);
    setSize(new Dimension(500, 200));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    setLayout(new BorderLayout());

    add(clockPanel, BorderLayout.PAGE_START);
    add(alarmInputPanel, BorderLayout.PAGE_END);
  }

  @Override
  public void run() {
    while (true) {
      clockPanel.updateTime();

      if (alarmInputPanel.isAlarmSet()) {
        LocalTime currentTime = clockPanel.getCurrentTime();
        LocalTime alarmTime = alarmInputPanel.getAlarmTime();

        if (isTimeToStartAlarm(currentTime, alarmTime)) {
          playMusic();
          showMessage("Bip-bip-bip");
          alarmInputPanel.unsetAlarm();
        }
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
    }

  }

  private boolean isTimeToStartAlarm(LocalTime currentTime, LocalTime alarmTime) {
    return currentTime.compareTo(alarmTime) >= 0;
  }

  private void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "ALARM!", JOptionPane.PLAIN_MESSAGE);
  }

  private void playMusic() {
    try {
      InputStream in = new FileInputStream(MUSIC_FILENAME);
      AudioStream audioStream = new AudioStream(in);
      AudioPlayer.player.start(audioStream);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
  }
}
