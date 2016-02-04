package com.mazerunner.controller

import play.api.mvc.{Action, Controller}

class WelcomeController extends Controller {
  def index = Action {
    Ok("Hello")
  }
  def login = ???
  def logout = ???
  def register = ???
}
