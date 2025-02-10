@import com.recipes.model.Recipe

@param Recipe recipe

<div class="recipe-entry p-4 border rounded mb-2 cursor-pointer"
     hx-get="/recipes/${recipe.getId().toString()}" hx-trigger="click" hx-target="#main-container" hx-swap="innerHTML">
    <h2 class="font-bold text-lg">${recipe.getTitle()}</h2>
</div>