package pia.dao.jpa;

import pia.dao.GenericDao;
import pia.data.Template;
import pia.data.UserJ;

import java.util.List;

/**
 * Created by jakub on 06.01.2017.
 */
public interface TemplateJ extends GenericDao<Template,Long>{
    /**
     * Creates template
     * @param template created template
     * @return template
     */
    Template create(Template template);

    /**
     * Find template by id
     * @param id template id
     * @return
     */
    Template findTemplate(Long id);

    /**
     * Update template
     * @param template updated template
     * @return template
     */
    Template updateTemplate(Template template);

    /**
     * delete template by id
     * @param id template id
     * @return count deleted
     */
    int deleteTemplate(Long id);

    /**
     * delete all templates by account id
     * @param id account id
     * @return deleted count
     */
    int deleteUserTemplates(Long id);

    /**
     * Find all account templates
     * @param id account id
     * @return list of templates
     */
    List<Template> findAccTemplates(Long id);
}
