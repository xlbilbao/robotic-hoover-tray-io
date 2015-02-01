package robotichoover.application

import java.io.{InputStream, FileInputStream}

import robotichoover.domain.Configuration
import robotichoover.service.{ResultsService, RoomCleaningAlgorithm}

object App {
  def main(args: Array[String]) {
    try {
      val configuration: Configuration = ConfigurationLoader(configurationInputStream(args));
      val finalRoomStatus = RoomCleaningAlgorithm.clean(configuration)
      val result = ResultsService.calculate(configuration, finalRoomStatus)
      ResultsService.print(result)
    } catch {
      case e: Exception => {
        Console.err.println(e.getMessage)
        System.exit(1)
      }
    }

  }

  private def configurationInputStream(args: Array[String]): InputStream = {
    if(args.length > 0) new FileInputStream(args(0))
    else App.getClass().getResourceAsStream("/input.txt")
  }
}
