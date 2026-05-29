package dev.makethiswork.criticaltechnews.util

fun readResource(path: String): String {
    return checkNotNull(object {}.javaClass.getResource(path)) {
        "Test resource not found: $path"
    }.readText()
}
