package com.recipes.recipes.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.ConcurrentHashMap
import java.util.UUID

@Controller
class TodoController {

    data class Todo(val id: UUID, var title: String, var completed: Boolean = false)

    private val todos = ConcurrentHashMap<UUID, Todo>() // A thread-safe HashMap

    @GetMapping("/")
    fun home(): String {
        return "redirect:/todo.html"
    }

    @GetMapping("/todos")
    @ResponseBody
    fun getTodos(): List<Todo> {
        return todos.values.toList()
    }

    @PostMapping("/todos")
    @ResponseBody
    fun createTodo(
        @RequestParam("title") title: String
    ): Todo {
        val todo = Todo(UUID.randomUUID(), title)
        todos[todo.id] = todo
        return todo
    }

    @PostMapping("/todos/{id}/toggle")
    @ResponseBody
    fun toggleTodo(@PathVariable id: UUID): Todo {
        val todo = todos[id] ?: throw IllegalArgumentException("TODO not found")
        todo.completed = !todo.completed
        return todo
    }

    @DeleteMapping("/todos/{id}")
    @ResponseBody
    fun deleteTodo(@PathVariable id: UUID): UUID {
        todos.remove(id) ?: throw IllegalArgumentException("TODO not found")
        return id
    }
}