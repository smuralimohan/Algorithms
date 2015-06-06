/**
 * Created by Murali M on 5/6/15.
 */
object PascalsTriangle {

  def main(args:Array[String]) {
    printTriangle(0, io.StdIn.readInt());
  }

  def fact(n:Int):Int = {
     if (n == 0) return 1;
    return n * fact(n-1);
  }

  def printTriangle(i:Int, k:Int):Unit = {
    if (i <= k) {
      printNumbers(i)
      printTriangle(i+1, k);
    }
  }

  def printNumbers(i:Int) = {
    printCoef(i,0);
    println();
  }

  def printCoef(n:Int, r:Int) {
    if (r <= n) {
      print(fact(n)/(fact(r) * fact(n-r)) + " ")
      printCoef(n, r+1);
    }
  }
}
