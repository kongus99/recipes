package com.recipes.controller

import com.recipes.controller.RenderTarget.Adding
import com.recipes.controller.RenderTarget.Default
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

    private val recipes = ConcurrentHashMap<UUID, Recipe>()

    @GetMapping("/")
    fun home(): String {
        return "redirect:/recipes.html"
    }

    @GetMapping("/mainContainer")
    @ResponseBody
    fun container(@RequestParam("render") render: RenderTarget?): String {
        return when (render) {
            Adding -> {
                render("recipeAddForm.jte")
            }
            else -> render("recipeAddButton.jte") + render("recipeList.jte")
        }
    }

    @GetMapping("/recipes")
    @ResponseBody
    fun getRecipes(): String {
        return recipes.values.joinToString("\n") { it.toListEntry() }
    }

    @PostMapping("/recipes")
    @ResponseBody
    fun create(@RequestParam("title") title: String, @RequestParam("file") file: MultipartFile?): String {
        val recipe = Recipe(title, fromMultipartFile(file))
        recipes[recipe.id] = recipe
        return container(Default)
    }

    @GetMapping("/recipes/{id}")
    @ResponseBody
    fun getRecipeSummary(@PathVariable id: UUID): String {
        val recipe = recipes[id] ?: throw IllegalArgumentException("Recipe not found")
        return render("recipeSummary.jte", mapOf("recipe" to recipe))
    }

    @PostMapping("/recipes/{id}/toggle")
    @ResponseBody
    fun toggle(@PathVariable id: UUID): String {
        val recipe = recipes[id] ?: throw IllegalArgumentException("Entry not found")
        recipe.completed = !recipe.completed
        return recipe.toListEntry()
    }

    fun Recipe.toListEntry(): String {
        return render("recipeEntry.jte", mapOf("recipe" to this))
    }

    private fun render(
        template: String,
        params: Map<String, Any> = mapOf(),
        output: TemplateOutput = StringOutput()
    ): String {
        engine.render(template, params, output)
        return output.toString()
    }
}

enum class RenderTarget {
    Default,
    Adding
}
