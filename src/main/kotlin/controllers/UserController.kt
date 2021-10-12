package controllers

import models.UserModel
import models.UserJSONStore
import mu.KotlinLogging
import views.UserView

class UserController {

    val mainUser = UserJSONStore()
    val userView = UserView()
    val logger = KotlinLogging.logger {}

    fun add() {
        var aUser = UserModel()

        if(userView.addUserData(aUser)) {
            mainUser.create(aUser)
        }
        else
            logger.info("User Not Added!!")
    }

    fun list() {
        userView.showUser(mainUser)
    }



}