package com.example.Store.controladores;

import com.example.Store.modelos.Detalle;
import com.example.Store.modelos.Usuario;
import com.example.Store.servicios.DetalleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("storeapi/v1/detalle")
public class DetalleControlador {
    @Autowired
    DetalleServicio detalleServicio;

    @PostMapping
    public ResponseEntity<?> guardarDetalle(@RequestBody Detalle datosDetalle){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(detalleServicio.guardarDetalle(datosDetalle));
        }catch (Exception error){
            Map<String, Object> errorDetalle = new LinkedHashMap<>();
            errorDetalle.put("timestamp", LocalDateTime.now());
            errorDetalle.put("message", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }
    @GetMapping
    public ResponseEntity<?> buscarDetalle(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(detalleServicio.buscarTodosDetalle());
        }catch (Exception error){
            Map<String, Object> errorDetalle = new LinkedHashMap<>();
            errorDetalle.put("timestamp", LocalDateTime.now());
            errorDetalle.put("message", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorDetalle);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<?> buscarDeteallePorId(@PathVariable Integer id){
        try {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(detalleServicio.consultarDetalleId(id));
        }catch (Exception error){
            Map<String, Object> errorDetalle = new LinkedHashMap<>();
            errorDetalle.put("timestamp", LocalDateTime.now());
            errorDetalle.put("message", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorDetalle);
        }
    }

}
