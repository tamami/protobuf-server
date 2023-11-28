package com.example.protobufserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CourseController {

    @Autowired
    lateinit var courseRepo: CourseRepository

    @CrossOrigin
    @RequestMapping(value = [ "/courses/{id}" ], produces = [ "application/x-protobuf" ])
    fun customer(@PathVariable id: Int) = courseRepo.getCourse(id)

}