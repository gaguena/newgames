package com.gaguena.core.scalatra

import scala.concurrent.ExecutionContext

import org.json4s.{ DefaultFormats, Formats }
import org.scalatra.{ FutureSupport, ScalatraServlet, UrlGeneratorSupport }
import org.scalatra.json.JacksonJsonSupport

import com.gaguena.core.utils.LocalDateTimeSerializer

import akka.actor.ActorSystem
import scala.reflect.runtime.universe._
import scala.reflect.ClassTag
import org.json4s.Writer
import org.scalatra.BadRequest
import org.scalatra.NotFound
import org.slf4j.LoggerFactory

trait ScalatraSupport extends ScalatraServlet
    with ContentSupport
    with FutureSupport
    with JacksonJsonSupport
    with UrlGeneratorSupport {

  protected implicit def executor: ExecutionContext = ActorSystem().dispatcher

  protected implicit lazy val jsonFormats: Formats = DefaultFormats.withBigDecimal + LocalDateTimeSerializer

  private[this] val logger = LoggerFactory.getLogger(getClass)
    
  before() {
    contentType = formats("json")
  }

  private def resultMessage(message: String){
    ResponseMessage(message)
  }

  def badRequest(message: String) = {
    logger.info(s"BadRequest: $message" )
    BadRequest(resultMessage(message))
  }
  
   def notFound(message: String) = {
    logger.info(s"NotFound: $message" )
    NotFound(resultMessage(message))
  }
}
