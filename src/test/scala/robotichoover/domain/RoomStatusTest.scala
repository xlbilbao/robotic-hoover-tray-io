package robotichoover.domain

import org.scalatest.{FlatSpec, Matchers}

class RoomStatusTest extends FlatSpec with Matchers {
  "(4, 4) hoover position" should "be a valid position for a 5x5 sized room" in {
    val roomStatus = RoomStatus((5, 5), (4, 3), Set())
    roomStatus.isValid(4, 4) should be { true }
  }

  it should "not be a valid position for a 4x5 sized room" in {
    val roomStatus = RoomStatus((4, 5), (4, 3), Set())
    roomStatus.isValid(4, 4) should be { false }
  }

  it should "not be a valid position for a 5x4 sized room" in {
    val roomStatus = RoomStatus((5, 4), (4, 3), Set())
    roomStatus.isValid(4, 4) should be { false }
  }

  "(0, -1) position" should "be invalid position for any given room" in {
    val roomStatus = RoomStatus((1, 1), (4, 3), Set())
    roomStatus.isValid(0, -1) should be { false }
  }

  "(-1, 0) position" should "be invalid position for any given room" in {
    val roomStatus = RoomStatus((1, 1), (4, 3), Set())
    roomStatus.isValid(-1, 0) should be { false }
  }

  "isHooverPositionDirty" should "return 'true' for a dirty position" in {
    val roomStatus = RoomStatus((5, 5), (1, 2), Set((1, 2)))
    roomStatus.isHooverPositionDirty() should be { true }
  }

  it should "return 'false' for a clean position" in {
    val roomStatus = RoomStatus((5, 5), (2, 2), Set((1, 2)))
    roomStatus.isHooverPositionDirty() should be { false }
  }

  "cleaning hoover position" should "clean the given position if the position was dirty" in {
    val roomStatus = RoomStatus((5, 5), (1, 2), Set((1, 2), (3, 4)))
    roomStatus.cleanHooverPosition() should be {
      RoomStatus((5, 5), (1, 2), Set((3, 4)))
    }
  }

  it should "not change the room status if the position was already clean" in {
    val roomStatus = RoomStatus((5, 5), (2, 2), Set((1, 2)))
    roomStatus.cleanHooverPosition() should be { roomStatus }
  }
}
