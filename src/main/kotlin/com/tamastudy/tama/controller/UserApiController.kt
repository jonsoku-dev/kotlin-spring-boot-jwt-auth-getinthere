package com.tamastudy.tama.controller

import com.tamastudy.tama.dto.user.CreateUserRequest
import com.tamastudy.tama.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1"])
class UserApiController(
        private val userService: UserService,
) {
    @PostMapping(value = ["/join"])
    fun join(@Valid @RequestBody createUserRequest: CreateUserRequest): ResponseEntity<String> {
        userService.save(createUserRequest)
        return ResponseEntity<String>("ok", HttpStatus.CREATED)
    }
}