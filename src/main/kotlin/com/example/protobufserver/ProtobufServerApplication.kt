package com.example.protobufserver

import com.example.protobufserver.proto.aikibo.Training
import com.example.protobufserver.proto.aikibo.Training.Course
import com.example.protobufserver.proto.aikibo.Training.Student
import com.example.protobufserver.proto.aikibo.Training.Student.PhoneNumber
import com.example.protobufserver.proto.aikibo.Training.Student.PhoneType
import com.example.protobufserver.repo.CourseRepo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*
import kotlin.collections.HashMap

@SpringBootApplication
class ProtobufServerApplication {
	@Bean
	fun protobufHttpMessageConverter() = ProtobufHttpMessageConverter()

	@Bean
	fun createTestCourses(): CourseRepository {
		var courses = HashMap<Int, Training.Course>()
		var course1 = Course.newBuilder()
			.setId(1)
			.setCourseName("REST with Spring")
			.addAllStudent(createTestStudents())
			.build()
		var course2 = Course.newBuilder()
			.setId(2)
			.setCourseName("Learn Spring Security")
			.addAllStudent(listOf())
			.build()
		courses[course1.getId()] = course1
		courses[course2.getId()] = course2
		return CourseRepository(courses)
	}

	@Bean
	fun createListCoursesGrpc(): CourseRepo {
		var courses = mutableListOf<Course>()
		var course1 = Course.newBuilder()
			.setId(1)
			.setCourseName("REST with Spring")
			.addAllStudent(createTestStudents())
			.build()
		var course2 = Course.newBuilder()
			.setId(2)
			.setCourseName("Learn Spring Security")
			.addAllStudent(listOf())
			.build()
		courses.add(course1)
		courses.add(course2)
		return CourseRepo(courses.toList())
	}
}

fun main(args: Array<String>) {
	runApplication<ProtobufServerApplication>(*args)
}



fun createTestStudents(): List<Student> {
	var phone1 = createPhone("123456", PhoneType.MOBILE)
	var student1 = createStudent(1, "John", "Doe",
		"john.doe@mail.com", listOf(phone1))

	var phone2 = createPhone("234567", PhoneType.LANDLINE)
	var student2 = createStudent(2, "Richard", "Roe",
		"richard.roe@mail.com", listOf(phone2))

	var phone3_1 = createPhone("345678", PhoneType.MOBILE)
	var phone3_2 = createPhone("456789", PhoneType.LANDLINE)
	var student3 = createStudent(3, "Jane", "Doe",
		"jane.doe@mail.com", listOf(phone3_1, phone3_2))

	return listOf(student1, student2, student3)
}

fun createPhone(number: String, type: PhoneType): PhoneNumber = PhoneNumber.newBuilder().setNumber(number).setType(type).build()

fun createStudent(id: Int, firstName: String, lastName: String, email: String, phones: List<PhoneNumber>): Student =
	Student.newBuilder().setId(id).setFirstName(firstName).setLastName(lastName).setEmail(email)
		.addAllPhone(phones).build()
