package com.recipes.controller

import com.recipes.model.Content
import com.recipes.model.Content.Companion.fromMultipartFile
import com.recipes.model.Recipe
import gg.jte.TemplateEngine
import gg.jte.TemplateOutput
import gg.jte.output.StringOutput
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Controller
class RecipeController(private val engine: TemplateEngine) {

    private val recipes = ConcurrentHashMap<UUID, Recipe>().apply {
        (1..10).forEach {
            val recipe = Recipe("Recipe $it", Content())
            this[recipe.id] = recipe
        }
    }

    @GetMapping("/recipes")
    @ResponseBody
    fun getRecipes(): String {
        return recipes.values.joinToString("\n") { render("recipeEntry", mapOf("recipe" to it)) }
    }

    @PostMapping("/recipes")
    @ResponseBody
    fun create(@RequestParam("title") title: String, @RequestParam("file") file: MultipartFile?): String {
        val recipe = Recipe(title, fromMultipartFile(file))
        recipes[recipe.id] = recipe
        return render("recipeEntry", mapOf("recipe" to recipe))
    }

    @GetMapping("/recipes/{id}")
    @ResponseBody
    fun getRecipeSummary(@PathVariable id: UUID): String {
        val recipe = recipes[id] ?: throw IllegalArgumentException("Recipe not found")
        return render("recipeSummary", mapOf("recipe" to recipe))
    }

    private fun render(
        template: String,
        params: Map<String, Any> = mapOf(),
        output: TemplateOutput = StringOutput()
    ): String {
        engine.render("$template.kte", params, output)
        return output.toString()
    }

    @GetMapping("/recipes/add")
    @ResponseBody
    fun modal(): String {
        return render("recipeAdd")
    }
}

