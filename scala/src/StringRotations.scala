/**
 * Created by Murali M on 3/6/15.
 */
object StringRotations {

  def main(args: Array[String]) {
    solve(Integer.parseInt(io.StdIn.readLine()))
  }

  def solve(count:Int)  {
      if (count > 0) {
        val line = io.StdIn.readLine();
        println(rotateStrings(line, 1));
        solve(count - 1);
      }
  }

  def rotateStrings(input: String, i: Int): String = {
    if (i >= input.length)
      return input;

    return input.substring(i) + input.substring(0, i) + " " + rotateStrings(input, i+1);
  }
}
