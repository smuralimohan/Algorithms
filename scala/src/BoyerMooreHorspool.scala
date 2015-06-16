import java.util

import scala.collection.mutable.Map

/**
 * Created by Murali M on 5/6/15.
 */
object BoyerMooreHorspool {

  private var shiftValueTable: Map[Character, Integer] = Map()
  private var patternLength: Int = 0
  private var textLength: Int = 0

  def main(args: Array[String]) {
    val count = Integer.parseInt(io.StdIn.readLine());
    checkSubstring(count);
  }

  private def checkSubstring(i:Int): Unit = {
    if (i > 0) {
      val text = io.StdIn.readLine();
      val pat = io.StdIn.readLine();
      preProcess(pat)
      if (indexOf(text, pat, 0) != -1) println ("YES")
      else
      println ("NO")
      checkSubstring(i-1);
    }
  }
  private def getShiftValue(c: Character): Int = {
    if (shiftValueTable.contains(c))
      return shiftValueTable(c)
    else
      return patternLength
  }

  private def preProcess(pattern: String) {
    {
      shiftValueTable.clear();
      var i: Int = 0
      while (i < pattern.length) {
        {
          shiftValueTable(pattern.charAt(i)) = new Integer(Math.max(1, this.patternLength - 1 - i))
        }
        ({
          i += 1; i - 1
        })
      }
    }
  }

  def indexOf(text: String, pattern: String): Int = {
    return indexOf(text, pattern, 0)
  }

  def indexOf(text: String, pattern: String, beginAt: Int): Int = {
    this.textLength = text.length
    this.patternLength = pattern.length
    var i: Int = beginAt
    var matchFound: Boolean = false
    preProcess(pattern)
    while (i <= this.textLength - this.patternLength) {
      matchFound = true

        var j: Int = this.patternLength - 1
        while (j >= 0) {
          {
            if (text.charAt(i + j) != pattern.charAt(j)) {
              matchFound = false

            }
          }
          ({
            j -= 1; j + 1
          })
        }

      if (matchFound) {
        return i
      }
      else {
        i = i + getShiftValue(text.charAt(i + this.patternLength - 1))
      }
    }
    return -1
  }
}
