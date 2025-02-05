@import com.recipes.model.Recipe

@param Recipe recipe

!{var backgroundClass = recipe.getCompleted() ? "bg-green-500" : "bg-red-500";}

<li
        class="recipe-item mt-2 p-4 text-white font-medium rounded shadow ${backgroundClass} "
        hx-post="/recipes/${recipe.getId().toString()}/toggle"
        hx-swap="outerHTML"
        hx-trigger="click"
>
    ${recipe.getTitle()}
</li>