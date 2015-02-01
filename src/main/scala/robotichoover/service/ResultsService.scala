package robotichoover.service

import robotichoover.domain.{Result, RoomStatus, Configuration}

object ResultsService {
  def calculate(configuration: Configuration, roomStatus: RoomStatus): Result =
    Result(roomStatus.hooverPosition, configuration.dirtyPatches.size - roomStatus.dirtyPatches.size)

  def print(result: Result) {
    Console.println(result.hooverPosition._1 + " " + result.hooverPosition._2)
    Console.println(result.cleanedPatches)
  }
}
