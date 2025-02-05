package com.recipes.config

import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.resolve.ResourceCodeResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JteConfiguration {
    @Bean
    fun templateEngine(): TemplateEngine {
        return TemplateEngine.create(
            ResourceCodeResolver("templates"), 
            ContentType.Plain
        )
    }
}