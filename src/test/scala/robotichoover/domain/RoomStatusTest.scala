package robotichoover.domain

import org.scalatest.{FlatSpec, Matchers}

class RoomStatusTest extends FlatSpec with Matchers {
  "(4, 4) position" should "be a valid position for a 5x5 sized room" in {
    val roomStatus = RoomStatus((5, 5), Set())
    roomStatus.isValid(4, 4) should be { true }
  }

  it should "not be a valid position for a 4x5 sized room" in {
    val roomStatus = RoomStatus((4, 5), Set())
    roomStatus.isValid(4, 4) should be { false }
  }

  it should "not be a valid position for a 5x4 sized room" in {
    val roomStatus = RoomStatus((5, 4), Set())
    roomStatus.isValid(4, 4) should be { false }
  }

  "(0, -1) position" should "be invalid position for any given room" in {
    val roomStatus = RoomStatus((1, 1), Set())
    roomStatus.isValid(0, -1) should be { false }
  }

  "(-1, 0) position" should "be invalid position for any given room" in {
    val roomStatus = RoomStatus((1, 1), Set())
    roomStatus.isValid(-1, 0) should be { false }
  }
}
