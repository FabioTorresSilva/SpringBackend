package io.reflectoring.Sprint3SpringBoot.Services;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.ParamException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RepositoryException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.UserNotFoundException;
import io.reflectoring.Sprint3SpringBoot.Models.Client;
import io.reflectoring.Sprint3SpringBoot.Repositories.ClientRepository;
import io.reflectoring.Sprint3SpringBoot.Services.IServices.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing clients.
 * This class implements the methods defined in the IClientService interface to perform CRUD operations on clients.
 */
@Service
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param clientRepository The repository for client operations.
     */
    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    /**
     * Retrieves a client by its unique identifier.
     *
     * @param id The unique identifier of the client.
     * @return A {@link Client} object representing the client, or throws a {@link UserNotFoundException} if the client is not found.
     */
    @Override
    public Client getClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new UserNotFoundException("Client with ID " + id + " not found.");
        }

        return client.get();
    }

    /**
     * Retrieves all clients in the system.
     *
     * @return A list of {@link Client} objects representing all clients in the system.
     */
    @Override
    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();

        if (clients.isEmpty()) {
            throw new UserNotFoundException("Clients not found.");
        }
        return clients;
    }

    /**
     * Updates an existing client with the provided data.
     * This method ensures that the provided ID matches the ID of the client to be updated,
     * retrieves the client from the database, updates the fields, and saves the changes.
     * If the provided ID does not match the client's ID, a {@link ParamException} will be thrown.
     * If an error occurs during saving to the repository, a {@link RepositoryException} will be thrown.
     *
     * @param id     The unique identifier of the client to be updated.
     * @param client The {@link Client} object containing the updated data.
     * @return The updated {@link Client} object.
     */
    @Override
    public Client updateClient(int id, Client client) {

        if (id != client.getId()) {
            throw new ParamException("Ids do not match.");
        }

        Client updateClient = getClientById(id);

        updateClient.setName(client.getName());
        updateClient.setEmail(client.getEmail());
        updateClient.setPassword(client.getPassword());

        try {
            clientRepository.save(updateClient);
        } catch (Exception e) {
            throw new RepositoryException("Error updating client in repository");
        }

        return updateClient;
    }

    /**
     * Creates a new client with the provided data.
     *
     * @param client A {@link Client} object containing the data for the new client.
     * @return The newly created {@link Client} object.
     */
    @Override
    public Client createClient(Client client) {

        try {
            clientRepository.save(client);
        } catch (Exception e) {
            throw new RepositoryException("Error creating client in repository");
        }

        return client;
    }

    /**
     * Retrieves a client's list of favorite fountains.
     * The method returns all fountains marked as favorites by the specified client.
     *
     * @param id The unique identifier of the client whose favorites are being requested.
     * @return A list of {@link FountainDto} objects representing the client's favorite fountains.
     */
    @Override
    public List<FountainDto> GetClientFavourites(int id) {
        Client client = getClientById(id);
        return client.getFavourites(); 
    }
}
