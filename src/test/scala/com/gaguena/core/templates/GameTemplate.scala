package com.gaguena.core.templates

import br.com.six2six.fixturefactory.loader.TemplateLoader
import br.com.six2six.fixturefactory.Fixture
import com.gaguena.core.model.Game
import br.com.six2six.fixturefactory.Rule
import java.time.LocalDateTime

class GameTemplate extends TemplateLoader {

  override def load() {
    Fixture.of(classOf[Game])
    .addTemplate("default", new Rule(){
      add("id", Some(random(Long, range(1L, 200L))))
      add("name", "Game Template")
      add("description", "Game Tamplate descricao")
      add("registerDH", LocalDateTime.now)
    });
  }
}