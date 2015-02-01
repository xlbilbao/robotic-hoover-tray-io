package robotichoover.application

import org.scalatest.{Matchers, FlatSpec}
import robotichoover.domain.{Configuration, Instruction}

class ConfigurationLoaderTest extends FlatSpec with Matchers {
  "load config" should "load the expected config when file is valid" in {
    ConfigurationLoader(this.getClass.getResourceAsStream("/valid-config.txt")) should be {
      Configuration((5, 5), (1, 2), Set((1, 0), (2, 2), (2, 3)),
        List(Instruction.N, Instruction.E))
    }
  }

  it should "fail when lines exist after instructions" in {
    intercept[Exception] {
      ConfigurationLoader(this.getClass.getResourceAsStream("/data-after-instructions-config.txt"))
    }
  }

  it should "fail when no dimensions defined" in {
    intercept[Exception] {
      ConfigurationLoader(this.getClass.getResourceAsStream("/no-dimensions-config.txt"))
    }
  }

  it should "fail when no hoover dimension defined" in {
    intercept[Exception] {
      ConfigurationLoader(this.getClass.getResourceAsStream("/no-hoover-position-config.txt"))
    }
  }

  it should "load a config without dirty patches when dirty patches not defined" in {
    ConfigurationLoader(this.getClass.getResourceAsStream("/no-dirty-patches-config.txt")) should be {
      Configuration((5, 5), (1, 2), Set(),
        List(Instruction.N, Instruction.E))
    }
  }

  it should "fail when invalid dimensions defined" in {
    intercept[Exception] {
      ConfigurationLoader(this.getClass.getResourceAsStream("/invalid-dimensions-config.txt"))
    }
  }

  it should "fail when invalid hoover position defined" in {
    intercept[Exception] {
      ConfigurationLoader(this.getClass.getResourceAsStream("/invalid-hoover-position-config.txt"))
    }
  }

  it should "fail when invalid dirty patch defined" in {
    intercept[Exception] {
      ConfigurationLoader(this.getClass.getResourceAsStream("/invalid-dirty-patch-config.txt"))
    }
  }

  it should "fail when invalid instruction defined" in {
    intercept[Exception] {
      ConfigurationLoader(this.getClass.getResourceAsStream("/invalid-instruction-config.txt"))
    }
  }
}
