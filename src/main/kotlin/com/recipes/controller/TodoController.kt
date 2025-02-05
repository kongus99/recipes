package com.recipes.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import java.util.concurrent.ConcurrentHashMap
import java.util.UUID

@Controller
class TodoController {

    data class Todo(val id: UUID, var title: String, var completed: Boolean = false)

    private val todos = ConcurrentHashMap<UUID, Todo>()

    @GetMapping("/")
    fun home(): String {
        return "redirect:/todo.html"
    }

    @GetMapping("/todos")
    @ResponseBody
    fun getTodos(): String {
        return todos.values.joinToString("\n") { generateTodoFragment(it) }
    }

    @PostMapping("/todos")
    @ResponseBody
    fun createTodo(@RequestParam("title") title: String): String {
        val todo = Todo(UUID.randomUUID(), title)
        todos[todo.id] = todo
        return generateTodoFragment(todo)
    }

    @PostMapping("/todos/{id}/toggle")
    @ResponseBody
    fun toggleTodo(@PathVariable id: UUID): String {
        val todo = todos[id] ?: throw IllegalArgumentException("TODO not found")
        todo.completed = !todo.completed
        return generateTodoFragment(todo)
    }

    private fun generateTodoFragment(todo: Todo): String {
        val backgroundClass = if (todo.completed) "bg-green-500" else "bg-red-500"
        return """
            <li 
                class="todo-item mt-2 p-4 text-white font-medium rounded shadow $backgroundClass"
                hx-post="/todos/${todo.id}/toggle" 
                hx-swap="outerHTML"
                hx-trigger="click"
            >
                ${todo.title}
            </li>
        """.trimIndent()
    }
}