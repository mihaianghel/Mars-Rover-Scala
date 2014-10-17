package util

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import rover.util.Validator._
import rover.util.Coordinates

/**
 * Test for rover.util.Validator
 * @author Mihai Anghel
 */
@RunWith(classOf[JUnitRunner])
class ValidatorTest extends FunSuite {

  /**
   * Tests to validate grid coordinates
   */
  test("Correct grid coordinates") {
    val gridCoordinates = validateGridCoordinates(" 5 5")
    assert(gridCoordinates === (5, 5))
  }

  test("Empty grid coordinates") {
    val thrown = intercept[IllegalArgumentException] {
      validateGridCoordinates(" ")
    }
    assert(thrown.getMessage === "No grid coordinates !")
  }

  test("Wrong grid coordinates: length") {
    val thrown = intercept[IllegalArgumentException] {
      validateGridCoordinates("A 6")
    }
    assert(thrown.getMessage === "Wrong input parameters !")
  }

  test("Wrong grid coordinates: width") {
    val thrown = intercept[IllegalArgumentException] {
      validateGridCoordinates("5 B")
    }
    assert(thrown.getMessage === "Wrong input parameters !")
  }

  test("Wrong grid coordinates: too many values") {
    val thrown = intercept[IllegalArgumentException] {
      validateGridCoordinates("5 6 7")
    }
    assert(thrown.getMessage === "Wrong input parameters !")
  }

  /**
   * Tests to validate rover coordinates
   */
  test("Correct rover coordinates") {
    val roverCoordinates = validateRoverCoordinates("1 3 N")
    assert(roverCoordinates === (1, 3, Coordinates.N))
  }

  test("Empty rover coordinates") {
    val thrown = intercept[IllegalArgumentException] {
      validateRoverCoordinates(" ")
    }
    assert(thrown.getMessage === "No rover coordinates !")
  }

  test("Wrong rover coordinates: position x") {
    val thrown = intercept[IllegalArgumentException] {
      validateRoverCoordinates(" d 4 N")
    }
    assert(thrown.getMessage === "Wrong input parameters !")
  }

  test("Wrong rover coordinates: position y") {
    val thrown = intercept[IllegalArgumentException] {
      validateRoverCoordinates(" 3 d N")
    }
    assert(thrown.getMessage === "Wrong input parameters !")
  }

  test("Wrong rover coordinates: orientation") {
    val thrown = intercept[IllegalArgumentException] {
      validateRoverCoordinates(" 3 4 U")
    }
    assert(thrown.getMessage === "Wrong input parameters !")
  }

  test("Wrong rover coordinates: too many values") {
    val thrown = intercept[IllegalArgumentException] {
      validateRoverCoordinates(" 3 4 U 8")
    }
    assert(thrown.getMessage === "Wrong input parameters !")
  }
}