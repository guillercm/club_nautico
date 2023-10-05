package com.joyfe.daw.des.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyfe.daw.des.response.Patron;
import com.joyfe.daw.des.rest.dao.PatronDAO;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatronService {

    @Autowired
    private PatronDAO patronDAO;

    public Patron getPatronById(Long id) {
        return patronDAO.getPatronById(id);
    }

	public Patron addPatron(Patron patron) {
		return patronDAO.addPatron(patron);
	}

	public List<Patron> getAllPatrones() {
		return patronDAO.getAllPatrones();
	}

}