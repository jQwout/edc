package utils

object InJavaArrayUtils {


    fun ignoreElements(array: Array<String?>, element: List<String>): String {
        return array.filterNot { element.contains(it) or it.isNullOrBlank() }
            .filterNotNull()
            .distinct()
            .joinToString("\n")
    }
}