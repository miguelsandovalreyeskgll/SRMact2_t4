package com.srm.actividad1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProyectoController {

    // Se llama desde el navegador o Postman al hacer GET /api/proyecto.
    @GetMapping("/proyecto")
    public String mostrarProyecto() {
        return "SRMact1_t4 - Sandoval Reyes Miguel";
    }
}
