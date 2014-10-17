
package rover

import scala.io.Source
import rover.model.Rover
import rover.util.Coordinates
import rover.util.Validator._

/**
 * Main class
 * @author Mihai Anghel
 */
object App {

  def main(args: Array[String]): Unit = {

    val fileIterator = if (args(0) == null) throw new IllegalArgumentException("No input file provided !") else Source.fromFile(args(0)).getLines();

    val gridCoordinates: (Int, Int) = validateGridCoordinates(fileIterator.next)

    var rovers = List[(Rover, String)]()

    while (fileIterator.hasNext) {
      val c = fileIterator.next;
      val cm = fileIterator.next;
      val coords = validateRoverCoordinates(c)
      val r = new Rover(coords._1, coords._2, coords._3);
      rovers = rovers ::: List((r, cm))
    }

    rovers.foreach(x => println(x._1.move(x._2)((gridCoordinates._1, gridCoordinates._2))))
  }

}