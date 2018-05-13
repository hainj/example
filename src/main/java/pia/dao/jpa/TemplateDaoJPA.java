package pia.dao.jpa;

import pia.data.Template;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Trida pro komunikaci s databazi, tabulkou template
 * Created by jakub on 06.01.2017.
 */
public class TemplateDaoJPA extends DaoJPA<Template, Long> implements TemplateJ {
    /**
     * @param em             entity manager
     * @param persistedClass entity type to be persisted by this instance
     */
    public TemplateDaoJPA(EntityManager em, Class<Template> persistedClass) {
        super(em, persistedClass);
    }

    @Override
    public Template create(Template template) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(template);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }

        return template;
    }

    /**
     * Najde template podle primarniho klice
     * @param id id template
     * @return template nebo null pokud neexistuje
     */
    @Override
    public Template findTemplate(Long id) {
        TypedQuery<Template> q =  entityManager.createQuery("SELECT u FROM Template u WHERE u.id = :id",persistedClass).setParameter("id", id);
        Template u = null;
        try {
            u = q.getSingleResult();
        }catch (NoResultException e){
            String error = "no result";
        }
        return u;
    }

    /**
     * Upraví template na nové hodnoty
     * @param template upravovany template s novými hodnotami
     * @return template
     */
    @Override
    public Template updateTemplate(Template template) {
        entityManager.getTransaction().begin();
      Query query = entityManager.createQuery("UPDATE Template a SET a.accNumber=:accN, a.amount=:amount,a.bankCode=:bank, a.message=:mess, a.varSymbol=:var, a.specSymbol=:spec,a.name=:name,a.constSymbol=:const WHERE a.id=:id");
        query.setParameter("accN", template.getAccNumber());
        query.setParameter("amount", template.getAmount());
        query.setParameter("bank", template.getBankCode());
        query.setParameter("mess", template.getMessage());
        query.setParameter("var", template.getVarSymbol());
        query.setParameter("const", template.getConstSymbol());
        query.setParameter("spec", template.getSpecSymbol());
        query.setParameter("name", template.getName());
        query.setParameter("id", template.getId());
        int i = query.executeUpdate();
        entityManager.getTransaction().commit();
        if (i == 1) {
            return entityManager.find(persistedClass, template.getId());
        }
        return null;
    }

    /**
     * Smaze template podle primarniho klice tabulky template
     * @param id hodnota primarniho klice
     * @return pocet smazanych zaznamu
     */
    @Override
    public int deleteTemplate(Long id) {
        entityManager.getTransaction().begin();
        int q =  entityManager.createQuery("DELETE FROM Template t WHERE t.id=:id").setParameter("id",id).executeUpdate();
        entityManager.getTransaction().commit();
        return q;
    }

    /**
     * Smaze vsechny templaty urciteho uctu
     * @param id primarni klic uctu
     * @return pocet smazanych template
     */
    @Override
    public int deleteUserTemplates(Long id) {
        entityManager.getTransaction().begin();
        int q =  entityManager.createQuery("DELETE FROM Template t WHERE t.accId.id=:id").setParameter("id",id).executeUpdate();
        entityManager.getTransaction().commit();
        return q;
    }

    /**
     * Vrati seznam vsech template uctu
     * @param id primarni klic uctu
     * @return seznam uctu
     */
    @Override
    public List<Template> findAccTemplates(Long id) {

        TypedQuery<Template> query = entityManager.createQuery("SELECT t FROM Template t WHERE t.accId.id=:id", persistedClass);
        query.setParameter("id", id);
        List<Template> templates = query.getResultList();


        return templates;
    }


    @Override
    public Template findOne(Long id) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }
}
