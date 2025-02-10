@import com.recipes.model.ContentType
@import com.recipes.model.Recipe


@param Recipe recipe

<div class="recipe-summary p-4 border rounded">
    <h2 class="font-bold text-xl mb-4">${recipe.getTitle()}</h2>

    <div class="recipe-content mb-4">
        @if(recipe.getContent().getContentType() == ContentType.TEXT)
            <p>${recipe.getContent().getContent()}</p>
        @else
            <img src="data:${recipe.getContent().getContent()};base64,${recipe.getContent().getContent()}"
                 alt="${recipe.getTitle()}"
                 class="rounded-md shadow"/>
        @endif
    </div>
    <button class="bg-blue-500 text-white py-2 px-4 rounded"
            hx-get="/mainContainer"
            hx-target="#main-container"
            hx-swap="innerHtml"
    >
        Back
    </button>

</div>