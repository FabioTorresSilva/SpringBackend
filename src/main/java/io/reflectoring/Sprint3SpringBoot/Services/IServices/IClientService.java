package io.reflectoring.Sprint3SpringBoot.Services.IServices;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Models.Client;

import java.util.List;

/**
 * Interface for managing {@link Client} entities.
 * This service provides methods to interact with client data, including retrieving, updating, creating clients,
 * and managing client-specific favorites (fountains).
 */
public interface IClientService {

    /**
     * Retrieves a client by their unique identifier.
     *
     * @param id The unique identifier of the client to be retrieved.
     * @return The {@link Client} object associated with the provided ID.
     */
    Client getClientById(int id);

    /**
     * Retrieves all clients in the system.
     *
     * @return A list of {@link Client} objects representing all clients.
     */
    List<Client> getAllClients();

    /**
     * Updates an existing client with the provided data.
     * The client's ID should match the provided ID in order to successfully update the client.
     *
     * @param id     The unique identifier of the client to be updated.
     * @param client The {@link Client} object containing the updated data.
     * @return The updated {@link Client} object.
     */
    Client updateClient(int id, Client client);

    /**
     * Creates a new client with the provided data.
     *
     * @param client The {@link Client} object to be created.
     * @return The newly created {@link Client} object.
     */
    Client createClient(Client client);

    /**
     * Retrieves a client's list of favorite fountains.
     * The method returns all fountains marked as favorites by the specified client.
     *
     * @param id The unique identifier of the client whose favorites are being requested.
     * @return A list of {@link FountainDto} objects representing the client's favorite fountains.
     */
    List<FountainDto> getClientFavourites(int id);

    /**
     * Adds a favorite fountain to the client's favorites list.
     * The method returns the fountain added.
     *
     * @param id The unique identifier of the client whose favorites list is being updated.
     * @return The fountain {@link FountainDto} object added to the list.
     */
    FountainDto addFavourite(int id, FountainDto fountainDto);
}
