package utils;

/**
 * An interaction with the user consists of some input to send the program
 * and some output to expect.  We represent it as an object that takes in two
 * StringBuilders and produces the intended effects on them
 */
public interface Interaction {
  /**
   * Takes in two StringBuilders and produces an effect on them.
   *
   * @param in  input StringBuilder
   * @param out output StringBuilder
   */
  void apply(StringBuilder in, StringBuilder out);
}