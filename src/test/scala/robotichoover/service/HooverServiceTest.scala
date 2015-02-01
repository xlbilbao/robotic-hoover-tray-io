package robotichoover.service

import org.scalatest.{Matchers, FlatSpec}
import robotichoover.domain.{Instruction, RoomStatus}

class HooverServiceTest extends FlatSpec with Matchers  {
  "clean" should "clean the current hoover position if it's dirty" in {
    val roomStatus = RoomStatus((5, 5), (1, 2), Set((1, 2), (3, 4)))
    HooverService.clean(roomStatus) should be {
      RoomStatus((5, 5), (1, 2), Set((3, 4)))
    }
  }

  it should "do nothing if current hoover position it's not dirty" in {
    val roomStatus = RoomStatus((5, 5), (2, 2), Set((1, 2), (3, 4)))
    HooverService.clean(roomStatus) should be {
      RoomStatus((5, 5), (2, 2), Set((1, 2), (3, 4)))
    }
  }

  "move" should "go to the next position when the position is valid" in {
    val roomStatus = RoomStatus((5, 5), (4, 4), Set((1, 2), (3, 4)))
    HooverService.move(roomStatus, Instruction.S) should be {
      RoomStatus((5, 5), (4, 3), Set((1, 2), (3, 4)))
    }
  }

  "move" should "stay in the same position when the position is not valid" in {
    val roomStatus = RoomStatus((5, 5), (4, 4), Set((1, 2), (3, 4)))
    HooverService.move(roomStatus, Instruction.N) should be {
      roomStatus
    }
  }
}
