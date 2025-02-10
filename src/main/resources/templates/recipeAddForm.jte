<form
        hx-post="/recipes"
        enctype="multipart/form-data"
        hx-target="#main-container"
        hx-swap="innerHtml"
        class="mb-4 flex flex-col gap-2"
>
    <div>
        <label for="title" class="block text-sm font-medium text-gray-700">Recipe Title</label>
        <input type="text" id="title" name="title" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm" required>
    </div>
    <div class="mt-4">
        <label for="file" class="block text-sm font-medium text-gray-700">Upload Image</label>
        <input type="file" id="file" name="file" accept="image/png, image/jpeg" class="mt-1 block w-full">
    </div>


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