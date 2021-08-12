package com.diary.demo.notes.controllers;

import com.diary.demo.notes.models.PersonalNote;
import com.diary.demo.notes.models.PersonalNoteListFilter;
import com.diary.demo.notes.models.PersonalNoteModel;
import com.diary.demo.notes.services.PersonalNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/personalNote")
@Slf4j
@RequiredArgsConstructor
public class PersonalNoteApi {

    @Autowired
    PersonalNoteService personalNoteService;

    @GetMapping
    public ResponseEntity<List<PersonalNote>> findAll() {
        return ResponseEntity.ok(personalNoteService.findAllByUser());
    }

    @PostMapping
    public ResponseEntity<PersonalNoteModel> create(@RequestBody PersonalNoteModel personalNoteModel) {
        return ResponseEntity.ok(personalNoteService.save(personalNoteModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalNoteModel> update(@PathVariable Long id, @Valid @RequestBody PersonalNoteModel personalNoteModel) {
        if (!personalNoteService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(personalNoteService.update(personalNoteModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!personalNoteService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        personalNoteService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/getPersonalNoteList")
    public ResponseEntity<List<PersonalNoteModel>> getPersonalNoteFilterList(@RequestBody PersonalNoteListFilter personalNoteListFilter) {
        return ResponseEntity.ok(personalNoteService.getPersonalNoteFilterList(personalNoteListFilter));
    }
}
