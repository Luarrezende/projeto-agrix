package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/*
 * @project ebytr
 */
public record PersonDto(Long id, String username, Role role) {
}
