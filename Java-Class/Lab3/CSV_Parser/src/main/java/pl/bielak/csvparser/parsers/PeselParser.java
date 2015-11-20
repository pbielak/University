package pl.bielak.csvparser.parsers;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class PeselParser {

  private static int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

  public static boolean isValid(String pesel) {
    if (pesel == null || !pesel.matches("[0-9]{11}")) {
      throw new IllegalArgumentException();
    }

    List<Integer> digits = getDigits(pesel);

    int checksum = getChecksum(digits);
    int controlDigit = digits.get(10);

    return (checksum == controlDigit);
  }

  private static List<Integer> getDigits(String pesel) {
    return asList(pesel.split("")).stream().map(Integer::parseInt).collect(Collectors.toList());
  }

  private static int getChecksum(List<Integer> digits) {
    int checksum = 0;

    for (int i = 0; i < 10; i++) {
      checksum += weights[i] * digits.get(i);
    }

    checksum = checksum % 10;

    if (checksum != 0) {
      checksum = 10 - checksum;
    }

    return checksum;
  }

}
