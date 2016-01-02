package pl.bielak.quiz.backend.utils

public class ServiceUtils {
  static boolean checkIfArguementsAreBlank(Object...args) {
    return args.inject(false, {value, arg ->  value || (!arg)})
  }
}
