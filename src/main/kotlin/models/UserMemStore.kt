package models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class UserMemStore {
    val user = UserModel()

//    override create(users: UserModel) {
//        var e = UserModel("user")
//    }
//
//
//    internal fun logAll() {
//        user.forEach { logger.info("${it}") }
//    }
}