package io.reflectoring.Sprint3SpringBoot.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Dto.UserDto;
import io.reflectoring.Sprint3SpringBoot.Dto.UserFullDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.ParamException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RepositoryException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.UserNotFoundException;
import io.reflectoring.Sprint3SpringBoot.Mapper.UsersMapper;
import io.reflectoring.Sprint3SpringBoot.Models.Client;
import io.reflectoring.Sprint3SpringBoot.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing clients.
 * <p>
 * Provides endpoints for retrieving, creating, updating, and managing client favorites.
 * </p>
 */

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;
    private final UsersMapper usersMapper;

    /**
     * Constructs a {@code ClientController} with the required dependencies.
     *
     * @param clientService The service handling client operations.
     * @param usersMapper   The mapper for converting {@link Client} objects to DTOs.
     */
    @Autowired
    public ClientController(ClientService clientService, UsersMapper usersMapper) {
        this.clientService = clientService;
        this.usersMapper = usersMapper;
    }

    /**
     * Retrieves a client by their unique ID.
     *
     * @param id The ID of the client.
     * @return A {@link ResponseEntity} containing the {@link UserDto} if found,
     *         or an error message with the appropriate HTTP status.
     * @throws UserNotFoundException If the client does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable int id) {
        try {
            Client client = clientService.getClientById(id);
            return ResponseEntity.ok(usersMapper.ClientToDto(client));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Retrieves all clients.
     *
     * @return A {@link ResponseEntity} containing a list of {@link UserDto} objects
     *         or an error message if no clients are found.
     * @throws UserNotFoundException If no clients exist in the database.
     */
    @GetMapping
    public ResponseEntity<?> getAllClients() {
        try {
            List<Client> clients = clientService.getAllClients();
            List<UserDto> clientDtos = new ArrayList<>();
            for (Client client : clients) {
                clientDtos.add(usersMapper.ClientToDto(client));
            }
            return ResponseEntity.ok(clientDtos);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Updates an existing client.
     *
     * @param id      The ID of the client to update.
     * @param userDto A {@link UserDto} containing the updated client details.
     * @return A {@link ResponseEntity} containing the updated client or an error message.
     * @throws UserNotFoundException If the client does not exist.
     * @throws ParamException        If the request parameters are invalid.
     * @throws RepositoryException   If there is an issue with updating the client in the repository.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable int id, @RequestBody UserDto userDto) {
        try {
            Client updatedClient = clientService.getClientById(id);
            updatedClient.name = userDto.name;
            updatedClient.email = userDto.email;

            clientService.updateClient(id, updatedClient);
            return ResponseEntity.ok(userDto);

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (ParamException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Creates a new client.
     *
     * @param userFullDto A {@link UserFullDto} containing the client details.
     * @return A {@link ResponseEntity} containing the created client or an error message.
     * @throws RepositoryException If there is an issue saving the client in the repository.
     */
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody UserFullDto userFullDto) {
        try {
            Client newClient = new Client();
            newClient.name = userFullDto.name;
            newClient.email = userFullDto.email;
            newClient.password = userFullDto.password;
            newClient.role = userFullDto.role;

            clientService.createClient(newClient);
            return ResponseEntity.status(HttpStatus.CREATED).body(usersMapper.ClientToDto(newClient));
        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Retrieves the favorite fountains of a specific client.
     *
     * @param id The ID of the client.
     * @return A {@link ResponseEntity} containing the list of favorite fountains
     *         or an error message if the client is not found.
     * @throws UserNotFoundException If the client does not exist.
     */
    @GetMapping("/favorites/{id}")
    public ResponseEntity<?> getClientFavourites(@PathVariable int id) {
        try {
            return ResponseEntity.ok(clientService.getClientFavourites(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Adds a fountain to the client's list of favorites.
     *
     * @param id          The ID of the client.
     * @param fountainDto The {@link FountainDto} representing the fountain to be added.
     * @return A {@link ResponseEntity} containing the added favorite fountain or an error message.
     * @throws UserNotFoundException If the client does not exist.
     * @throws ParamException        If the request parameters are invalid.
     */
    @PutMapping("/addfavorite/{id}")
    public ResponseEntity<?> addFavourite(@PathVariable int id, @RequestBody FountainDto fountainDto) {
        try {
            return ResponseEntity.ok(clientService.addFavourite(id, fountainDto));

        } catch (ParamException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Removes a fountain from the client's list of favorites.
     *
     * @param id          The ID of the client.
     * @param fountainDto The {@link FountainDto} representing the fountain to be removed.
     * @return A {@link ResponseEntity} containing the removed favorite fountain or an error message.
     * @throws UserNotFoundException If the client does not exist.
     * @throws ParamException        If the request parameters are invalid.
     */
    @PutMapping("/removefavorite/{id}")
    public ResponseEntity<?> removeFavourite(@PathVariable int id, @RequestBody FountainDto fountainDto) {
        try {
            return ResponseEntity.ok(clientService.removeFavourite(id, fountainDto));

        } catch (ParamException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
