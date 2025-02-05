package com.recipes.controller

import com.recipes.model.Todo
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
class TodoController {

    private val todos = ConcurrentHashMap<UUID, Todo>()

    @GetMapping("/")
    fun home(): String {
        return "redirect:/todo.html"
    }

    @GetMapping("/todos")
    @ResponseBody
    fun getTodos(): String {
        return todos.values.joinToString("\n") { it.toListEntry() }
    }

    @PostMapping("/todos")
    @ResponseBody
    fun createTodo(@RequestParam("title") title: String): String {
        val todo = Todo(title)
        todos[todo.id] = todo
        return todo.toListEntry()
    }

    @PostMapping("/todos/{id}/toggle")
    @ResponseBody
    fun toggleTodo(@PathVariable id: UUID): String {
        val todo = todos[id] ?: throw IllegalArgumentException("TODO not found")
        todo.completed = !todo.completed
        return todo.toListEntry()
    }


}