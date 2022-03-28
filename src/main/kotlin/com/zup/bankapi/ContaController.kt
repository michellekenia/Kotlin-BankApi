package com.zup.bankapi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/conta")
class ContaController(private val repository: ContaRepository) {

    @PostMapping
    fun create(@RequestBody conta: Conta): Conta = repository.save(conta)

    @GetMapping
    fun getAll(): List<Conta> = repository.findAll()

    @GetMapping("/{id}")
    fun getbyId(@PathVariable id: Long): ResponseEntity<Conta> =
        repository.findById(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody conta: Conta): ResponseEntity<Conta> =
        repository.findById(id).map {
            val contaToUpdate = it.copy(
                name = conta.name,
                document = conta.document,
                phone = conta.phone
            )
            ResponseEntity.ok(repository.save(contaToUpdate))
        }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("{/id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        repository.findById(id).map {
            repository.delete(it)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
}