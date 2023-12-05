package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * PersonDto sem senha.
 */
public record PersonDto(Long id, String username, Role role) {
}
