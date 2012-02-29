package pl.softwaremill.asamal.example.service.ticket;

import pl.softwaremill.asamal.example.model.ticket.TicketCategory;
import pl.softwaremill.common.cdi.transaction.Transactional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named("tickets")
public class TicketService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<TicketCategory> getTicketCategories() {
        return entityManager.createQuery("select t from TicketCategory t order by t.fromDate").getResultList();
    }

    @Transactional
    public void addTicketCategory(TicketCategory ticketCategory) {
        entityManager.persist(ticketCategory);
    }

    @Transactional
    public TicketCategory loadCategory(Long id) {
        return entityManager.find(TicketCategory.class, id);
    }
    
    @Transactional
    public TicketCategory getAllTicketCategory() {
        return (TicketCategory) entityManager.createQuery("select t from TicketCategory t where t.name = :name")
                .setParameter("name", TicketCategory.ALL_CATEGORY)
                .getSingleResult();
    }

    @Transactional
    public void updateTicketCategory(TicketCategory ticketCat) {
        entityManager.merge(ticketCat);
    }

    @Transactional
    public void deleteTicketCategory(Long id) {
        TicketCategory category = entityManager.find(TicketCategory.class, id);

        if (TicketCategory.ALL_CATEGORY.equals(category.getName())) {
            throw new RuntimeException("You cannot delete ALL category !");
        }
        entityManager.remove(category);
    }
}
