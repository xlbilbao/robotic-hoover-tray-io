package robotichoover

package object domain {
  /*
     Position is an alias for a tuple of ints where the first element of the tuple is the value for the 'x' axis and
     the second element is the value for the 'y' axis.
   */

  type Position = (Int, Int)
  type Vector = Position
  type Dimensions = Position
}
