# Foodie Android (Kotlin)

Based on the Foodie screen descriptions in `Projekti - Android Studio (2).pdf`.
This is a self-contained **Android Kotlin app** with native programmatically created layouts (no XML layout files or Firebase credentials required).

## Open and run
1. Open **Android Studio** → **File → Open** → select the `FoodieAndroid` directory.
2. Let Gradle sync. Install Android SDK 35 when prompted. Use JDK 17 or newer supported by Android Gradle Plugin.
3. Select an Android emulator/device and press **Run**.
4. If you already have a project and only want the Kotlin code, copy all `.kt` files into `app/src/main/java/com/example/foodie/`, updating `package` and `namespace` if necessary. Copy the Manifest activity declarations as well.

## Files
- `Food.kt`: food model and example menu data.
- `CartStore.kt`: on-device cart persisted via `SharedPreferences`.
- `Ui.kt`: reusable Kotlin UI building blocks.
- `MainActivity.kt`: home screen, best foods, search and categories.
- `ListFoodActivity.kt`: foods in a category.
- `DetailActivity.kt`: food details, quantity and add to cart.
- `CartActivity.kt`: cart, coupon, order summary and demo checkout.
- `AndroidManifest.xml`: screen registrations.

## Original PDF vs this project
The PDF provides XML layout samples, screenshots, and descriptions of Firebase data; it does not supply the original Kotlin activity/controller source, Firebase project configuration, backend, or drawable images. This implementation reproduces the described **navigation and main functionality using local sample data**. Food photos are represented by emoji. It does **not** submit real orders, charge payments, or fetch Firebase data.

Promo code: `17righthere` gives 17% off the subtotal (assumed demo discount; the PDF gives the code but not its rate). Demo delivery is $2.99 and tax is 8% (both assumptions).
