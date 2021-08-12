package com.diary.demo.notes.services;

import com.diary.demo.authentication.service.UserPrinciple;
import com.diary.demo.notes.models.PersonalNote;
import com.diary.demo.notes.models.PersonalNoteListFilter;
import com.diary.demo.notes.models.PersonalNoteModel;
import com.diary.demo.notes.repository.PersonalNoteRepository;
import com.diary.demo.util.DateUtils;
import com.diary.demo.util.UserUtil;
import com.diary.demo.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalNoteService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PersonalNoteRepository personalNoteRepository;

    // Get user wise all personal note list
    public List<PersonalNote> findAllByUser() {
        UserPrinciple userPrinciple = UserUtil.getUser();
        return personalNoteRepository.findAllByUserId(userPrinciple.getId());
    }

    // Get personal note info by personal note id
    public Optional<PersonalNote> findById(Long personalNoteId){
        return personalNoteRepository.findById(personalNoteId);
    }

    // Add personal note info by personal note details info
    public PersonalNoteModel save(PersonalNoteModel personalNoteModel) {
        UserPrinciple userPrinciple = UserUtil.getUser();

        personalNoteModel.setUserId(userPrinciple.getId());
        personalNoteModel.setDateCreated(DateUtils.getCurrentDateTime());

        PersonalNote personalNote = Util.toEntity(personalNoteModel, PersonalNote.class);
        personalNoteRepository.saveAndFlush(personalNote);

        return personalNoteModel;
    }

    // Update personal note info by personal note details info
    public PersonalNoteModel update(PersonalNoteModel personalNoteModel) {
        UserPrinciple userPrinciple = UserUtil.getUser();

        PersonalNote existingPersonalNote = personalNoteRepository.getById(personalNoteModel.getId());

        personalNoteModel.setDateCreated(existingPersonalNote.getDateCreated());
        personalNoteModel.setUserId(userPrinciple.getId());
        personalNoteModel.setLastUpdated(DateUtils.getCurrentDateTime());

        PersonalNote personalNote = Util.toEntity(personalNoteModel, PersonalNote.class);
        personalNoteRepository.saveAndFlush(personalNote);

        return personalNoteModel;
    }

    // Delete personal note info by using personal note id
    public void deleteById(Long id) {
        personalNoteRepository.deleteById(id);
    }

    public List<PersonalNoteModel> getPersonalNoteFilterList(PersonalNoteListFilter personalNoteListFilter) {
        UserPrinciple userPrinciple = UserUtil.getUser();

        String queryStr = "SELECT pn.id, pn.category_id 'categoryId', ct.name 'categoryName', pn.title, pn.content, ";
        queryStr += " DATE_FORMAT(pn.last_updated, '%d-%m-%Y %H:%i:%S %p') AS 'lastUpdatedAsString' ";
        queryStr += " FROM personal_note pn ";
        queryStr += " INNER JOIN category ct ON (pn.category_id = ct.id) ";
        queryStr += " WHERE pn.user_id = " + userPrinciple.getId() ;
        // queryStr += "   AND ct.name LIKE '%" + personalNoteListFilter.getScCategoryName() + "%'";
        queryStr += "   AND pn.title LIKE '%" + personalNoteListFilter.getScTitle() + "%'";
        queryStr += "   AND pn.content LIKE '%" + personalNoteListFilter.getScContent() + "%'";

        if(personalNoteListFilter.getScCategoryId() > 0) {
            queryStr += "   AND pn.category_id = " + personalNoteListFilter.getScCategoryId();
        }
        if(personalNoteListFilter.getScNoteUpdatedDate() != null && personalNoteListFilter.getScNoteUpdatedDate() != "") {
            queryStr += " AND date(pn.last_updated) = '" + personalNoteListFilter.getScNoteUpdatedDate() + "'";
        }

        return jdbcTemplate.query(queryStr, new BeanPropertyRowMapper(PersonalNoteModel.class));
    }
}
