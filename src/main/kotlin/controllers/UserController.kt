package controllers

import models.UserModel
import models.UserMemStore
import mu.KotlinLogging
import views.UserView

class UserController {

    val user = UserModel()
    val userView = UserView()
    val logger = KotlinLogging.logger {}

    fun add() {
        var aUser = UserModel()

        if(userView.addUserData(aUser)) {
            user.
        }
    }

}