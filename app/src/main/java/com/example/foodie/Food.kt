package com.example.foodie

data class Food(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val minutes: Int,
    val rating: Double,
    val emoji: String,
    val description: String
)

object FoodRepository {
    val foods = listOf(
        Food(1, "Margherita", "Pizza", 10.99, 15, 4.8, "🍕", "Classic pizza with tomato, fresh mozzarella and basil."),
        Food(2, "Pepperoni Lover", "Pizza", 12.99, 18, 4.7, "🍕", "Crispy pizza topped with pepperoni and melted cheese."),
        Food(3, "Veggie Supreme", "Pizza", 11.99, 20, 4.6, "🍕", "Colorful vegetables, tomato sauce and mozzarella."),
        Food(4, "BBQ Chicken Delight", "Pizza", 13.99, 20, 4.9, "🍕", "Chicken, barbecue sauce, peppers and onions."),
        Food(5, "Classic Cheeseburger", "Burger", 11.49, 15, 4.8, "🍔", "Juicy beef patty, cheddar, lettuce and house sauce."),
        Food(6, "Double Burger", "Burger", 14.99, 20, 4.9, "🍔", "Two grilled patties with cheese, pickles and onions."),
        Food(7, "Crispy Chicken", "Chicken", 10.99, 18, 4.7, "🍗", "Golden crispy chicken served with homemade dip."),
        Food(8, "Grilled Chicken", "Chicken", 12.49, 22, 4.6, "🍗", "Tender marinated chicken grilled to perfection."),
        Food(9, "Pasta Alfredo", "Pasta", 12.99, 18, 4.7, "🍝", "Creamy parmesan sauce and freshly cooked pasta."),
        Food(10, "Spaghetti Bolognese", "Pasta", 13.49, 25, 4.8, "🍝", "Slow-cooked beef and tomato sauce over spaghetti."),
        Food(11, "Caesar Salad", "Salad", 9.99, 10, 4.5, "🥗", "Crisp lettuce, croutons and parmesan dressing."),
        Food(12, "Fresh Garden Salad", "Salad", 8.99, 10, 4.5, "🥗", "Seasonal vegetables and light lemon dressing."),
        Food(13, "California Sushi", "Sushi", 14.99, 25, 4.8, "🍣", "Fresh California rolls with avocado and crab."),
        Food(14, "Salmon Sushi", "Sushi", 16.99, 25, 4.9, "🍣", "Fresh salmon nigiri and maki selection."),
        Food(15, "Chocolate Cake", "Dessert", 6.99, 5, 4.9, "🍰", "Rich chocolate cake with soft ganache."),
        Food(16, "Vanilla Ice Cream", "Dessert", 4.99, 5, 4.7, "🍨", "Creamy vanilla ice cream with sweet toppings."),
        Food(17, "Iced Coffee", "Drink", 4.49, 5, 4.6, "🥤", "Freshly brewed coffee chilled with milk."),
        Food(18, "Fruit Smoothie", "Drink", 5.49, 5, 4.7, "🥤", "Blended fresh fruits and yogurt.")
    )
    val categories: List<String> = foods.map { it.category }.distinct()
    fun find(id: Int): Food? = foods.find { it.id == id }
}
