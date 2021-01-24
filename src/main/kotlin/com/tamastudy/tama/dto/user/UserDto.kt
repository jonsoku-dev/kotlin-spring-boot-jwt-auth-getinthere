package com.tamastudy.tama.dto.user

data class UserDto(
        var id: Long? = null,
        var username: String? = null,
        var email: String? = null,
        var roles: String? = null// ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
)