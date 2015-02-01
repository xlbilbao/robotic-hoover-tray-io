package robotichoover.service

import org.scalatest.{Matchers, FlatSpec}
import robotichoover.domain.{Result, Instruction, RoomStatus, Configuration}

class ResultsServiceTest extends FlatSpec with Matchers {
  "calculate" should "return the final position for the hoover and the number of cleaned patches" in {
    val configuration = Configuration((5, 5), (1, 2), Set((1,2), (2, 2)), List(Instruction.S))
    val roomStatus = RoomStatus((5, 5), (0, 2), Set((2, 2)))

    ResultsService.calculate(configuration, roomStatus) should be {
      Result((0, 2), 1)
    }
  }
}
