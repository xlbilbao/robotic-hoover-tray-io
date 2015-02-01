package robotichoover.domain

object Instruction extends Enumeration {
  type Instruction = Vector

  val N = ( 0,  1)
  val S = ( 0, -1)
  val E = ( 1,  0)
  val W = (-1,  0)
}

import Instruction._

case class Configuration(roomDimensions: Dimensions, hooverPosition: Position, dirtyPatches: Set[Position],
                         instructions: List[Instruction])

case class RoomStatus(topRightCorner: Position, dirtyPatches: Set[Position])

case class Result(hooverPosition: Position, cleanedPatches: Int)