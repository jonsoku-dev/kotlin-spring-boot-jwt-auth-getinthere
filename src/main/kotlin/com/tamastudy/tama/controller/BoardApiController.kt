package com.tamastudy.tama.controller

import com.tamastudy.tama.dto.board.*
import com.tamastudy.tama.service.board.BoardService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1")
class BoardApiController(
        private val boardService: BoardService
) {
    @PostMapping("/board-category")
    fun createBoardCategory(@Valid @RequestBody createBoardCategoryRequest: CreateBoardCategoryRequest): ResponseEntity<BoardCategoryDto> {
        return boardService.createBoardCategory(createBoardCategoryRequest).let {
            ResponseEntity.status(HttpStatus.CREATED)
                    .body(it)
        }
    }

    @PostMapping("/board")
    fun createBoard(@Valid @RequestBody createBoardRequest: CreateBoardRequest): ResponseEntity<BoardDto> {
        return boardService.createBoard(1L, createBoardRequest).let {
            ResponseEntity.status(HttpStatus.CREATED)
                    .body(it)
        }
    }

    @GetMapping("/boards")
    fun getBoards(condition: BoardPagingCondition?, pageable: Pageable?): Page<BoardPagingDto> {
        return boardService.findAllWithPage(condition, pageable)
    }

    @GetMapping("/boards-complex")
    fun getBoardsComplex(condition: BoardPagingCondition?, pageable: Pageable?): Page<BoardPagingDto> {
        return boardService.findAllWithComplexPage(condition, pageable)
    }

    @PatchMapping("/board/{boardId}")
    fun updateBoard(@PathVariable boardId: Long, @Valid @RequestBody updateBoardRequest: UpdateBoardRequest): ResponseEntity<BoardDto> {
        return boardService.updateBoard(1L, boardId, updateBoardRequest).let {
            ResponseEntity.status(HttpStatus.OK)
                    .body(it)
        }
    }

    @DeleteMapping("/board/{boardId}")
    fun deleteBoard(@PathVariable boardId: Long): String {
        boardService.deleteById(1L, boardId)
        return ""
    }
}