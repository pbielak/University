import javax.swing.*;
import java.awt.*;
import java.time.*;

/**
 * @author Piotr Bielak
 */
public class ClockPanel extends JPanel{
  private LocalTime time;
  private JLabel clockLabel;

  public ClockPanel() {
    time = LocalTime.now();
    clockLabel = new JLabel();
    clockLabel.setFont(new Font(this.getFont().getName(), Font.BOLD, 50));

    setBackground(Color.GRAY);
    add(clockLabel);
  }

  public void updateTime() {
    time = LocalTime.now();

    String currentTime = String.format("%02d:%02d:%02d", time.getHour(), time.getMinute(), time.getSecond());
    clockLabel.setText(currentTime);
  }

  public LocalTime getCurrentTime() {
    return time;
  }

}
