package ua.edu.ukma.cinemax.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.cinemax.persistance.entity.Session;
import ua.edu.ukma.cinemax.persistance.entity.ShoppingCart;
import ua.edu.ukma.cinemax.persistance.entity.Ticket;
import ua.edu.ukma.cinemax.persistance.entity.User;
import ua.edu.ukma.cinemax.persistance.repository.ShoppingCartRepository;
import ua.edu.ukma.cinemax.persistance.repository.TicketRepository;
import ua.edu.ukma.cinemax.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   TicketRepository ticketRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void addSession(Session session, User user) {
        Ticket newTicket = new Ticket();
        newTicket.setUser(user);
        newTicket.setFilmSession(session);
        ShoppingCart shoppingCart = getByUser(user);
        shoppingCart.getTickets().add(ticketRepository.save(newTicket));
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartRepository.getByUser(user).get();
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void clearShoppingCart(ShoppingCart shoppingCart) {
        ShoppingCart shoppingCartForClear =
                shoppingCartRepository.getReferenceById(shoppingCart.getId());
        shoppingCartForClear.setTickets(new ArrayList<>());
        shoppingCartRepository.save(shoppingCartForClear);
    }
}
