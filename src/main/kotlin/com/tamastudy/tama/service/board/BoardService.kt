package com.tamastudy.tama.service.board

import com.tamastudy.tama.dto.board.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardService {
    fun createBoardCategory(createBoardCategoryRequest: CreateBoardCategoryRequest): BoardCategoryDto
    fun createBoard(userId: Long, createBoardRequest: CreateBoardRequest): BoardDto
    fun findAllWithPage(condition: BoardPagingCondition?, pageable: Pageable?): Page<BoardPagingDto>
    fun findAllWithComplexPage(condition: BoardPagingCondition?, pageable: Pageable?): Page<BoardPagingDto>
    fun findById(id: Long): BoardDto?
    fun updateBoard(userId: Long, boardId: Long, updateBoardRequest: UpdateBoardRequest): BoardDto
    fun deleteById(userId: Long, boardId: Long)
}