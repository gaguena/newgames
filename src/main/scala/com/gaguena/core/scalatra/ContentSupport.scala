package com.gaguena.core.scalatra

import org.scalatra.{ScalatraBase, NotAcceptable}

trait ContentSupport {
  self: ScalatraBase =>

  val acceptmediaTypes: List[String]

  before() {
   /* if (acceptmediaTypes.find(request.getHeader("Accept").contains(_)).isEmpty) {
      halt(406, reason = s"Content Accept error, versions: $acceptmediaTypes")
    }*/
  }
}
