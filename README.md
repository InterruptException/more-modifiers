# more-modifiers
Some modifiers for Jetpack Compose

[![](https://jitpack.io/v/InterruptException/more-modifiers.svg)](https://jitpack.io/#InterruptException/more-modifiers)

You can use the following code to add edge blur to the Column component:

For the verticalScroll() modifier, please use fadingEdgeVertical() :
```kotlin
val scrollState = rememberScrollState()

Column(
    Modifier
        .verticalScroll(scrollState)
        .fadingEdgeVertical(scrollState)
) {
    for (i in 0..100) {
        Text("item $i")
    }
}
```

For the horizontalScroll() modifier, please use fadingEdgeHorizontal() :
```kotlin
val scrollState = rememberScrollState()

Row(
    Modifier
        .horizontalScroll(scrollState)
        .fadingEdgeHorizontal(scrollState)
) {
    for (i in 0..100) {
        Text("item $i")
    }
}
```


By default, only the bottom or right edge gradient effect is displayed. 
If you need the top or left edge effect, you can use the overloaded versions of the above function.
