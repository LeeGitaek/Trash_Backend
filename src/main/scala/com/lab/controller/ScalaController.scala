package com.lab.controller

import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class ScalaController {

  @GetMapping(path = Array("/scala"))
  def demo: String = {
      "Welcome engine."
  }
}
