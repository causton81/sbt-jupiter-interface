package interop

import io.cucumber.scala.{EN, ScalaDsl}
import org.hamcrest.CoreMatchers.is
import org.hamcrest.MatcherAssert.assertThat


class StepDefinitions extends ScalaDsl with EN {
  Given("""an implemented step""") { () =>
    assertThat(1 + 1, is(2))
  }
}
