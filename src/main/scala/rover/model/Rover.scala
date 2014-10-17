package rover.model
import rover.util.Coordinates._

/**
 * Model class for rover
 * @author Mihai Anghel
 */
class Rover(val x: Int, val y: Int, val c: Coordinates) {

  if (x < 0) {
    throw new IllegalStateException("Rover is out of the grid at position x=%d y=%d !".format(x, y))
  }
  
  if (y < 0) {
    throw new IllegalStateException("Rover is out of the grid at position x=%d y=%d !".format(x, y))
  }
  
  require((c == N) || (c == S) || (c == W) || (c == E))

  /**
   * Moves the rover according to the commands parameter
   */
  def move(commands: String)(implicit sizeOfGrid: (Int, Int)): Rover = {

    def movement(listOfCmds: List[Char], r: Rover)(implicit sizeOfGrid: (Int, Int)): Rover = listOfCmds match {
      case Nil => r
      case head :: tail => head match {
        case 'L' => r.c match {
          case N => movement(tail, new Rover(r.x, r.y, W))
          case W => movement(tail, new Rover(r.x, r.y, S))
          case S => movement(tail, new Rover(r.x, r.y, E))
          case E => movement(tail, new Rover(r.x, r.y, N))
          case _ => throw new IllegalArgumentException()
        }
        case 'R' => r.c match {
          case N => movement(tail, new Rover(r.x, r.y, E))
          case E => movement(tail, new Rover(r.x, r.y, S))
          case S => movement(tail, new Rover(r.x, r.y, W))
          case W => movement(tail, new Rover(r.x, r.y, N))
          case _ => throw new IllegalArgumentException()
        }
        case 'M' => r.c match {
          case N => movement(tail, Rover.checkIfRoverIsOutOfBounds(new Rover(r.x, r.y + 1, r.c)))
          case E => movement(tail, Rover.checkIfRoverIsOutOfBounds(new Rover(r.x + 1, r.y, r.c)))
          case S => movement(tail, Rover.checkIfRoverIsOutOfBounds(new Rover(r.x, r.y - 1, r.c)))
          case W => movement(tail, Rover.checkIfRoverIsOutOfBounds(new Rover(r.x - 1, r.y, r.c)))
          case _ => throw new IllegalArgumentException()
        }
        case _ => throw new IllegalArgumentException("Unknown command!")
      }
    }

    movement(commands.toList, this)
  }

  override def toString() = "%d %d %s".format(x, y, c)
}

object Rover {

  /**
   * Checks if the rover is out of bounds according to the sizeOfGrid parameter
   */
  def checkIfRoverIsOutOfBounds(r: Rover)(implicit sizeOfGrid: (Int, Int)): Rover =
    if (r.x > sizeOfGrid._1 || r.y > sizeOfGrid._2) throw new IllegalStateException("Rover is out of the grid at position x=%d y=%d !".format(r.x, r.y)) else r
}