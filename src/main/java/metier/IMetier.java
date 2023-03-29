package metier;

import dao.Credit;

public interface IMetier {
    Credit calculer_Mensualite(Long idCreedit)
        throws Exception;
}
