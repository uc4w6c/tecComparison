package com.example.hellokotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class SampleController {

    @GetMapping("/hello")
    fun getHello() :String {
        return "Hello World"
    }
}
