package ru.netology.nmedia.dto

import ru.netology.nmedia.enumeration.AttachmentType

data class Post(
        val id: Long,
        val author: String,
        val authorAvatar: String,
        val content: String,
        val published: Long,
        val sharing: Int = 0,
        val likes: Int = 0,
        var countVisability: Int = 0,
        val video: String,
        val likedByMe: Boolean,
        var attachment: Attachment? = null,
)

data class Attachment(
    val url: String,
    val description: String,
    val type: AttachmentType,
)
