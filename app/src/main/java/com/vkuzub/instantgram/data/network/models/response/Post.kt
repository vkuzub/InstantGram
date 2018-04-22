package com.vkuzub.instantgram.data.network.models.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Post(
        @PrimaryKey
        var uuid: String? = null,
        var owner: Owner? = null,
        var images: RealmList<String>? = null,
        @JsonProperty("post_description")
        var postDescription: String? = null,
        @JsonProperty("liked_by_users")
        var likedByUsers: RealmList<String>? = null,
        @JsonProperty("created_at")
        var createdAt: Long? = 0
) : RealmModel {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (uuid != other.uuid) return false
        if (owner != other.owner) return false
        if (images != other.images) return false
        if (postDescription != other.postDescription) return false
        if (likedByUsers != other.likedByUsers) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid?.hashCode() ?: 0
        result = 31 * result + (owner?.hashCode() ?: 0)
        result = 31 * result + (images?.hashCode() ?: 0)
        result = 31 * result + (postDescription?.hashCode() ?: 0)
        result = 31 * result + (likedByUsers?.hashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        return result
    }
}
