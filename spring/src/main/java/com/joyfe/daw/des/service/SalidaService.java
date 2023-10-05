package com.joyfe.daw.des.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyfe.daw.des.response.Salida;
import com.joyfe.daw.des.rest.dao.SalidaDAO;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SalidaService {

    @Autowired
    private SalidaDAO salidaDAO;

    public Salida getSalidaById(Long id) {
        return salidaDAO.getSalidaById(id);
    }

    public List<Salida> getAllSalidas() {
        return salidaDAO.getAllSalidas();
    }

    public Salida addSalida(Salida salida) {
        return salidaDAO.addSalida(salida);
    }

    public Salida updateSalida(Long id, Salida salida) {
        return salidaDAO.updateSalida(id, salida);
    }

    public Salida deleteSalida(Long id) {
        return salidaDAO.deleteSalida(id);
    }
}
