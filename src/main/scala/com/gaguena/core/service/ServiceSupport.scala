package com.gaguena.core.service

import scala.concurrent.Future
import com.gaguena.core.exceptions.AppException

trait ServiceSupport[T] {
  def failed[T](message: String): Future[T] = Future.failed(throw new AppException(message))
}