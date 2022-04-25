package com.github.ryutaro.kmmsample

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}