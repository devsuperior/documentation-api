package com.devsuperior.dsmovie.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.services.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/movies")
@Tag(name = "Movies", description = "Controller for Movie")
public class MovieController {

	@Autowired
	private MovieService service;

	@Operation(
			description = "Get all movies",
			summary = "List all movies",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200"
					),	
			}
	)
	@GetMapping(produces = "application/json")
	public Page<MovieDTO> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@Operation(
			description = "Get movie by id",
			summary = "Get movie by Id",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200"
					),
					@ApiResponse(
							description = "Not Found",
							responseCode = "404"
					),
			}
	)
	@GetMapping(value = "/{id}", produces = "application/json")
	public MovieDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@Operation(
			description = "Create a new movie",
			summary = "Create a new movie",
			responses = {
					@ApiResponse(
							description = "Created",
							responseCode = "201"
					),
					@ApiResponse(
							description = "Bad Request",
							responseCode = "400"
					),
					@ApiResponse(
							description = "Unauthorized",
							responseCode = "401"
					),
					@ApiResponse(
							description = "Forbidden",
							responseCode = "403"
					),
					@ApiResponse(
							description = "Unprocessable Entity",
							responseCode = "422"
					),
			}
	)
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(produces = "application/json")
	public ResponseEntity<MovieDTO> insert(@Valid @RequestBody MovieDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@Operation(
			description = "Update a movie",
			summary = "Update movie",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200"
					),
					@ApiResponse(
							description = "Not Found",
							responseCode = "404"
					),
					@ApiResponse(
							description = "Unauthorized",
							responseCode = "401"
					),
					@ApiResponse(
							description = "Forbidden",
							responseCode = "403"
					),
					@ApiResponse(
							description = "Unprocessable Entity",
							responseCode = "422"
					),
			}
	)
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<MovieDTO> update(@PathVariable Long id, @Valid @RequestBody MovieDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@Operation(
			description = "Delete a movie",
			summary = "Delete movie",
			responses = {
				@ApiResponse(
						description = "Success",
						responseCode = "204"
				),
				@ApiResponse(
						description = "Bad Request",
						responseCode = "400"
				),
				@ApiResponse(
						description = "Not Found",
						responseCode = "404"
				),
				@ApiResponse(
						description = "Unauthorized",
						responseCode = "401"
				),
				@ApiResponse(
						description = "Forbidden",
						responseCode = "403"
				)
			}
	)
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<MovieDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
