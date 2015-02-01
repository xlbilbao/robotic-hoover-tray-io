package robotichoover.service

import robotichoover.domain.Instruction.Instruction
import robotichoover.domain._

import scala.annotation.tailrec

object RoomCleaningAlgorithm {
  def clean(configuration: Configuration): RoomStatus = clean(
      configuration.instructions,
      RoomStatus(configuration.roomDimensions, configuration.hooverPosition, configuration.dirtyPatches)
    )

  @tailrec
  private def clean(instructions: List[Instruction], roomStatus: RoomStatus): RoomStatus = {
    val statusAfterCleaning = HooverService.clean(roomStatus)
    instructions match {
      case instruction :: rest => {
        clean(rest, HooverService.move(statusAfterCleaning, instruction))
      }
      case Nil => statusAfterCleaning
    }
  }

}
