package robotichoover.application

import java.io.InputStream

import robotichoover.domain.Instruction.Instruction
import robotichoover.domain.Instruction.Instruction
import robotichoover.domain.{Instruction, Dimensions, Position, Configuration}

import scala.io.Source

object ConfigurationLoader {
  def apply(file: InputStream): Configuration = {
    val configurationLines = Source.fromInputStream(file).getLines()
    val roomDimensions = extractRoomDimensions(configurationLines)
    val hooverPosition = extractHooverPosition(configurationLines)
    val dirtyPatchesAndInstructions = configurationLines.toList
    val dirtyPatches = extractDirtyPatches(dirtyPatchesAndInstructions)
    if(dirtyPatches.size + 1 == dirtyPatchesAndInstructions.size) {
      val instructions = extractInstructions(dirtyPatchesAndInstructions.last)
      Configuration(roomDimensions, hooverPosition, dirtyPatches.toSet, instructions)
    } else {
      throw new Exception("Values found after hoover instructions")
    }
  }

  private val TUPLE_PATTERN = """(\d+) (\d+)""".r

  private def parseTuple(line: String): Option[(Int, Int)] =
    line match {
      case TUPLE_PATTERN(x, y) => Some((x.toInt, y.toInt))
      case _ => None
    }

  private def extractTuple(lines: Iterator[String], tupleDescription: String): (Int, Int) =
    if(lines.hasNext) parseTuple(lines.next()) match {
        case Some(tuple) => tuple
        case None => throw new Exception(s"not valid $tupleDescription entry in config file")
      }
    else throw new Exception(s"$tupleDescription not found in configuration file")

  private def extractRoomDimensions(lines: Iterator[String]): Dimensions = extractTuple(lines, "room dimensions")

  private def extractHooverPosition(lines: Iterator[String]): Position = extractTuple(lines, "hoover position")

  private def extractDirtyPatches(lines: List[String]): List[Position] =
    if(lines.isEmpty) throw new Exception("instructions not found in configuration file")
    else parseTuple(lines.head) match {
      case Some(dirtyPatch) => dirtyPatch :: extractDirtyPatches(lines.tail)
      case None => Nil
    }

  private def extractInstructions(line: String): List[Instruction] =
    line.map(instruction => Instruction.withName(instruction.toString)).toList
}
