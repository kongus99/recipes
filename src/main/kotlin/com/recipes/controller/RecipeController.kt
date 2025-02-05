package com.recipes.controller

import com.recipes.model.Recipe
import gg.jte.TemplateEngine
import gg.jte.output.StringOutput
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Controller
class RecipeController(private val engine: TemplateEngine) {

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

    fun Recipe.toListEntry(): String {
        val output = StringOutput()
        engine.render("recipeEntry.jte", mapOf("recipe" to this), output)
        return output.toString()
    }

}