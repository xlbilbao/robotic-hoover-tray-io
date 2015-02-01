package robotichoover.service

import robotichoover.domain.Instruction.Instruction
import robotichoover.domain._

object HooverService {
  def clean(roomStatus: RoomStatus): RoomStatus = {
    if (roomStatus.isHooverPositionDirty()) roomStatus.cleanHooverPosition()
    else roomStatus
  }

  def move(roomStatus: RoomStatus, instruction: Instruction): RoomStatus =
    nextPosition(roomStatus.hooverPosition, toVector(instruction)) match {
      case position if roomStatus.isValid(position) => roomStatus.copy(hooverPosition = position)
      case _ => roomStatus
    }

  private def nextPosition(current: Position, instructionVector: Vector): Position =
    (current._1 + instructionVector._1, current._2 + instructionVector._2)

  private def toVector(instruction: Instruction): Vector = instruction match {
    case Instruction.N => ( 0,  1)
    case Instruction.S => ( 0, -1)
    case Instruction.E => ( 1,  0)
    case Instruction.W => (-1,  0)
  }
}
