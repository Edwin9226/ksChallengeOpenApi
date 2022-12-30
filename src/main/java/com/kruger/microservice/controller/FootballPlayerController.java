package com.kruger.microservice.controller;


import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.microservice.model.FootBallPlayer;
import com.kruger.microservice.model.service.FootballPlayerService;

@RestController
@RequestMapping("/footballplayer")
@Tag(name = "member", description = "The member API")
public class FootballPlayerController {
	
	@Autowired
	private FootballPlayerService service;

	@Operation(
			summary = "Find all members",
			description = "Find all members",
			tags = "member"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "successful operation",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = FootBallPlayer.class)
									)
							)
					)
			}
	)
	@RequestMapping(value = "/all",method=RequestMethod.GET, produces="application/json" )
	public Iterable<FootBallPlayer> findAll(){
		return service.findAll();
	}
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public FootBallPlayer save(@RequestBody FootBallPlayer entity) {

		return service.save(entity);
	}
	@RequestMapping(value = "/update/{id}",method=RequestMethod.PUT, produces="application/json" )
	public FootBallPlayer edit(@PathVariable Integer id, @RequestBody FootBallPlayer entity) {
		Optional<FootBallPlayer> footBallPlayer=service.findById(id);
		return service.save(entity);
	}
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
	@RequestMapping(value = "find/{id}", method = RequestMethod.POST)
	public Optional<FootBallPlayer> findById(@PathVariable Integer id){
		return service.findById(id);
	}

}
