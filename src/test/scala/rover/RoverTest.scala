package rover

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import rover.util.Coordinates._

import rover.model.Rover

/**
 * Test for rover.model.Rover
 * @author Mihai Anghel
 */
@RunWith(classOf[JUnitRunner])
class RoverTest extends FunSuite {

  object Rovers {
    val r1 = new Rover(1, 2, N)
    val r2 = new Rover(3, 3, E)
    val gridSize = (8, 8)
  }

  test("Rover 1 moves") {
    val resultRover = Rovers.r1.move("LMLMLMLMM")(Rovers.gridSize)
    assert(resultRover.x === 1)
    assert(resultRover.y === 3)
    assert(resultRover.c === N)
  }

  test("Rover 2 moves") {
    val resultRover = Rovers.r2.move("MMRMMRMRRM")(Rovers.gridSize)
    assert(resultRover.x === 5)
    assert(resultRover.y === 1)
    assert(resultRover.c === E)
  }

  test("Rover is not allowed to have negative coordinates") {
    val thrown = intercept[IllegalStateException] {
      new Rover(-3, 2, N)
    }
    assert(thrown.getMessage === "Rover is out of the grid at position x=-3 y=2 !")
  }

  test("Rover is not allowed to leave the grid on the right") {
    val thrown = intercept[IllegalStateException] {
      Rovers.r2.move("MMMMMM")(Rovers.gridSize)
    }
    assert(thrown.getMessage === "Rover is out of the grid at position x=9 y=3 !")
  }

  test("Rover is not allowed to leave the grid on the left") {
    val thrown = intercept[IllegalStateException] {
      Rovers.r1.move("LMMMMM")(Rovers.gridSize)
    }
    assert(thrown.getMessage === "Rover is out of the grid at position x=-1 y=2 !")
  }

  test("Unknown command passed to the rover") {
    val thrown = intercept[IllegalArgumentException] {
      Rovers.r2.move("ALRM")(Rovers.gridSize)
    }
    assert(thrown.getMessage === "Unknown command!")
  }

}