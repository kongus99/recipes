package com.recipes.model

import com.recipes.model.ContentType.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import java.util.UUID.randomUUID

data class Recipe(
    var title: String,
    var content: Content,
    val id: UUID = randomUUID(),
    var completed: Boolean = false,
)

data class Content(val content: String = "No content", val contentType: ContentType = TEXT) {

    companion object {
        fun fromMultipartFile(file: MultipartFile?): Content {
            return when {
                file == null || file.isEmpty -> Content()
                else -> {
                    when (val type = ContentType.fromCode(file.contentType)) {
                        JPEG, PNG, SVG -> {
                            Content(Base64.getEncoder().encodeToString(file.bytes), type)
                        }

                        TEXT, HTML, JSON -> {
                            Content(file.bytes.decodeToString(), type)
                        }

                        null -> throw IllegalArgumentException("Unsupported content type")
                    }
                }
            }
        }
    }
}

enum class ContentType(val code: String) {
    JPEG("image/jpeg"), // 
    PNG("image/png"), // 
    SVG("image/svg+xml"), // 
    TEXT("text/plain"), // 
    HTML("text/html"), // 
    JSON("application/json");

    companion object {
        fun fromCode(code: String?): ContentType? {
            return code?.let {
                entries.find { it.code == code }
            }
        }
    }
}
