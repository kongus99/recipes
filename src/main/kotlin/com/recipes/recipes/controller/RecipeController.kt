package com.recipes.recipes.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.ResponseBody
import java.util.UUID

@Controller
class RecipeController {

    val recipes = mutableMapOf<UUID, Recipe>()

    data class Recipe(val id: UUID, val name: String, val content: String) {
        constructor(name: String, content: String) : this(UUID.randomUUID(), name, content)
    }

    @GetMapping("/")
    fun redirectToRecipes(): String {
        return "redirect:/recipes"
    }

    @GetMapping("/recipes")
    @ResponseBody
    fun getAll(): List<Recipe> {
        return recipes.values.toList()
    }

    @PostMapping("/recipes")
    @ResponseBody
    fun uploadRecipe(@RequestParam("file") file: MultipartFile): Recipe {
        val content = file.inputStream.bufferedReader().use { it.readText() }
        val name = file.originalFilename?.substringBeforeLast(".") ?: "Unknown Recipe"
        val recipe = Recipe(name, content)
        recipes[recipe.id] = recipe
        return recipe
    }

}