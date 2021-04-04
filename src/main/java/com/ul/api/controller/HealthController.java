package com.ul.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The purpose of this interface end point is to check the
 * health of rest controller.
 */
public interface HealthController {
    /**
     *
     * @return {@link ResponseEntity}
     * @throws Exception
     */
    @GetMapping("/health")
    default ResponseEntity<String> status() throws Exception {
        return ResponseEntity.ok().body("Status : Active");
    }
}
