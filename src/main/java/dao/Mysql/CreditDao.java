package dao.Mysql;

import Model.Credit;
import Model.Utilisateur;
import dao.IDao;

import java.util.List;

public class CreditDao implements IDao<Credit, Long> {
    @Override
    public Credit trouveParId(Long aLong) {
        return null;
    }

    @Override
    public List<Credit> findAll() {
        return null;
    }

    @Override
    public Credit save(Credit credit) {
        return null;
    }

    @Override
    public Credit update(Credit credit) {
        return null;
    }

    @Override
    public Boolean delete(Credit credit) {
        return null;
    }

    @Override
    public Boolean deteleByID(Long aLong) {
        return null;
    }
}
