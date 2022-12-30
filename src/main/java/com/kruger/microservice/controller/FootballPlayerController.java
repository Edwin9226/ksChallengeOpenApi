package com.kruger.microservice.controller;


import com.kruger.microservice.model.FootBallPlayer;
import com.kruger.microservice.model.service.FootballPlayerService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/footballplayer")
@OpenAPIDefinition(info = @Info(title = "Footbal Player API",
		description = "API for creating football player",
		version = "1.0",
		contact = @Contact(
				name = "Edwin",
				email = "miguepinchao1992@gmail.com",
				url = "https://github.com/Edwin9226"
		),
		license = @License(
				name = "MIT Licence",
				url = "https://opensource.org/licenses/mit-license.php"
		)
))
@Tag(name = "FootballPlayer")
public class FootballPlayerController {
	
	@Autowired
	private FootballPlayerService service;

@Tag(name="FootballPlayer", description = "Find all FootballPalayer ")
@Operation(summary = "Get all FootballPlayer")
	@RequestMapping(value = "/all",method=RequestMethod.GET, produces="application/json" )
	public Iterable<FootBallPlayer> findAll(){
		return service.findAll();
	}

	@Operation(summary = "Save FootballPlayer")
	@ApiResponse(responseCode = "200", description =
			"Successful retrieval of footballPlayer", content =
	@Content(array = @ArraySchema(schema =
	@Schema(implementation = FootBallPlayer.class))))
	@Tag(name="Save FootballPalayer", description = "Save FootballPalayer ")
	@RequestMapping(value="/save", method=RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	public FootBallPlayer save(@RequestBody FootBallPlayer entity) {

		return service.save(entity);
	}
	@Tag(name="Update FootballPalayer",  description = "Update FootballPalayer ")
	@Operation(summary = "update FootballPlayer")
	@RequestMapping(value = "/update/{id}",method=RequestMethod.PUT,  produces = MediaType.APPLICATION_JSON_VALUE)
	public FootBallPlayer edit(@PathVariable Integer id, @RequestBody FootBallPlayer entity) {
		Optional<FootBallPlayer> footBallPlayer=service.findById(id);
		return service.save(entity);
	}
	@Operation(summary = "Delete FootballPlayer by ID")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
	@Operation(summary = "Find FootballPlayer by ID")
	@RequestMapping(value = "find/{id}", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<FootBallPlayer> findById(@PathVariable Integer id){
		return service.findById(id);
	}

}
