package com.tamastudy.tama.entity

import com.tamastudy.tama.dto.board.BoardCategoryDto
import com.tamastudy.tama.dto.user.UserDto
import javax.persistence.*

@Entity
data class BoardCategory(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "category_id")
        var id: Long? = null,
        var name: String? = null,

        @OneToMany(mappedBy = "category")
        var boards: List<Board>? = null
)

fun BoardCategory?.toBoardCategoryDto(): BoardCategoryDto {
    return BoardCategoryDto(
            id = this?.id,
            name = this?.name
    )
}