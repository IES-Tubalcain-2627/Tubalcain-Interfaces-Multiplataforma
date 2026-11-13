package net.tubalcain.interfacesmultiplataforma.inicial

import net.tubalcain.interfacesmultiplataforma.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return sayHello(platform.name)
    }
}