package com.vkuzub.instantgram.data.network.models.response

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Owner(var username: String? = null,
                 var account: String? = null,
                 var avatar: String? = null) : RealmModel {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Owner

        if (username != other.username) return false
        if (account != other.account) return false
        if (avatar != other.avatar) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username?.hashCode() ?: 0
        result = 31 * result + (account?.hashCode() ?: 0)
        result = 31 * result + (avatar?.hashCode() ?: 0)
        return result
    }
}