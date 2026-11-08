package com.srm.actividad1.controller;

import com.srm.actividad1.dto.MateriaDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MateriaPostController {

    // Se llama desde Postman o Bruno con POST /api/materias.
    // @RequestBody convierte el JSON recibido en un objeto MateriaDTO.
    // Devuelve el mismo objeto como JSON; no se guarda en base de datos.
    @PostMapping("/materias")
    public MateriaDTO recibirMateria(@RequestBody MateriaDTO materia) {
        return materia;
    }
}
