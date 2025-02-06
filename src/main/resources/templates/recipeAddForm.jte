<form
        hx-post="/recipes"
        hx-target="#main-container"
        hx-swap="innerHtml"
        class="mb-4 flex flex-col gap-2"
>
    <label>
        <input
                type="text"
                name="title"
                placeholder="Enter recipe title..."
                class="flex-1 border-gray-300 p-2 border rounded w-full"
                required
        />
    </label>
    <div class="flex justify-between">
        <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded shadow hover:bg-green-600">
            Submit
        </button>
        <button
                type="button"
                class="bg-gray-500 text-white px-4 py-2 rounded shadow hover:bg-gray-600"
                hx-get="/mainContainer"
                hx-target="#main-container"
                hx-swap="innerHtml"
        >
            Cancel
        </button>
    </div>
</form>