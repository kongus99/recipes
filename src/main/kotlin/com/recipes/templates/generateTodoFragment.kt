package com.recipes.templates

import com.recipes.model.Todo

object Templates {
    fun Todo.toListEntry(): String {
        val backgroundClass = if (completed) "bg-green-500" else "bg-red-500"
        return """
            <li 
                class="todo-item mt-2 p-4 text-white font-medium rounded shadow $backgroundClass"
                hx-post="/todos/${id}/toggle" 
                hx-swap="outerHTML"
                hx-trigger="click"
            >
                $title
            </li>
        """.trimIndent()
    }

}
