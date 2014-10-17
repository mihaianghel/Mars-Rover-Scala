package rover.util

/**
 * Validator utility class
 * @author Mihai Anghel
 */
object Validator {

  /**
   * Validates the grid coordinates
   */
  def validateGridCoordinates(coordinates: String): (Int, Int) = coordinates.trim.split(" ").toList match {
    case List("") => throw new IllegalArgumentException("No grid coordinates !")
    case (x :: (y :: Nil)) => {
      val length = toInt(x) match {
        case Some(int) => int
        case None => throw new IllegalArgumentException("Wrong input parameters !")
      }
      val height = toInt(y) match {
        case Some(int) => int
        case None => throw new IllegalArgumentException("Wrong input parameters !")
      }

      (length, height)
    }
    case _ => throw new IllegalArgumentException("Wrong input parameters !")
  }

  /**
   * Validates the rover coordinates
   */
  def validateRoverCoordinates(coordinates: String): (Int, Int, Coordinates.Value) = coordinates.trim.split(" ").toList match {
    case List("") => throw new IllegalArgumentException("No rover coordinates !")
    case (x :: (y :: (z :: Nil))) => {
      val xCoord = toInt(x) match {
        case Some(int) => int
        case None => throw new IllegalArgumentException("Wrong input parameters !")
      }
      val yCoord = toInt(y) match {
        case Some(int) => int
        case None => throw new IllegalArgumentException("Wrong input parameters !")
      }

      translateNoSuchElemExToIllegalArgumentEx(z) match {
        case orientation: Coordinates.Value => (xCoord, yCoord, orientation)
        case e : Throwable => throw e
      }
    }
    case _ => throw new IllegalArgumentException("Wrong input parameters !")
  }

  /**
   * Converts the String to Option[Int]
   */
  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }

  /**
   * Translates NoSuchElementException to IllegalArgumentException for consistency
   */
  def translateNoSuchElemExToIllegalArgumentEx(s: String): Any = {
    try {
      Coordinates.withName(s);
    } catch {
      case _: Throwable => new IllegalArgumentException("Wrong input parameters !")
    }
  }
}