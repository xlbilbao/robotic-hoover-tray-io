package robotichoover.domain

object Instruction extends Enumeration {
  type Instruction = Value
  val N, S, E, W = Value
}

import Instruction._

case class Configuration(roomDimensions: Dimensions, hooverPosition: Position, dirtyPatches: Set[Position],
                         instructions: List[Instruction])

case class RoomStatus(dimensions: Dimensions, hooverPosition: Position, dirtyPatches: Set[Position]) {
  def isValid(position: Position): Boolean =
    position._1 >= 0 && position._1 < dimensions._1 &&
      position._2 >= 0 && position._2 < dimensions._2

  def isHooverPositionDirty(): Boolean = dirtyPatches contains hooverPosition

  def cleanHooverPosition(): RoomStatus =
    this.copy(dirtyPatches = this.dirtyPatches - hooverPosition)
}

case class Result(hooverPosition: Position, cleanedPatches: Int)