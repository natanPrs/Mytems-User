package com.mtuser.controllers

import com.mtuser.domain.inventory.ItemModel
import com.mtuser.dtos.UserDto
import com.mtuser.domain.users.UserModel
import com.mtuser.dtos.InventoryResponseDto
import com.mtuser.dtos.MoneyToWalletDto
import com.mtuser.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<UserModel> {
        println(userDto)
        val newUser = userService.createUser(userDto)
        return ResponseEntity(newUser, HttpStatus.CREATED)
    }

    @DeleteMapping("/{email}")
    fun deleteUser(@PathVariable email: String): ResponseEntity<String> {
        userService.deleteUser(email)
        return ResponseEntity("User has been deleted", HttpStatus.OK)
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserModel>> { return ResponseEntity(userService.getAllUsers(), HttpStatus.OK)
    }

    @GetMapping("/{email}")
    fun getUserInventory(@PathVariable email: String): ResponseEntity<List<InventoryResponseDto>> {
        val inventory = userService.getInventory(email)
        return ResponseEntity.ok(inventory)
    }

    @PostMapping("/money")
    fun addMoneyToWallet(@RequestBody moneyToWalletDto: MoneyToWalletDto): ResponseEntity<UserModel> {
        val user = userService.addMoneyToWallet(moneyToWalletDto)
        return ResponseEntity(user, HttpStatus.OK)
    }
}