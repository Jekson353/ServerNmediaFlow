package ru.netology.nmedia.entity

import ru.netology.nmedia.dto.Attachment
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.enumeration.AttachmentType
import javax.persistence.*

@Entity
data class PostEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
        var author: String,
        var authorAvatar: String,
        @Column(columnDefinition = "TEXT")
        var content: String,
        var published: Long,
        val sharing: Int = 0,
        var likes: Int = 0,
        var countVisability: Int = 0,
        val video: String,
        var likedByMe: Boolean,
        @Embedded
        var attachment: AttachmentEmbeddable?,
) {
    fun toDto() = Post(id, author, authorAvatar, content, published, sharing, likes, countVisability, video, likedByMe, attachment?.toDto())

    companion object {
        fun fromDto(dto: Post) = PostEntity(
                dto.id,
                dto.author,
                dto.authorAvatar,
                dto.content,
                dto.published,
                dto.sharing,
                dto.likes,
                dto.countVisability,
                dto.video,
                dto.likedByMe,
                AttachmentEmbeddable.fromDto(dto.attachment),
        )
    }
}

@Embeddable
data class AttachmentEmbeddable(
    var url: String,
    @Column(columnDefinition = "TEXT")
    var description: String,
    @Enumerated(EnumType.STRING)
    var type: AttachmentType,
) {
    fun toDto() = Attachment(url, description, type)

    companion object {
        fun fromDto(dto: Attachment?) = dto?.let {
            AttachmentEmbeddable(it.url, it.description, it.type)
        }
    }
}
