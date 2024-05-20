package fr.univrouen.cv24.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.cv24.repository.CVRepository;

@RestController
public class DeleteController {

	@DeleteMapping("/cv24/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteCV(@PathVariable Long id) {
        boolean deleted = deleteCVById(id);
        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("id", String.valueOf(id));
            response.put("status", "DELETED");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	@Autowired
    private CVRepository cvRepository;

    private boolean deleteCVById(Long id) {
        try {
            if (cvRepository.existsById(id)) {            
                cvRepository.deleteById(id);
                return true; 
            } else {
                return false; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }   
}
