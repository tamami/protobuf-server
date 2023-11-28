package com.example.protobufserver.repo

import com.example.protobufserver.proto.aikibo.Training.Course
import com.google.protobuf.Int32Value
import org.slf4j.LoggerFactory
import java.util.*

class CourseRepo(
    var courses: List<Course>
) {
    val log = LoggerFactory.getLogger(CourseRepo::class.java)

    fun findById(id: Int?): Optional<Course> {
        log.info("masuk ke findById()")
        return courses.stream().filter {
            it.id == id
        }.findFirst()
    }

}