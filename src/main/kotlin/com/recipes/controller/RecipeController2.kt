package com.recipes.controller

import com.recipes.model.Recipe
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import java.util.concurrent.ConcurrentHashMap
import java.util.UUID
import com.recipes.templates.Templates.toListEntry

@Controller
class RecipeController2 {

    private val recipes = ConcurrentHashMap<UUID, Recipe>()

    @GetMapping("/")
    fun home(): String {
        return "redirect:/recipes.html"
    }

    @GetMapping("/recipes")
    @ResponseBody
    fun get(): String {
        return recipes.values.joinToString("\n") { it.toListEntry() }
    }

    @PostMapping("/recipes")
    @ResponseBody
    fun create(@RequestParam("title") title: String): String {
        val recipe = Recipe(title)
        recipes[recipe.id] = recipe
        return recipe.toListEntry()
    }

    @PostMapping("/recipes/{id}/toggle")
    @ResponseBody
    fun toggle(@PathVariable id: UUID): String {
        val recipe = recipes[id] ?: throw IllegalArgumentException("Entry not found")
        recipe.completed = !recipe.completed
        return recipe.toListEntry()
    }


}