package com.tamastudy.tama.service.user

import com.tamastudy.tama.dto.user.CreateUserRequest
import com.tamastudy.tama.dto.user.UserDto
import com.tamastudy.tama.entity.User
import com.tamastudy.tama.entity.toUserDto
import com.tamastudy.tama.repository.user.UserRepository
import javassist.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : UserService {
    override fun save(createUserRequest: CreateUserRequest) {
        User().apply {
            this.username = createUserRequest.username
            this.email = createUserRequest.email
            this.password = bCryptPasswordEncoder.encode(createUserRequest.password)
            this.roles = "ROLE_USER"
        }.let {
            userRepository.save(it)
        }
    }

    override fun findAll(): List<UserDto> = userRepository.findAll().map(User::toUserDto)

    override fun findById(id: Long): UserDto? {
        return userRepository.findByIdOrNull(id)?.let {
            it.toUserDto()
        } ?: throw NotFoundException("$id 에 해당하는 유저가 존재하지 않습니다. ")
    }
}