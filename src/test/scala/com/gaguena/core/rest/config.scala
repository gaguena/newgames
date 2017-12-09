package com.gaguena.core.rest

import org.scalatra.test.specs2.MutableScalatraSpec
import com.gaguena.core.support.FixtureSupport
import com.gaguena.core.model.Game

class ServletSpecConfig extends MutableScalatraSpec  with FixtureSupport {
  addServlet(classOf[GameRest], "/game/*")

  "GET / on GameRest" should {
    "return status 200" in {
      get("/game/1") {
        var game = buildFixture[Game]("default")
        println("retorno body: " + body)
        status must_== 200
      }
    }
  }
}