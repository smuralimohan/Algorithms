/**
 * Created by Murali M on 2/6/15.
 */
object StringReductions {
  def main(args: Array[String]) {
    val lines = io.Source.stdin.getLines.take(1);

    for (line <- lines) {
      println(printUnique(Array(),line.toCharArray, 0));
    }
  }

  def printUnique(unique: Array[Char], input: Array[Char], i: Int): String = {
    if (i >= input.length)
      return "";

    if (unique.contains(input(i))) {
      return printUnique(unique, input, i + 1)
    } else {
      return input(i) + printUnique(Array.concat(unique,Array(input(i))), input, i + 1)
    }
  }
}
