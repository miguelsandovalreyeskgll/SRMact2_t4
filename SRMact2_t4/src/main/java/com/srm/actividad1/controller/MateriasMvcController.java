package com.srm.actividad1.controller;

import java.util.ArrayList;
import java.util.List;
import com.srm.actividad1.dto.MateriaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/materias")
public class MateriasMvcController {

    // @Value lee la propiedad definida en application.properties.
    @Value("${proyecto.nombre}")
    private String nombreProyecto;

    // Lista simulada, no utiliza base de datos.
    private final List<MateriaDTO> materias = new ArrayList<>(List.of(
        new MateriaDTO(1, "Programacion Web", "Web"),
        new MateriaDTO(2, "Servicios Web", "Web"),
        new MateriaDTO(3, "Conmutacion y Enrutamiento", "Redes")
    ));

    // Se llama al abrir GET /materias o GET /materias?categoria=Web.
    // Model envía datos a materias.html; th:each recorre la lista.
    @GetMapping
    public String listar(@RequestParam(defaultValue = "") String categoria, Model model) {
        List<MateriaDTO> resultado = materias.stream()
            .filter(m -> categoria.isBlank() || m.getCategoria().equalsIgnoreCase(categoria))
            .toList();

        model.addAttribute("materias", resultado);
        model.addAttribute("categoria", categoria);
        model.addAttribute("nombreProyecto", nombreProyecto);
        return "materias";
    }

    // Se llama al abrir GET /materias/1 (el número es el id).
    // @PathVariable recibe el id de la dirección y devuelve detalle.html.
    @GetMapping("/{id}")
    public String detalle(@PathVariable int id, Model model) {
        MateriaDTO encontrada = materias.stream()
            .filter(m -> m.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Materia no encontrada"));

        model.addAttribute("materia", encontrada);
        model.addAttribute("nombreProyecto", nombreProyecto);
        return "detalle";
    }

    // Se llama al abrir GET /materias/nueva y muestra formulario.html.
    @GetMapping("/nueva")
    public String formulario(Model model) {
        model.addAttribute("materia", new MateriaDTO());
        model.addAttribute("nombreProyecto", nombreProyecto);
        return "formulario";
    }

    // Se llama al enviar el formulario HTML con POST /materias/guardar.
    // @ModelAttribute recibe el DTO, lo agrega en memoria y vuelve a materias.html.
    @PostMapping("/guardar")
    public synchronized String guardar(@ModelAttribute("materia") MateriaDTO materia) {
        int nuevoId = materias.stream()
            .mapToInt(MateriaDTO::getId)
            .max()
            .orElse(0) + 1;

        materia.setId(nuevoId);
        materias.add(materia);
        return "redirect:/materias";
    }
}
