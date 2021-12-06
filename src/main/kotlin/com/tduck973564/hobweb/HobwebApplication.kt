package com.tduck973564.hobweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class HobwebApplication

fun main(args: Array<String>) {
	runApplication<HobwebApplication>(*args)
}

@Table("MESSAGES")
data class Message(@Id val id: String?, val name: String, val text: String)

interface MessageRepository : CrudRepository<Message, String> {
	@Query("select * from messages")
	fun findMessages(): List<Message>
}

@Service
class MessageService(val db: MessageRepository) {
	fun findMessages(): List<Message> = db.findMessages().takeLast(50)

	fun post(message: Message) {
		db.save(message)
	}
}


@RestController
@CrossOrigin(origins = ["http://localhost:63342"])
class MessageResource(val service: MessageService) {
	@GetMapping
	fun index(): List<Message> = service.findMessages()

	@PostMapping
	fun post(@RequestBody message: Message) = service.post(message)
}