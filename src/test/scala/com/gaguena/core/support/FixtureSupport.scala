package com.gaguena.core.support

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import scala.reflect.ClassTag
import br.com.six2six.fixturefactory.Rule

trait FixtureSupport {

  FixtureFactoryLoader.loadTemplates("com.gaguena.core.templates")

  def buildFixture[T](name: String)(implicit tag: ClassTag[T]): T =
    Fixture.from(tag.runtimeClass).gimme(name).asInstanceOf[T]

  def buildFixture[T](name: String, rules: Rule)(implicit tag: ClassTag[T]): T =
    Fixture.from(tag.runtimeClass).gimme(name, rules).asInstanceOf[T]

}
