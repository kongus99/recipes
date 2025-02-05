package com.recipes.model

import java.util.*
import java.util.UUID.randomUUID

data class Recipe(var title: String, val id: UUID = randomUUID(), var completed: Boolean = false)