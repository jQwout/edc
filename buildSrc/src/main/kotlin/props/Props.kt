package props

import java.io.File
import java.io.FileInputStream
import java.util.*

private const val VERSION_CODE = "VERSION_CODE"
private const val VERSION_CODE_DEFAULT = "1"

internal fun Properties.getVersionCodeInt() = get(VERSION_CODE).toString().toInt()

internal fun loadVersionProperties(path: String): Properties {
    val propsFile = File(path)
    val props = Properties()

    if (propsFile.canRead()) {
        props.load(FileInputStream(propsFile))
    } else {
        props[VERSION_CODE] = VERSION_CODE_DEFAULT
    }

    return props
}

internal fun incrementVersionCode(path: String){
    val props = loadVersionProperties(path)
    val code = props.getVersionCodeInt() + 1
    props[VERSION_CODE] = code.toString()
    props.store(File(path).writer(), null)
}