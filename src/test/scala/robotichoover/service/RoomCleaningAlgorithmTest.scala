package robotichoover.service

import org.scalatest.{Matchers, FlatSpec}
import robotichoover.domain._

class RoomCleaningAlgorithmTest extends FlatSpec with Matchers {
  val roomDimensions = (5, 5)
  val initialHooverPosition = (1, 2)
  val initialDirtyPositions = Set((1, 0), (2, 2), (2, 3))
  
  "cleaning a room" should "have same hoover position and dirty patches when no instructions are given" in {
    val configuration = Configuration(
      roomDimensions, initialHooverPosition, initialDirtyPositions, List())
    RoomCleaningAlgorithm.clean(configuration) should be {
      RoomStatus(roomDimensions, initialHooverPosition, initialDirtyPositions)
    }
  }

  it should "have different hoover position but same dirty patches when no dirty patches found for given instructions" in {
    val configuration = Configuration(
      roomDimensions, initialHooverPosition, initialDirtyPositions, List(Instruction.S))
    RoomCleaningAlgorithm.clean(configuration) should be {
      RoomStatus(roomDimensions, (1, 1), initialDirtyPositions)
    }
  }

  it should "have different hoover position and clean some patches when dirty patches found for given instructions" in {
    val configuration = Configuration(
      roomDimensions, initialHooverPosition, initialDirtyPositions, List(Instruction.E))
    RoomCleaningAlgorithm.clean(configuration) should be {
      RoomStatus(roomDimensions, (2, 2), Set((1, 0), (2, 3)))
    }
  }

  it should "have different position and clean only one patch when second clean attempt over an originally dirty patch" in {
    val configuration = Configuration(
      roomDimensions, initialHooverPosition, initialDirtyPositions, List(Instruction.E, Instruction.W, Instruction.E))
    RoomCleaningAlgorithm.clean(configuration) should be {
      RoomStatus(roomDimensions, (2, 2), Set((1, 0), (2, 3)))
    }
  }

  it should "have same position but clean initial position when initial position is dirty" in {
    val configuration = Configuration(
      roomDimensions, (1, 0), initialDirtyPositions, List())
    RoomCleaningAlgorithm.clean(configuration) should be {
      RoomStatus(roomDimensions, (1, 0), Set((2, 2), (2, 3)))
    }
  }
}
